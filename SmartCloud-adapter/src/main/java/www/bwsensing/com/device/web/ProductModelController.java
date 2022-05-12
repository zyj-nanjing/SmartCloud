package www.bwsensing.com.device.web;

import javax.validation.Valid;
import io.swagger.annotations.Api;
import com.alibaba.excel.EasyExcel;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import www.bwsensing.com.device.dto.command.*;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.device.export.ProductModelVO;
import org.springframework.web.multipart.MultipartFile;
import www.bwsensing.com.device.api.ProductModelService;
import www.bwsensing.com.device.api.ProductDataItemService;
import org.springframework.validation.annotation.Validated;
import www.bwsensing.com.common.clientobject.ImportResultCO;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.dto.clientobject.ProductKindCO;
import www.bwsensing.com.device.dto.clientobject.ProductModelCO;
import www.bwsensing.com.device.api.ExtraProductDataItemService;
import www.bwsensing.com.device.listener.ProductModelDataListener;
import www.bwsensing.com.device.dto.clientobject.ProductDataItemCO;
import www.bwsensing.com.device.dto.clientobject.ExtraProductDataItemCO;
import www.bwsensing.com.device.dto.command.query.ProductDataItemPageQuery;
import www.bwsensing.com.device.dto.command.query.ProductExtraDataItemPageQuery;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "产品型号管理")
@RequestMapping("/api/v1.0/product/model")
@RestController
public class ProductModelController {
    @Autowired
    private ProductModelService productModelService;
    @Autowired
    private ProductDataItemService productDataItemService;
    @Autowired
    private ExtraProductDataItemService extraProductDataItemService;

    @ApiOperation("新增产品型号")
    @PostMapping("/save")
    public Response saveModel(@Valid @RequestBody ProductModelSaveCmd saveCmd){
        return  productModelService.saveModel( saveCmd);
    }


    @ApiOperation("修改产品型号")
    @PostMapping("/update")
    public Response updateModel(@Valid @RequestBody ProductModelUpdateCmd updateCmd){
        return  productModelService.updateModel(updateCmd);
    }

    @ApiOperation("获取产品类型")
    @PostMapping("/kinds")
    public MultiResponse<ProductKindCO> getProductModelKinds(){
        return  productModelService.getProductModelKinds();
    }

    /**
     * Excel Test
     * @param file 文件流
     * @return
     * @throws Exception
     */
    @ApiOperation("导入产品型号")
    @PostMapping("/importData")
    public SingleResponse<ImportResultCO>  importData(@RequestParam MultipartFile file) throws Exception
    {
        ProductModelDataListener dataListener = new ProductModelDataListener(productModelService);
        EasyExcel.read(file.getInputStream(), ProductModelVO.class, dataListener).sheet().doRead();
        return SingleResponse.of(dataListener.getImportResult());
    }

    @ApiOperation("根据ID删除产品型号")
    @GetMapping("/delete/{modelId}")
    public Response deleteModel(@PathVariable Integer modelId){
        return productModelService.deleteModel( modelId);
    }

    @ApiOperation("产品型号查询(非分页)")
    @GetMapping("/query")
    public MultiResponse<ProductModelCO> queryAllModels(){
        return productModelService.queryProductModels();
    }

    @ApiOperation("根据编号查询产品型号详细信息")
    @GetMapping("/detail/query/{modelId}")
    public SingleResponse<ProductModelCO> selectModelById(@PathVariable Integer modelId){
        return productModelService.selectModelById(modelId);
    }

    @ApiOperation("根据编号查询产品检测项列表")
    @PostMapping("/item/query/sort")
    public MultiResponse<ProductDataItemCO> getProductDataItemsByModelSort(@Valid @RequestBody ProductDataItemPageQuery pageQuery){
        return productDataItemService.getProductDataItemsByModelSort(pageQuery);
    }

