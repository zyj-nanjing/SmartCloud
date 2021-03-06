package com.bwsensing.com.domain;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import org.junit.runner.RunWith;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.util.MockUtil;
import org.powermock.modules.junit4.PowerMockRunner;
import www.bwsensing.com.domain.device.model.data.model.*;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;


/**
 * @author macos-zyj 
 */
@PowerMockIgnore
@RunWith(PowerMockRunner.class)
@PrepareForTest({MockUtil.class,ProductDataModelTest.class})
@Slf4j
public class ProductDataModelTest {

    private static final Integer LENGTH_WITH_NO_ACC = 5;
    private static final Integer LENGTH_WITH_ACC = 9;
    private static final Integer LENGTH_WITH_HEX = 8;

    private static final String DATA_FORMAT ="^\\d{{size}}$";

    private static final ProductDataModel ASCII_WITH_NO_ACC = new ProductDataModel(10, ",","");

    private static final ProductDataModel ASCII_WITH_ACC = new ProductDataModel(10, ",","");

    public static final ProductDataModel HEX_MODEL = new ProductDataModel(16,2,"");

    public static  List<ProductDataItem> noAccProducts = new ArrayList<>();

    public static  List<ProductDataItem> accProducts = new ArrayList<>();

    private static final String ASCII_NO_ACC_EXPECT ="MonitorReceive(sn=100000005, channelId=null, ip=null, receiveTime=null, " +
            "phoneNumber=null, electricity=null, temperature=null, receiveSize=1, sendCount=4, receiveMessage=null, totalSize=null, " +
            "dataCollection=[MonitorData(groupId=null, dataId=x_ang, dataIdValue=-5.833, sn=100000005, type=null, timeStamp=2022-04-02 15:36:58.512), " +
            "MonitorData(groupId=null, dataId=y_ang, dataIdValue=10.223, sn=100000005, type=null, timeStamp=2022-04-02 15:36:58.512), " +
            "MonitorData(groupId=null, dataId=temp, dataIdValue=21.237, sn=100000005, type=null, timeStamp=2022-04-02 15:36:58.512), " +
            "MonitorData(groupId=null, dataId=elect, dataIdValue=9.0, sn=100000005, type=null, timeStamp=2022-04-02 15:36:58.512)])";

    private static final String ASCII_ACC_EXPECT = "MonitorReceive(sn=100000005, channelId=null, ip=null, receiveTime=null, phoneNumber=1440033931560, " +
            "electricity=null, temperature=null, receiveSize=1, sendCount=8, receiveMessage=null, totalSize=null, dataCollection=[MonitorData(groupId=null, " +
            "dataId=x_ang, dataIdValue=3.4982, sn=100000005, type=null, timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=y_ang, " +
            "dataIdValue=0.557, sn=100000005, type=null, timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=z_ang, dataIdValue=86.4531, " +
            "sn=100000005, type=null, timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=x_acc, dataIdValue=0.0601, sn=100000005, type=null, " +
            "timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=y_acc, dataIdValue=0.0097, sn=100000005, type=null, timeStamp=2022-04-02 15:36:58.512), " +
            "MonitorData(groupId=null, dataId=z_acc, dataIdValue=0.9823, sn=100000005, type=null, timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=temp, " +
            "dataIdValue=27.0, sn=100000005, type=null, timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=elect, dataIdValue=9.0, sn=100000005," +
            " type=null, timeStamp=2022-04-02 15:36:58.512)])";

    private static final String HEX_ACC_EXPECT = "MonitorReceive(sn=000000001, channelId=null, ip=null, receiveTime=null, phoneNumber=null, electricity=null, temperature=null, " +
            "receiveSize=1, sendCount=8, receiveMessage=null, totalSize=null, dataCollection=[MonitorData(groupId=null, dataId=x_ang, dataIdValue=20191.0, sn=000000001, type=null, " +
            "timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=y_ang, dataIdValue=20064.0, sn=000000001, type=null, timeStamp=2022-04-02 15:36:58.512), " +
            "MonitorData(groupId=null, dataId=z_ang, dataIdValue=28798.0, sn=000000001, type=null, timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=x_acc, " +
            "dataIdValue=20330.0, sn=000000001, type=null, timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=y_acc, dataIdValue=20110.0, sn=000000001, type=null, " +
            "timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=z_acc, dataIdValue=29907.0, sn=000000001, type=null, timeStamp=2022-04-02 15:36:58.512), " +
            "MonitorData(groupId=null, dataId=temp, dataIdValue=22805.0, sn=000000001, type=null, timeStamp=2022-04-02 15:36:58.512), MonitorData(groupId=null, dataId=elect, " +
            "dataIdValue=9.0, sn=000000001, type=null, timeStamp=2022-04-02 15:36:58.512)])";

