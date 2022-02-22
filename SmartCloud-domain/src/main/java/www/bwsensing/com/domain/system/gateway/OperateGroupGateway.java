package www.bwsensing.com.domain.system.gateway;

import www.bwsensing.com.domain.system.model.user.OperateGroup;
/**
 * @author macos-zyj
 */
public interface OperateGroupGateway {
    /**
     * 保存
     * @param operateGroup
     */
    void  save(OperateGroup operateGroup);

    /**
     * 修改
     * @param operateGroup
     */
    void  update(OperateGroup operateGroup);

}
