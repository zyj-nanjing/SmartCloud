package com.bwsensing.com.domain;

import java.util.Map;
import java.util.List;
import org.junit.Test;
import java.util.HashMap;
import java.sql.Timestamp;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.mockito.internal.util.MockUtil;
import org.powermock.modules.junit4.PowerMockRunner;
import www.bwsensing.com.domain.device.model.data.model.*;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import www.bwsensing.com.domain.device.model.DataItemSourceKind;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import www.bwsensing.com.domain.device.model.ExtraProductDataItem;

/**
 * @author macos-zyj
 */
@PowerMockIgnore
@RunWith(PowerMockRunner.class)
@PrepareForTest({MockUtil.class,DataComputationModelTest.class})
@Slf4j
public class DataComputationModelTest {
    private static final String DATA_FORMAT ="^\\d{{size}}$";

    private static final Integer LENGTH_WITH_ACC = 9;

    private static final List<ExtraProductDataItem> EXTRA_DATA_ITEMS =  new ArrayList<>();

    private static final Map<String, ProductDataItem> DATA_ITEM_MAP = new HashMap<>();

    private static final ProductDataModel ASCII_WITH_ACC = new ProductDataModel(10, ",","");

    public static  List<ProductDataItem> accProducts = new ArrayList<>();

    static {
        accProducts.add(new ProductDataItem("x_ang"));
        accProducts.add(new ProductDataItem("y_ang"));
        accProducts.add(new ProductDataItem("z_ang"));
        accProducts.add(new ProductDataItem("x_acc"));
        accProducts.add(new ProductDataItem("y_acc"));
        accProducts.add(new ProductDataItem("z_acc"));
        accProducts.add(new ProductDataItem("temp"));
        accProducts.add(new ProductDataItem("elect"));
        List<DataModelItem> asciiItemsWithAcc = new ArrayList<>();
        asciiItemsWithAcc.add(new DataModelItem(9,0,1, DATA_FORMAT, DataType.STRING));
        for(int i=1;i<LENGTH_WITH_ACC;i++){
            asciiItemsWithAcc.add(new DataModelItem(accProducts.get(i-1),i,1,DataType.DOUBLE));
        }
        ASCII_WITH_ACC.setExpectDataSize(8);
        ASCII_WITH_ACC.setDataForm(DataForm.SINGLE_LINE_DATA);
        asciiItemsWithAcc.add(new DataModelItem(9, DataItemKind.IDENTIFY_CODE,DataType.INT));
        ASCII_WITH_ACC.setDataItems(asciiItemsWithAcc);
        EXTRA_DATA_ITEMS.add(new ExtraProductDataItem("高度","high","10","m",false,"",""));
        DATA_ITEM_MAP.put("x_ang",new ProductDataItem("X轴倾角","x_ang","°", DataItemSourceKind.DIRECT_DATA,true,"3.14159265358979323846*{data}/180","{data}"));
        DATA_ITEM_MAP.put("y_ang",new ProductDataItem("Y轴倾角","y_ang","°", DataItemSourceKind.DIRECT_DATA,true,"3.14159265358979323846*{data}/180","{data}"));
    }


    @Test
    public void testComputation(){
        String testTime  = "2022-04-02 15:36:58.512";
        DataComputationModel dataModel = new DataComputationModel();
        dataModel.setComputationDataId("displacement");
        List<String> dataIds = new ArrayList<>();
        dataIds.add("x_ang");
        dataIds.add("y_ang");
        dataModel.setDataIds(dataIds);
        dataModel.setExtraProductDataItems(EXTRA_DATA_ITEMS);
        dataModel.setDataItemMap(DATA_ITEM_MAP);
        dataModel.setComputationKind(ComputationKind.SUBMIT_CALCULATION);
        dataModel.setComputationFormula("{high}*sqrt(sin({x_ang})*sin({x_ang})+sin({y_ang})*sin({y_ang}))");
        String testAccAscii = "100000005,+003.4982,+000.5570,+086.4531,+000.0601,+000.0097,+000.9823,+027.0000,9,1440033931560";
        System.out.println(dataModel.computationDataCollection("100000005",ASCII_WITH_ACC.messageAnalyse(testAccAscii, Timestamp.valueOf(testTime)).getDataCollection()));
    }
}