    static {
        ASCII_WITH_NO_ACC.setDataForm(DataForm.DOUBLE_LINE_DATA);
        ASCII_WITH_ACC.setDataForm(DataForm.SINGLE_LINE_DATA);
        HEX_MODEL.setDataForm(DataForm.SINGLE_LINE_DATA);
        noAccProducts.add(new ProductDataItem("x_ang"));
        noAccProducts.add(new ProductDataItem("y_ang"));
        noAccProducts.add(new ProductDataItem("temp"));
        noAccProducts.add(new ProductDataItem("elect"));
        accProducts.add(new ProductDataItem("x_ang"));
        accProducts.add(new ProductDataItem("y_ang"));
        accProducts.add(new ProductDataItem("z_ang"));
        accProducts.add(new ProductDataItem("x_acc"));
        accProducts.add(new ProductDataItem("y_acc"));
        accProducts.add(new ProductDataItem("z_acc"));
        accProducts.add(new ProductDataItem("temp"));
        accProducts.add(new ProductDataItem("elect"));
    }

    static {
        List<DataModelItem> asciiItemsWithNoAcc = new ArrayList<>();
        asciiItemsWithNoAcc.add(new DataModelItem(9,0,1, DATA_FORMAT, DataType.STRING));
        for(int i=1;i<LENGTH_WITH_NO_ACC;i++){
            asciiItemsWithNoAcc.add(new DataModelItem(noAccProducts.get(i-1),i,1,DataType.DOUBLE));
        }
        ASCII_WITH_NO_ACC.setDataItems(asciiItemsWithNoAcc);
        ASCII_WITH_NO_ACC.setExpectDataSize(4);
        List<DataModelItem> asciiItemsWithAcc = new ArrayList<>();
        asciiItemsWithAcc.add(new DataModelItem(9,0,1, DATA_FORMAT, DataType.STRING));
        for(int i=1;i<LENGTH_WITH_ACC;i++){
            asciiItemsWithAcc.add(new DataModelItem(accProducts.get(i-1),i,1,DataType.DOUBLE));
        }
        ASCII_WITH_ACC.setExpectDataSize(8);
        asciiItemsWithAcc.add(new DataModelItem(9, DataItemKind.IDENTIFY_CODE,DataType.INT));
        ASCII_WITH_ACC.setDataItems(asciiItemsWithAcc);
        List<DataModelItem> hexItemsWithAcc = new ArrayList<>();
        hexItemsWithAcc.add(new DataModelItem(9,0,1, DATA_FORMAT, DataType.INT));
        hexItemsWithAcc.add(new DataModelItem(1, DataItemKind.FUNCTION_CODE,DataType.INT,1));
        hexItemsWithAcc.add(new DataModelItem(2, DataItemKind.DATA_SIZE,DataType.INT,1));
        for(int i=0;i<LENGTH_WITH_HEX;i++){
            hexItemsWithAcc.add(new DataModelItem(accProducts.get(i),i*2+3,2,DataType.DOUBLE));
        }
        HEX_MODEL.setDataItems(hexItemsWithAcc);
        HEX_MODEL.setExpectDataSize(8);
    }

    @Test
    public void testProductDataModelTransform(){
        String testTime  = "2022-04-02 15:36:58.512";
        String testNoAccAscii = "100000005,-005.8330,+010.2230,+021.2370,9";
        Assert.assertEquals("BW ASCII ???????????????????????????", ASCII_NO_ACC_EXPECT, ASCII_WITH_NO_ACC.messageAnalyse(testNoAccAscii,Timestamp.valueOf(testTime)).toString());
        String testAccAscii = "100000005,+003.4982,+000.5570,+086.4531,+000.0601,+000.0097,+000.9823,+027.0000,9,1440033931560";
        Assert.assertEquals("BW ASCII ????????????????????????", ASCII_ACC_EXPECT, ASCII_WITH_ACC.messageAnalyse(testAccAscii,Timestamp.valueOf(testTime)).toString());
        String hexWithAcc ="01 03 10 4E DF 4E 60 70 7E 4F 6A 4E 8E 74 D3 59 15 00 09 9D E7";
        Assert.assertEquals("BW HEX ????????????????????????", HEX_ACC_EXPECT, HEX_MODEL.messageAnalyse(hexWithAcc,Timestamp.valueOf(testTime)).toString());
    }
}
