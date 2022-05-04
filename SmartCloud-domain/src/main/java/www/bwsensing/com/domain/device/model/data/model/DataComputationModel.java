package www.bwsensing.com.domain.device.model.data.model;

import lombok.Data;
import java.util.*;
import java.sql.Timestamp;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import www.bwsensing.com.domain.device.model.data.MonitorData;
import www.bwsensing.com.domain.common.math.MathCalculatorUtil;
import www.bwsensing.com.domain.device.model.ExtraProductDataItem;

/**
 * 计算模型
 * @author macos-zyj
 */
@Data
public class DataComputationModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 计算模型名称
     */
    private String name;

    /**
     * 产品型号Id
     */
    private Integer modelId;

    /**
     * 数据项Id
     */
    private Integer dataItemId;

    /**
     * 计算数据编码
     */
    private String computationDataId;

    /**
     * 产品数据项
     */
    private ProductDataItem productDataItem;

    /**
     * 监测数据项
     */
    private List<ProductDataItem>  productDataItems;

    /**
     * 额外数据
     */
    private List<ExtraProductDataItem> extraProductDataItems;

    /**
     * 数据项编码占位符
     */
    private List<String> dataIds;

    /**
     * 计算触发类型
     */
    private ComputationKind computationKind;

    /**
     * 监测因素 key 时间戳
     */
    private Map<String, ProductDataItem> dataItemMap;

    /**
     * 公式名称
     */
    private String formulaName;

    /**
     * 计算公式
     */
    private String computationFormula;


    public boolean checkDataComputationIsLegal(){
        Assert.notEmpty(productDataItems,"PRODUCT_ITEM_NOT_EXIST","数据检测项未选择!");
        Assert.notNull(computationFormula,"COMPUTATION_FORMULA_NOT_EXIST","计算公式未填写!");
        initDataIds();
        String checkFormula ="";
        for (String dataId : dataIds){
            checkFormula = computationFormula.replace(getPlaceholder(dataId),"");
        }
        if(null != extraProductDataItems){
            for (ExtraProductDataItem dataItem : extraProductDataItems){
                checkFormula = computationFormula.replace(getPlaceholder(dataItem.getExtraDataId()),"");
            }
        }
        return !checkFormula.contains("{") && !checkFormula.contains("}");
    }


    public List<MonitorData> computationDataCollection(String uniqueCode,List<MonitorData> dataCollection){
        if(null != productDataItem){
            this.computationDataId = productDataItem.getDataId();
        }
        initDataIds();
        List<MonitorData> dataCollectionResult = new ArrayList<>();
        Map<Timestamp,List<MonitorData>>  itemsDataMap = new HashMap<>(dataIds.size());
        dataCollection.forEach(item -> {
            itemsDataMap.computeIfAbsent(item.getTimeStamp(), k -> new ArrayList<>());
            itemsDataMap.get(item.getTimeStamp()).add(item);
        });
        for (Timestamp ts : itemsDataMap.keySet()){
            List<MonitorData> currentCollections = itemsDataMap.get(ts);
            String currentComputationFormula = computationFormula;
            for (MonitorData current : currentCollections){
                if(dataIds.contains(current.getDataId())){
                    ProductDataItem dataItem = dataItemMap.get(current.getDataId());
                    currentComputationFormula = currentComputationFormula.
                            replace(getPlaceholder(current.getDataId()),dataItem.getDataCalculation(current.getDataIdValue()+""));
                }
            }
            if (null != extraProductDataItems){
                for (ExtraProductDataItem current : extraProductDataItems){
                    currentComputationFormula = currentComputationFormula.
                            replace(getPlaceholder(current.getExtraDataId()),current.getDataCalculation());
                }
            }
            MonitorData data = new MonitorData();
            data.setDataIdValue(Double.parseDouble(MathCalculatorUtil.calculator(currentComputationFormula)));
            data.setUniqueCode(uniqueCode);
            data.setDataId(this.computationDataId);
            if (computationKind.equals(ComputationKind.SUBMIT_CALCULATION)){
                data.setTimeStamp(ts);
            } else {
                data.setTimeStamp(new Timestamp(System.currentTimeMillis()));
            }
            dataCollectionResult.add(data);
        }
        return dataCollectionResult;
    }

    private void initDataIds() {
        if (null == dataIds){
            if (null  == productDataItems) {
                throw new BizException("DATA_ITEMS_NOT_EXIST","数据项不存在!");
            } else {
                dataIds = new ArrayList<>();
                productDataItems.forEach(item -> dataIds.add(item.getDataId()));
            }
        }
    }

    private String getPlaceholder(String dataId){
        return "{"+dataId+"}";
    }

}
