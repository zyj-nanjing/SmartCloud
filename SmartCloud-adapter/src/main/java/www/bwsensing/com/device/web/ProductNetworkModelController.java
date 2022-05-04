package www.bwsensing.com.device.web;

import javax.validation.Valid;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.api.ProductNetworkModelService;
import www.bwsensing.com.device.dto.clientobject.ProductNetworkModelCO;
import www.bwsensing.com.device.dto.command.ProductNetworkModelSaveCmd;
import www.bwsensing.com.device.dto.command.ProductNetworkModelUpdateCmd;
import www.bwsensing.com.device.dto.command.query.ProductNetworkModelPageQuery;


/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "产品网络模型管理")
@RequestMapping("/api/v1.0/product/network/model")
@RestController
public class ProductNetworkModelController {
    @Autowired
    private ProductNetworkModelService productNetworkModelService;

    @ApiOperation("新增产品网络模型")
    @PostMapping("/save")
    public Response addNetworkModel(@Valid @RequestBody ProductNetworkModelSaveCmd saveCmd){
        return productNetworkModelService.addNetworkModel(saveCmd);
    }

    @ApiOperation("修改产品网络模型")
    @PostMapping("/update")
    public Response updateNetworkModel(@Valid @RequestBody ProductNetworkModelUpdateCmd updateCmd){
        return productNetworkModelService.updateNetworkModel(updateCmd);
    }


    @ApiOperation("删除产品网络模型")
    @GetMapping("/delete/{id}")
    public Response deleteNetworkModel(@PathVariable Integer id){
        return productNetworkModelService.deleteNetworkModelById(id);
    }

    @ApiOperation("分页查询产品网络模型")
    @PostMapping("/page/query")
    public PageResponse<ProductNetworkModelCO> queryProductModelNetworkBySort(@Valid @RequestBody ProductNetworkModelPageQuery pageQuery){
        return productNetworkModelService.queryProductModelNetworkBySort(pageQuery);
    }

    @ApiOperation("查询产品网络模型")
    @GetMapping("/{id}")
    public SingleResponse<ProductNetworkModelCO> getNetworkModel(@PathVariable Integer id){
        return productNetworkModelService.getNetworkModelById(id);
    }
}
