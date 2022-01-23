package www.bwsensing.com.gatewayimpl;

import java.util.Date;
import java.util.List;

import com.alibaba.cola.exception.Assert;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.convertor.DeptTemplateConvertor;
import www.bwsensing.com.convertor.StructureTemplateConvertor;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.gatewayimpl.database.DeptTemplateMapper;
import www.bwsensing.com.domain.gateway.SysStructureTemplateGateway;
import www.bwsensing.com.gatewayimpl.database.StructureTemplateMapper;
import www.bwsensing.com.domain.system.organization.StructureTemplate;
import www.bwsensing.com.domain.system.organization.SystemDeptTemplate;
import www.bwsensing.com.gatewayimpl.database.dataobject.SysDeptTemplateDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SysStructureTemplateDO;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class StructureTemplateGatewayImpl implements SysStructureTemplateGateway {

    @Resource
    private StructureTemplateMapper structureTemplateMapper;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private DeptTemplateMapper deptTemplateMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void save(StructureTemplate structureTemplate) {
        filterTemplate(structureTemplate.getTemplateName(),null);
        TokenData tokenData = tokenGateway.getTokenInfo();
        structureTemplate.setCreator(tokenData.getUserName());
        structureTemplate.setCreateTime(new Date());
        SysStructureTemplateDO saveData = StructureTemplateConvertor.toDataObject(structureTemplate);
        structureTemplateMapper.addStructureTemplate(saveData);
        structureTemplate.getBelowField().forEach(industryField -> structureTemplateMapper.addStructureTemplateFieldLink(saveData.getId(), industryField.getId()));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void update(StructureTemplate structureTemplate) {
        filterTemplate(structureTemplate.getTemplateName(),structureTemplate.getId());
        TokenData tokenData = tokenGateway.getTokenInfo();
        structureTemplate.setUpdater(tokenData.getUserName());
        structureTemplate.setUpdateTime(new Date());
        SysStructureTemplateDO updateData = StructureTemplateConvertor.toDataObject(structureTemplate);
        structureTemplateMapper.updateStructureTemplate(updateData);
        structureTemplateMapper.deleteStructureTemplateFieldLink(updateData.getId());
        structureTemplate.getBelowField().forEach(industryField -> structureTemplateMapper.addStructureTemplateFieldLink(updateData.getId(), industryField.getId()));
    }

    @Override
    public void addDepartment(SystemDeptTemplate deptTemplate) {
        validDepartment(deptTemplate);
        SysStructureTemplateDO dataObject = structureTemplateMapper.getStructureTemplateById(deptTemplate.getStructureId());
        if(null != dataObject){
            filterDepartment(deptTemplate);
            SysDeptTemplateDO saveData = DeptTemplateConvertor.toDataObject(deptTemplate);
            deptTemplateMapper.addDeptTemplate(saveData);
        } else {
            throw new BizException("SYS_STRUCTURE_TEMPLATE_NOT_EXIST","系统组织结构模板不存在!");
        }
    }

    @Override
    public void updateDepartment(SystemDeptTemplate deptTemplate) {
        validDepartment(deptTemplate);
        filterDepartment(deptTemplate);
        SysDeptTemplateDO updateData = DeptTemplateConvertor.toDataObject(deptTemplate);
        deptTemplateMapper.updateDeptTemplate(updateData);
    }

    @Override
    public void deleteDepartment(SystemDeptTemplate deptTemplate) {

    }

    @Override
    public StructureTemplate getStructureTemplateById(Integer id) {
        SysStructureTemplateDO dataTemplate = structureTemplateMapper.getStructureTemplateById(id);
        return StructureTemplateConvertor.toDomain(dataTemplate);
    }

    private void validDepartment(SystemDeptTemplate deptTemplate){
        if(null != deptTemplate.getParentId()){
            Assert.isTrue((null!=structureTemplateMapper.getStructureTemplateById(deptTemplate.getParentId())),"VALID_ERROR","父节点不存在!");
        }
    }

    private void filterDepartment(SystemDeptTemplate deptTemplate){
        int structureId = 0;
        if ( null != deptTemplate.getId()){
            SysDeptTemplateDO dataResult = deptTemplateMapper.getDeptTemplateById(deptTemplate.getId());
            structureId = dataResult.getStructureId();
            if (StringUtils.isNotEmpty(dataResult.getDeptName())){
                if ( dataResult.getDeptName().equals(deptTemplate.getDeptName()) ){
                    return;
                }
            }
        }
        if (0 == structureId){
            structureId = deptTemplate.getStructureId();
        }
        SysDeptTemplateDO query = new SysDeptTemplateDO();
        query.setDeptName(deptTemplate.getDeptName());
        query.setStructureId(structureId);
        query.setParentId(deptTemplate.getParentId());
        List<SysDeptTemplateDO> dataCollection = deptTemplateMapper.validDeptTemplateBySort(query);
        if (dataCollection.size() > 0){
            throw new BizException("NAME_EXIST","该名称已存在!");
        }
    }

    private void filterTemplate(String name,Integer id){
        if ( null != id ){
            SysStructureTemplateDO result = structureTemplateMapper.getStructureTemplateById(id);
            if (StringUtils.isNotEmpty(result.getTemplateName())){
                if ( result.getTemplateName().equals(name) ){
                    return;
                }
            }
        }
        SysStructureTemplateDO query = new SysStructureTemplateDO();
        query.setTemplateName(name);
        List<SysStructureTemplateDO> dataCollection = structureTemplateMapper.validStructureTemplateBySort(query);
        if (dataCollection.size() > 0){
            throw new BizException("NAME_EXIST","该名称已存在!");
        }
    }
}
