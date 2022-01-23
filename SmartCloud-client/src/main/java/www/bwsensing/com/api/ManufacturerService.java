package www.bwsensing.com.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.command.ManufacturerSaveCmd;
import www.bwsensing.com.dto.command.ManufacturerUpdateCmd;
import www.bwsensing.com.dto.clientobject.ManufacturerCO;
import www.bwsensing.com.dto.command.query.ManufacturerSortQuery;

/**
 * @author macos-zyj
 */
public interface ManufacturerService {
    /**
     * 保存
     * @param saveCmd
     * @return
     */
    Response saveManufacturer(ManufacturerSaveCmd saveCmd);

    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response updateManufacturer(ManufacturerUpdateCmd updateCmd);

    /**
     * 查询根据编号获取厂商
     * @param id
     * @return
     */
    SingleResponse<ManufacturerCO> selectManufacturerById(Integer id);

    /**
     * 下拉框选择
     * @return
     */
    MultiResponse<ManufacturerCO> selectManufactureShow();

    /**
     * 条件查询
     * @param pageQuery
     * @return
     */
    PageResponse<ManufacturerCO> selectManufactureBySort(ManufacturerSortQuery pageQuery);
}
