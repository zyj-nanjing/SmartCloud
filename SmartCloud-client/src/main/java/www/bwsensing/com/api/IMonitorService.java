package www.bwsensing.com.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.command.PrototypeAddCmd;
import www.bwsensing.com.dto.command.query.PrototypeSortQuery;
import www.bwsensing.com.dto.command.PrototypeUpdateCmd;
import www.bwsensing.com.dto.clientobject.PrototypeCO;

/**
 * @author macos-zyj
 */
public interface IMonitorService {
    /**
     * 保存
     * @param prototypeAddCmd
     * @return
     */
    Response save(PrototypeAddCmd prototypeAddCmd);

    /**
     * 修改
     * @param prototypeUpdateCmd
     * @return
     */
    Response update(PrototypeUpdateCmd prototypeUpdateCmd);

    /**
     * 删除
     * @param typeId
     * @return
     */
    Response delete(int typeId);

    /**
     * 展示所有
     * @return
     */
    MultiResponse<PrototypeCO> showAllPrototype();

    /**
     * 条件查询
     * @param typeSortQuery
     * @return
     */
    PageResponse<PrototypeCO> selectPrototypeBySort(PrototypeSortQuery typeSortQuery);


    /**
     * 根据Id 进行查询
     * @param id
     * @return
     */
    SingleResponse<PrototypeCO>  selectPrototypeById(Integer id);

}
