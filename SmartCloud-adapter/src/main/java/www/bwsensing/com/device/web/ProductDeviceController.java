package www.bwsensing.com.device.web;

import javax.validation.Valid;
import io.jsonwebtoken.lang.Assert;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import www.bwsensing.com.device.dto.command.*;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.device.dto.clientobject.*;
import www.bwsensing.com.device.api.ProductDeviceService;
import org.springframework.validation.annotation.Validated;
import www.bwsensing.com.device.api.DeviceComputationService;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.dto.command.query.ProductSortQuery;
import www.bwsensing.com.device.dto.command.query.FacilityReceivePageQuery;



/**
 * 设备信息
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "设备管理")
@RequestMapping("/api/v1.0/device")
@RestController
public class ProductDeviceController {
    @Autowired
    private ProductDeviceService productDeviceService;
    @Autowired
    private DeviceComputationService deviceComputationService;

    @ApiOperation("设备查询(分页/条件查询)")
    @PostMapping("/query/sort")
    public PageResponse<ProductDeviceCO> queryProductBySort(@RequestBody ProductSortQuery productSortQuery){
        return productDeviceService.queryProductByPageSort(productSortQuery);
    }

    @ApiOperation("设备查询(条件查询)")
    @PostMapping("/total/query/sort")
    public MultiResponse<ProductDeviceCO> getAllProductBySort(@RequestBody ProductSortQuery productSortQuery){
        return productDeviceService.queryProductsBySort(productSortQuery);
    }

    @ApiOperation("设备上报日志查询(分页/条件查询)")
    @PostMapping("/log/query")
    public PageResponse<FacilityReceiveCO> queryFacilitySends(@Valid @RequestBody FacilityReceivePageQuery receivePageQuery){
        return productDeviceService.queryFacilitySendsByUniqueCode(receivePageQuery);
    }

    @ApiOperation("设备与项目测点绑定")
    @PostMapping("/bind/save")
    public Response projectBindWithProduct(@Valid @RequestBody ProjectBindWithProductCmd projectBindCmd){
        return productDeviceService.productBindWithProject(projectBindCmd);
    }

    @ApiOperation("获取当前测点下可以绑定的设备(未绑定)")
    @GetMapping("/bind/query/{positionId}")
    public MultiResponse<ProductBindStatusCO> getProjectBindWithProducts(@PathVariable Integer positionId){
        return productDeviceService.getProductBindStatusWithPositionId(positionId);
    }

    @ApiOperation("获取项目下的所有设备")
    @GetMapping("/project/query/{projectId}")
    public MultiResponse<ProductDeviceCO> getProductsByProjectId(@PathVariable Integer projectId){
        return productDeviceService.queryProductsByProjectId(projectId);
    }

    @ApiOperation("主页设备定位接口")
    @GetMapping("/index/map/show")
    public MultiResponse<ProductMapDetailCO> showProductDeviceInMap(){
        return productDeviceService.getProductsWithIndexMap();
    }

    @ApiOperation("创建设备")
    @PostMapping("/save")
    public Response saveProductDevice(@Valid @RequestBody ProductSaveCmd saveCmd){
        return productDeviceService.saveProduct(saveCmd);
    }

    @ApiOperation("修改设备信息")
    @PostMapping("/update")
    public Response updateProductDevice(@Valid @RequestBody ProductUpdateCmd updateCmd){
        return productDeviceService.updateProduct(updateCmd);
    }

    @ApiOperation("根据ID获取设备详细信息")
    @GetMapping("/query/{id}")
    public SingleResponse<ProductDeviceCO> queryProductDeviceById(@PathVariable Integer id){
        Assert.notNull(id, "设备编号不能为空!");
        return productDeviceService.queryProductDetailById(id);
    }

    @ApiOperation("根据ID删除设备")
    @GetMapping("/delete/{id}")
    public Response deleteProductDevice(@PathVariable Integer id){
        return productDeviceService.deleteProduct(id);
    }

    @ApiOperation("根据ID删除设备")
    @GetMapping("/item/query/{uniqueCode}")
    public MultiResponse<ProductDataItemCO> getProductDataItemsByUniqueCode(@PathVariable String uniqueCode){
        return productDeviceService.getProductDataItemsByUniqueCode(uniqueCode);
    }

    @ApiOperation("添加设备与计算模型关联")
    @PostMapping("/computation/save")
    public  Response addDeviceComputation(@Valid @RequestBody DeviceComputationSaveCmd saveCmd){
        return deviceComputationService.addDeviceComputation(saveCmd);
    }

    @ApiOperation("修改设备与计算模型关联")
    @PostMapping("/computation/update")
    public  Response addDeviceComputation(@Valid @RequestBody DeviceComputationUpdateCmd updateCmd){
        return deviceComputationService.updateDeviceComputation(updateCmd);
    }

}
