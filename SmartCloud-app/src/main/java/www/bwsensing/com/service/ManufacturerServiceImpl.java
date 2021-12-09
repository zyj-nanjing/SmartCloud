package www.bwsensing.com.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.IManufacturerService;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.convertor.ManufacturerCoConvertor;
import www.bwsensing.com.domain.device.manufacturer.ProductManufacturer;
import www.bwsensing.com.domain.gateway.ManufacturerGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.clientobject.ManufacturerCO;
import www.bwsensing.com.dto.command.ManufacturerSaveCmd;
import www.bwsensing.com.dto.command.ManufacturerUpdateCmd;
import www.bwsensing.com.dto.command.query.ManufacturerSortQuery;
import www.bwsensing.com.gatewayimpl.database.ProductManufacturerMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.ProductManufacturerDO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class ManufacturerServiceImpl implements IManufacturerService {
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private ManufacturerGateway manufacturerGateway;
    @Resource
    private ProductManufacturerMapper manufacturerMapper;

    @Override
    public Response saveManufacturer(ManufacturerSaveCmd saveCmd) {
        permissionValid();
        ProductManufacturer manufacturer = new ProductManufacturer();
        BeanUtils.copyProperties(saveCmd,manufacturer);
        manufacturerGateway.saveManufacturer(manufacturer);
        return Response.buildSuccess();
    }

    @Override
    public Response updateManufacturer(ManufacturerUpdateCmd updateCmd) {
        permissionValid();
        ProductManufacturer manufacturer = new ProductManufacturer();
        BeanUtils.copyProperties(updateCmd,manufacturer);
        manufacturerGateway.updateManufacturer(manufacturer);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<ManufacturerCO> selectManufacturerById(Integer id) {
        Assert.notNull(id,"编号不能为空");
        permissionValid();
        ProductManufacturerDO manufacturerDo = manufacturerMapper.selectManufacturerById(id);
        if (null != manufacturerDo){
            return SingleResponse.of(ManufacturerCoConvertor.toClientObject(manufacturerDo));
        } else {
            return SingleResponse.of(new ManufacturerCO());
        }
    }

    @Override
    public MultiResponse<ManufacturerCO> selectManufactureShow() {
        permissionValid();
        List<ProductManufacturerDO> manufacturers = manufacturerMapper.selectManufacturesBySort(new ProductManufacturerDO());
        return MultiResponse.of(ManufacturerCoConvertor.toClientObjectCollection(manufacturers));
    }

    @Override
    public PageResponse<ManufacturerCO> selectManufactureBySort(ManufacturerSortQuery pageQuery) {
        permissionValid();
        PageHelper.startPage(pageQuery.getPageIndex(), pageQuery.getPageSize());
        ProductManufacturerDO querySortQuery = new ProductManufacturerDO();
        BeanUtils.copyProperties(pageQuery,querySortQuery);
        List<ProductManufacturerDO> manufacturers = manufacturerMapper.selectManufacturesBySort(querySortQuery);
        PageInfo<ProductManufacturerDO> pageInfo = new PageInfo<>(manufacturers);
        List<ManufacturerCO> result = ManufacturerCoConvertor.toClientObjectCollection(manufacturers);
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }

    private void permissionValid(){
        TokenData tokens = tokenGateway.getTokenInfo();
        if (!tokens.getRole().equals(RoleConstant.ROOT_ADMIN)){
            throw new BizException("NO_PERMISSION_VIEW","无权访问");
        }
    }
}
