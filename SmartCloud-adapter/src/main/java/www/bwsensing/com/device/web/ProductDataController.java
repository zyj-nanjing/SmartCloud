package www.bwsensing.com.device.web;


import javax.validation.Valid;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.device.api.ProductDataService;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.dto.command.query.ProductDataTableQuery;
import www.bwsensing.com.device.dto.clientobject.ProductDynamicTableCO;


/**
 * 产品数据信息
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "产品数据")
@RequestMapping("/api/v1.0/sensor/data")
@RestController
public class ProductDataController {
    @Autowired
    private ProductDataService productDataService;


    @ApiOperation("动态表格查询")
    @PostMapping("/dynamic/table/query")
    public SingleResponse<ProductDynamicTableCO> sensorDynamicTableQuery(@Valid @RequestBody ProductDataTableQuery tableQuery){
        return productDataService.productDynamicTableQuery(tableQuery);
    }


}
