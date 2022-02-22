package www.bwsensing.com.monitor.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.monitor.dto.command.IndustryFieldSaveCmd;
import www.bwsensing.com.monitor.dto.command.IndustryFieldUpdateCmd;
import www.bwsensing.com.monitor.dto.clientobject.IndustryFieldCO;
import www.bwsensing.com.monitor.dto.command.query.IndustryFileSortQuery;

/**
 * @author macos-zyj
 */
public interface IndustryFieldService {

    /**
     * 保存行业领域
     * @param saveCmd
     * @return
     */
    Response saveIndustryField(IndustryFieldSaveCmd saveCmd);


    /**
     * 修改行业领域
     * @param updateCmd
     * @return
     */
    Response updateIndustryField(IndustryFieldUpdateCmd updateCmd);

    /**
     * 根据 id 查询
     * @param id
     * @return
     */
    SingleResponse<IndustryFieldCO> selectIndustryFieldById(Integer id);

    /**
     * 查询所有
     * @param sortQuery
     * @return
     */
    MultiResponse<IndustryFieldCO> selectIndustryFileQuery(IndustryFileSortQuery sortQuery);

    /**
     * 表格查询
     * @param sortQuery
     * @return
     */
    PageResponse<IndustryFieldCO> selectIndustryFileBySortPage(IndustryFileSortQuery sortQuery);
}
