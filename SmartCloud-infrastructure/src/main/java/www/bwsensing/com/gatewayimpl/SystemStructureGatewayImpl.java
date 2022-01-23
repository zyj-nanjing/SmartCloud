package www.bwsensing.com.gatewayimpl;

import java.util.*;
import javax.annotation.Resource;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.convertor.SystemDeptConvertor;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.convertor.SystemStructureConvertor;
import www.bwsensing.com.domain.gateway.SystemStructureGateway;
import www.bwsensing.com.domain.system.organization.SystemDept;
import www.bwsensing.com.gatewayimpl.database.SystemDeptMapper;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.gatewayimpl.database.SystemStructureMapper;
import www.bwsensing.com.domain.system.organization.SystemStructure;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemDeptDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemStructureDO;

/**
 * @author macos-zyj
 */
@Component
public class SystemStructureGatewayImpl implements SystemStructureGateway {
    @Resource
    private SystemStructureMapper systemStructureMapper;
    @Resource
    private SystemDeptMapper systemDeptMapper;
    @Resource
    private TokenGateway tokenGateway;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveSystemStructure(SystemStructure systemStructure) {
        TokenData userData = tokenGateway.getTokenInfo();
        SystemStructureDO dataObject = SystemStructureConvertor.toDataObject(systemStructure);
        dataObject.setCreator(userData.getUserName());
        dataObject.setCreateTime(new Date());
        systemStructureMapper.addStructure(dataObject);
        if(null != systemStructure.getDepartments() && systemStructure.getDepartments().size() > 0){
            systemStructure.getDepartments().forEach(department -> department.setStructureId(dataObject.getId()));
            List<SystemDept> waitSaveDept = initialiseDeptTree(systemStructure.getDepartments());
            waitSaveDept.forEach(this::addDepartment);
        }
    }

    @Override
    public void addDepartment(SystemDept systemDept) {
        validDepartment(systemDept);
        filterDepartment(systemDept);
        addDepartment(systemDept,null);
    }


    @Override
    public void updateDepartment(SystemDept systemDept) {
        validDepartment(systemDept);
        filterDepartment(systemDept);
        SystemDeptDO updateData = SystemDeptConvertor.toDataObject(systemDept);
        systemDeptMapper.updateSystemDept(updateData);
    }

    @Override
    public void deleteDepartment(Integer id) {

    }

    @Override
    public SystemStructure getSystemStructureById(Integer id) {
        SystemStructureDO dataObject = systemStructureMapper.getStructureById(id);
        if (null != dataObject ){
            List<SystemDeptDO> deptDataCollection = systemDeptMapper.getDepartmentByStructureId(id);
            List<SystemDept> deptCollection = SystemDeptConvertor.toDomainCollection(deptDataCollection);
            SystemStructure domainStructure = SystemStructureConvertor.toDomain(dataObject);
            domainStructure.setDepartments(initialiseDeptTree(deptCollection));
            return domainStructure;
        }
        return new SystemStructure();
    }

    private void addDepartment(SystemDept systemDept,Integer fatherId){
        if(null != fatherId) {
            systemDept.setParentId(fatherId);
        }
        SystemDeptDO dataObject = SystemDeptConvertor.toDataObject(systemDept);
        systemDeptMapper.addSystemDept(dataObject);
        if(null !=systemDept.getChildrenDept() && systemDept.getChildrenDept().size()>0){
            systemDept.getChildrenDept().forEach(dept -> {
                addDepartment(dept,dataObject.getId());
            });
        }
    }

    private List<SystemDept> initialiseDeptTree(List<SystemDept> deptCollection){
        Map<Integer,List<SystemDept>> dataMap = new LinkedHashMap<>();
        for(SystemDept currentDept : deptCollection){
            dataMap.computeIfAbsent(currentDept.getParentId(), k -> new ArrayList<>());
            dataMap.get(currentDept.getParentId()).add(currentDept);
        }
        return  getCurrentDeptAndChild(null,dataMap);
    }


    private List<SystemDept> getCurrentDeptAndChild(Integer keyLeave,Map<Integer,List<SystemDept>> dataMap ){
        if(null == dataMap.get(keyLeave) ){
            return new ArrayList<>();
        }
        List<SystemDept> fatherNodes = new ArrayList<>(dataMap.get(keyLeave));
        for(SystemDept currentDept : fatherNodes){
            currentDept.setChildrenDept(getCurrentDeptAndChild(currentDept.getId(),dataMap));
        }
        return  fatherNodes;
    }

    private void validDepartment(SystemDept dept){
        if(null != dept.getParentId()){
            Assert.isTrue((null!=systemDeptMapper.getSystemDeptById(dept.getParentId())),"VALID_ERROR","父节点不存在!");
        }
    }

    private void filterDepartment(SystemDept dept){
        int structureId = 0;
        if ( null != dept.getId()){
            SystemDeptDO dataResult = systemDeptMapper.getSystemDeptById(dept.getId());
            structureId = dataResult.getStructureId();
            if (StringUtils.isNotEmpty(dataResult.getDeptName())){
                if ( dataResult.getDeptName().equals(dept.getDeptName()) ){
                    return;
                }
            }
        }
        if (0 == structureId){
            structureId = dept.getStructureId();
        }
        SystemDeptDO query = new SystemDeptDO();
        query.setDeptName(dept.getDeptName());
        query.setStructureId(structureId);
        query.setParentId(dept.getParentId());
        List<SystemDeptDO> dataCollection = systemDeptMapper.validDeptBySort(query);
        if (dataCollection.size() > 0){
            throw new BizException("NAME_EXIST","该名称已存在!");
        }
    }
}
