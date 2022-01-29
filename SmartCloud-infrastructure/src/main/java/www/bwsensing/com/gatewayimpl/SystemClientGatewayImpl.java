package www.bwsensing.com.gatewayimpl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.convertor.SystemClientConvertor;
import www.bwsensing.com.domain.system.client.SystemClient;
import www.bwsensing.com.domain.gateway.SystemClientGateway;
import www.bwsensing.com.domain.gateway.SystemStructureGateway;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.gatewayimpl.database.SystemClientMapper;
import www.bwsensing.com.domain.system.organization.StructureTypeEnum;
import www.bwsensing.com.domain.system.organization.SystemStructure;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemClientDO;


/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class SystemClientGatewayImpl implements SystemClientGateway {
    @Resource
    private SystemClientMapper systemClientMapper;
    @Resource
    private SystemStructureGateway systemStructureGateway;
    @Resource
    private TokenGateway tokenGateway;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveSystemClient(SystemClient systemClient) {
        TokenData userData = tokenGateway.getTokenInfo();
        SystemClientDO dataObject = SystemClientConvertor.toDataObject(systemClient);
        dataObject.setCreator(userData.getUserName());
        dataObject.setCreateTime(new Date());
        filterSystemClient(systemClient);
        systemClientMapper.addClient(dataObject);
        systemClient.getReleaseFields().forEach(field -> systemClientMapper.addClientFieldRelate(dataObject.getId(), field.getId()) );
        SystemStructure saveStructure = systemClient.getInnerStructure();
        saveStructure.setOwnerId(dataObject.getId());
        saveStructure.setStructureType(StructureTypeEnum.CLIENT_STRUCTURE);
        systemStructureGateway.saveSystemStructure(saveStructure);
        log.info("add client: client name:{}",systemClient.getClientName());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateSystemClient(SystemClient systemClient) {
        TokenData userData = tokenGateway.getTokenInfo();
        filterSystemClient(systemClient);
        SystemClientDO dataObject = SystemClientConvertor.toDataObject(systemClient);
        dataObject.setUpdater(userData.getUserName());
        dataObject.setUpdateTime(new Date());
        systemClientMapper.deleteClientFieldRelate(systemClient.getId());
        systemClient.getReleaseFields().forEach(field -> systemClientMapper.addClientFieldRelate(dataObject.getId(), field.getId()) );
        systemClientMapper.updateClient(dataObject);
    }

    @Override
    public void deleteSystemClient(Integer clientId) {

    }

    private void filterSystemClient(SystemClient systemClient){
        if ( null != systemClient.getId()){
            SystemClientDO dataResult = systemClientMapper.getClientById(systemClient.getId());
            if (StringUtils.isNotEmpty(dataResult.getClientName())){
                if ( dataResult.getClientName().equals(systemClient.getClientName()) ){
                    return;
                }
            }
        }
        SystemClientDO query = new SystemClientDO();
        query.setClientName(systemClient.getClientName());
        query.setClientShortName(systemClient.getClientShortName());
        List<SystemClientDO> dataCollection = systemClientMapper.validSystemClientBySort(query);
        if (dataCollection.size() > 0){
            throw new BizException("NAME_EXIST","该客户已存在!");
        }
    }

}
