package www.bwsensing.com.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.clientobject.StructureTemplateCO;
import www.bwsensing.com.dto.command.StructureTemplateSaveCmd;
import www.bwsensing.com.dto.command.StructureTemplateUpdateCmd;
import www.bwsensing.com.dto.command.query.StructureTemplateSortPageQuery;
import www.bwsensing.com.dto.command.query.StructureTemplateSortQuery;

/**
 * @author macos-zyj
 */
public interface StructureTemplateService {
    /**
     * 新增
     * @param saveCmd
     * @return
     */
    Response addStructureTemplate(StructureTemplateSaveCmd saveCmd);


    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response updateStructureTemplate(StructureTemplateUpdateCmd updateCmd);

    /**
     * 根据ID进行查询
     * @param id
     * @return
     */
    SingleResponse<StructureTemplateCO> getStructureTemplateById(Integer id);

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    PageResponse<StructureTemplateCO> pageQueryStructureTemplateBySort(StructureTemplateSortPageQuery pageQuery);

    /**
     * 条件查询
     * @param templateSortQuery
     * @return
     */
    MultiResponse<StructureTemplateCO> queryStructureTemplateBySort(StructureTemplateSortQuery templateSortQuery);
}
