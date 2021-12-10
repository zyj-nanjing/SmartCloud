package www.bwsensing.com.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.export.PositionModelVo;
import www.bwsensing.com.dto.export.StructureModelVo;
import www.bwsensing.com.dto.clientobject.ImportResultCO;
import www.bwsensing.com.dto.command.query.BaseQuery;
import www.bwsensing.com.dto.command.StructureModelSaveCmd;
import www.bwsensing.com.dto.command.StructureModelUpdateCmd;
import www.bwsensing.com.dto.clientobject.StructureModelCO;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface IStructureModelService {
    /**
     * 保存
     * @param saveCmd
     * @return
     */
    Response save(StructureModelSaveCmd saveCmd);

    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response update(StructureModelUpdateCmd updateCmd);

    /**
     * 删除结构物
     * @param code
     * @return
     */
    Response deleteStructure(String code);

    /**
     * 查询
     * @return
     */
    MultiResponse<StructureModelCO> selectStructureShow();

    /**
     * 结构物模板Excel 导入
     * @param structureCollection
     * @param positionCollection
     * @return
     */
    SingleResponse<ImportResultCO> importStructure(List<StructureModelVo> structureCollection,
                                                   List<PositionModelVo> positionCollection);

    /**
     * Page 查询
     * @param baseQuery 查询
     * @return
     */
    PageResponse<StructureModelCO> selectStructurePages(BaseQuery baseQuery);

    /**
     * 根据ID 查询
     * @param id
     * @return
     */
    SingleResponse<StructureModelCO> selectStructureById(Integer id);

    /**
     * 根据结构物code查询
     * @param code
     * @return
     */
    SingleResponse<StructureModelCO> selectStructureByCode(String code);
}
