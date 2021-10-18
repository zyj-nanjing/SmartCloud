package www.bwsensing.com.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.command.IndustryFieldSaveCmd;
import www.bwsensing.com.dto.command.IndustryFieldUpdateCmd;
import www.bwsensing.com.dto.clientobject.IndustryFieldCO;
import www.bwsensing.com.dto.command.query.IndustryFileSortQuery;

/**
 * @author macos-zyj
 */
public interface IIndustryFieldService {

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
     * @return
     */
    MultiResponse<IndustryFieldCO> selectIndustryFileQuery();

    /**
     * 表格查询
     * @param sortQuery
     * @return
     */
    PageResponse<IndustryFieldCO> selectIndustryFileBySortPage(IndustryFileSortQuery sortQuery);
}
