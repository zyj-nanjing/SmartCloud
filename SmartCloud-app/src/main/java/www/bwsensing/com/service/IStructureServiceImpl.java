package www.bwsensing.com.service;

import javax.annotation.Resource;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import www.bwsensing.com.api.StructureService;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.dto.clientobject.SystemStructureCO;
import www.bwsensing.com.domain.gateway.SystemStructureGateway;
import www.bwsensing.com.domain.system.organization.SystemStructure;


/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class IStructureServiceImpl implements StructureService {
    private static final BeanCopier COPIER = BeanCopier.create(SystemStructure.class, SystemStructureCO.class,false);
    @Resource
    private SystemStructureGateway systemStructureGateway;

    @Override
    public SingleResponse<SystemStructureCO> getStructureById(Integer id) {
        SystemStructure systemStructure = systemStructureGateway.getSystemStructureById(id);
        if(null != systemStructure && StringUtils.isNotEmpty(systemStructure.getName())) {
            SystemStructureCO result = new SystemStructureCO();
            COPIER.copy(systemStructure, result,null);
            result.setStructureType(systemStructure.getStructureType().getRemark());
            return SingleResponse.of(result);
        }
        return SingleResponse.of(new SystemStructureCO());
    }

}
