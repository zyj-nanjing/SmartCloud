package www.bwsensing.com.api;

import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.clientobject.SystemStructureCO;

/**
 * 组织结构接口
 * @author macos-zyj
 */
public interface StructureService {
    /**
     * 根据ID进行查询
     * @param id
     * @return
     */
    SingleResponse<SystemStructureCO> getStructureById(Integer id);

}
