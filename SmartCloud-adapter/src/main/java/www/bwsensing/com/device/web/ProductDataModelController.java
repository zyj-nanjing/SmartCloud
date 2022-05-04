package www.bwsensing.com.device.web;

import javax.validation.Valid;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import www.bwsensing.com.device.api.ProductDataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.dto.command.ProductDataModelAddCmd;
import www.bwsensing.com.device.dto.clientobject.ProductDataModelCO;
import www.bwsensing.com.device.dto.command.ProductDataModelUpdateCmd;
import www.bwsensing.com.device.dto.command.ProductDataModelItemAddCmd;
import www.bwsensing.com.device.dto.clientobject.ProductDataModelItemCO;
import www.bwsensing.com.device.dto.command.ProductDataModelItemUpdateCmd;
import www.bwsensing.com.device.dto.command.query.ProductDataModelsPageQuery;
import www.bwsensing.com.device.dto.command.query.ProductDataModelItemsPageQuery;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "产品数据模型管理")
@RequestMapping("/api/v1.0/product/data/model")
@RestController
public class ProductDataModelController {

    @Autowired
    private ProductDataModelService productDataModelService;

    @ApiOperation("新增产品数据模型")
    @PostMapping("/save")
    public Response addProductModel(@Valid @RequestBody ProductDataModelAddCmd addCmd){
        return productDataModelService.addProductDateModel(addCmd);
    }

    @ApiOperation("修改产品数据模型")
    @PostMapping("/update")
    public Response updateProductModel(@Valid @RequestBody ProductDataModelUpdateCmd updateCmd){
        return productDataModelService.updateProductDateModel(updateCmd);
    }

    @ApiOperation("删除产品数据模型")
    @GetMapping("/delete/{id}")
    public Response deleteProductModelById(@PathVariable Integer id){
        return productDataModelService.deleteProductDateModel(id);
    }

    @ApiOperation("获取产品数据模型")
    @GetMapping("/view/{id}")
    public SingleResponse<ProductDataModelCO> getProductDataModelById(@PathVariable Integer id){
        return productDataModelService.getProductDataModelById(id);
    }

    @ApiOperation("产品数据模型分页查询")
    @PostMapping("/page/query")
    public PageResponse<ProductDataModelCO> getProductDataModelsByPageQuery(@RequestBody ProductDataModelsPageQuery pageQuery){
        return productDataModelService.getProductDataModelsByPageQuery(pageQuery);
    }



    @ApiOperation("新增产品解析数据项")
    @PostMapping("/item/save")
    public Response addProductDataItem(@Valid @RequestBody ProductDataModelItemAddCmd addCmd){
        return productDataModelService.addProductDataItem(addCmd);
    }


    @ApiOperation("修改产品解析数据项")
    @PostMapping("/item/update")
    public Response updateProductDataItem(@Valid @RequestBody ProductDataModelItemUpdateCmd addCmd){
        return productDataModelService.updateProductDataItem(addCmd);
    }

    @ApiOperation("删除产品解析数据项")
    @GetMapping("/item/delete/{id}")
    public Response deleteProductDataItemById(@PathVariable Integer id){
        return productDataModelService.deleteProductDataItemById(id);
    }

    @ApiOperation("产品解析数据项分页查询")
    @PostMapping("/item/page/query")
    public PageResponse<ProductDataModelItemCO> getProductDataModelItemsByPageQuery(@RequestBody ProductDataModelItemsPageQuery pageQuery){
        return productDataModelService.getProductDataModelItemsByPageQuery(pageQuery);
    }

    @ApiOperation("获取产品解析数据项")
    @GetMapping("/item/view/{id}")
    public SingleResponse<ProductDataModelItemCO> getProductDataModelItemById(@PathVariable Integer id){
        return productDataModelService.getProductDataModelItemById(id);
    }

}