    @ApiOperation("根据编号查询产品检测项列表")
    @GetMapping("/item/query/{modelId}")
    public MultiResponse<ProductDataItemCO> getProductDataItemsByModelId(@PathVariable Integer modelId){
        return productDataItemService.getProductDataItemsByModelId(modelId);
    }

    @ApiOperation("产品监测数据项分页查询")
    @PostMapping("/item/page/query")
    public PageResponse<ProductDataItemCO> getProductDataItemsByPage(@Valid @RequestBody ProductDataItemPageQuery pageQuery){
        return  productDataItemService.getProductDataItemsByPage(pageQuery);
    }

    @ApiOperation("获取产品监测数据项")
    @PostMapping("/item/detail/{id}")
    public SingleResponse<ProductDataItemCO> getProductDataItemById(@PathVariable  Integer id){
        return productDataItemService.getProductDataItemById(id);
    }


    @ApiOperation("保存产品监测数据项")
    @PostMapping("/item/save")
    public  Response  addProductDataItem(@Valid @RequestBody ProductDataItemSaveCmd saveCmd){
        return productDataItemService.addProductDataItem(saveCmd);
    }

    @ApiOperation("修改产品监测数据项")
    @PostMapping("/item/update")
    public Response updateProductDataItem(@Valid @RequestBody ProductDataItemUpdateCmd updateCmd){
        return productDataItemService.updateProductDataItem(updateCmd);
    }

    @ApiOperation("删除产品监测数据项")
    @PostMapping("/item/delete/{id}")
    public Response deleteProductDataItemById(@PathVariable Integer id){
        return productDataItemService.deleteProductDataItemById(id);
    }

    /**
     * 根据产品额外数据项列表
     * @param modelId
     * @return
     */
    @ApiOperation("根据编号查询产品额外数据项列表")
    @GetMapping("/extra/item/query/{modelId}")
    public MultiResponse<ExtraProductDataItemCO> getProductExtraDataItemsByModelId( @PathVariable Integer modelId){
        return extraProductDataItemService.getProductExtraDataItemsByModelId(modelId);
    }

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    @ApiOperation("产品额外数据项分页查询")
    @PostMapping("/extra/item/page/query")
    public PageResponse<ExtraProductDataItemCO> getProductExtraDataItemsByPage(@Valid @RequestBody ProductExtraDataItemPageQuery pageQuery){
        return extraProductDataItemService.getProductExtraDataItemsByPage(pageQuery);
    }

    /**
     * 根据Id进行查询
     * @param modelId
     * @return
     */
    @ApiOperation("根据编号查询产品额外数据项")
    @GetMapping("/extra/item/detail/query/{modelId}")
    public  SingleResponse<ExtraProductDataItemCO> getProductExtraDataItemById(@PathVariable Integer modelId){
        return extraProductDataItemService.getProductExtraDataItemById(modelId);
    }

    /**
     * 添加产品检测项
     * @param saveCmd
     * @return
     */
    @ApiOperation("保存产品额外数据项")
    @PostMapping("/extra/item/save")
    public Response addProductDataItem(@Valid @RequestBody ProductExtraDataItemSaveCmd saveCmd){
        return extraProductDataItemService.addProductDataItem(saveCmd);
    }


    /**
     * 修改产品检测项
     * @param updateCmd
     * @return
     */
    @ApiOperation("保存产品额外数据项")
    @PostMapping("/extra/item/update")
    public Response updateExtraProductDataItem(@Valid @RequestBody ProductExtraDataItemUpdateCmd updateCmd){
        return extraProductDataItemService.updateExtraProductDataItem(updateCmd);
    }


    /**
     * 根据Id删除产品检测项
     * @param id
     * @return
     */
    @ApiOperation("删除产品额外数据项")
    @PostMapping("/extra/item/delete/{id}")
    public Response deleteExtraProductDataItemById(@PathVariable Integer id){
        return extraProductDataItemService.deleteExtraProductDataItemById(id);
    }
}
