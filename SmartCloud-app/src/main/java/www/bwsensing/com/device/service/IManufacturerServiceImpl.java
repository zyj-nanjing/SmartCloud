package www.bwsensing.com.device.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.api.ManufacturerService;
import www.bwsensing.com.device.convertor.ManufacturerCoConvertor;
import www.bwsensing.com.domain.device.model.manufacturer.ProductManufacturer;
import www.bwsensing.com.domain.monitor.gateway.ManufacturerGateway;
import www.bwsensing.com.device.dto.command.ManufacturerSaveCmd;
import www.bwsensing.com.device.dto.command.ManufacturerUpdateCmd;
import www.bwsensing.com.device.dto.clientobject.ManufacturerCO;
import www.bwsensing.com.device.dto.command.query.ManufacturerSortQuery;
import www.bwsensing.com.device.gatewayimpl.database.ProductManufacturerMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductManufacturerDO;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class IManufacturerServiceImpl implements ManufacturerService {
    @Resource
    private ManufacturerGateway manufacturerGateway;
    @Resource
    private ProductManufacturerMapper manufacturerMapper;

    @Override
    public Response saveManufacturer(ManufacturerSaveCmd saveCmd) {
        ProductManufacturer manufacturer = new ProductManufacturer();
        BeanUtils.copyProperties(saveCmd,manufacturer);
        manufacturerGateway.saveManufacturer(manufacturer);
        return Response.buildSuccess();
    }

    @Override
    public Response updateManufacturer(ManufacturerUpdateCmd updateCmd) {
        ProductManufacturer manufacturer = new ProductManufacturer();
        BeanUtils.copyProperties(updateCmd,manufacturer);
        manufacturerGateway.updateManufacturer(manufacturer);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<ManufacturerCO> selectManufacturerById(Integer id) {
        Assert.notNull(id,"编号不能为空");
        ProductManufacturerDO manufacturerDo = manufacturerMapper.selectManufacturerById(id);
        if (null != manufacturerDo){
            return SingleResponse.of(ManufacturerCoConvertor.toClientObject(manufacturerDo));
        } else {
            return SingleResponse.of(new ManufacturerCO());
        }
    }

    @Override
    public MultiResponse<ManufacturerCO> selectManufactureShow() {
        List<ProductManufacturerDO> manufacturers = manufacturerMapper.selectManufacturesBySort(new ProductManufacturerDO());
        return MultiResponse.of(ManufacturerCoConvertor.toClientObjectCollection(manufacturers));
    }

    @Override
    public PageResponse<ManufacturerCO> selectManufactureBySort(ManufacturerSortQuery pageQuery) {
        PageHelperUtils<ManufacturerSortQuery, ProductManufacturerDO> pageHelper =
                PageHelperUtils.<ManufacturerSortQuery, ProductManufacturerDO>builder()
                        .pageFunction((groupQuery)->manufacturerMapper.selectManufacturesBySort (initializeQuery(pageQuery))).build();
        PageInfo<ProductManufacturerDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<ManufacturerCO> result = ManufacturerCoConvertor.toClientObjectCollection(pageInfo.getList());
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex() );
    }

    private ProductManufacturerDO initializeQuery(ManufacturerSortQuery pageQuery){
        ProductManufacturerDO query = new ProductManufacturerDO();
        BeanUtils.copyProperties(pageQuery,query);
        return query;
    }
}
