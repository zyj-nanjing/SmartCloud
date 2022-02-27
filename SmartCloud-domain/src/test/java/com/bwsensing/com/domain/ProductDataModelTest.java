package com.bwsensing.com.domain;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import org.junit.runner.RunWith;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.util.MockUtil;
import org.powermock.modules.junit4.PowerMockRunner;
import www.bwsensing.com.domain.device.model.data.model.DataItemKind;
import www.bwsensing.com.domain.device.model.data.model.DataModelItem;
import www.bwsensing.com.domain.device.model.data.model.DataType;
import www.bwsensing.com.domain.monitor.model.MonitorPrototype;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import www.bwsensing.com.domain.device.model.data.model.ProductDataModel;



@PowerMockIgnore
@RunWith(PowerMockRunner.class)
@PrepareForTest({MockUtil.class,ProductDataModelTest.class})
@Slf4j
public class ProductDataModelTest {

    private static final String dataFormat="^\\d{{size}}$";

    private static final ProductDataModel asciiWithNoAcc = new ProductDataModel(10, ",");

    private static final ProductDataModel asciiWithAcc = new ProductDataModel(10, ",");

    public static final ProductDataModel hexModel = new ProductDataModel(16,2);

    public static  List<MonitorPrototype> noAccProducts = new ArrayList<>();

    public static  List<MonitorPrototype> accProducts = new ArrayList<>();

    private static final String ASCII_NO_ACC_EXPECT ="MonitorReceive(sn=100000005, channelId=null, ip=null, receiveTime=null, " +
            "phoneNumber=null, electricity=null, temperature=null, receiveSize=1, sendCount=4, receiveMessage=null, " +
            "totalSize=null, dataCollection=[MonitorData(groupId=null, dataId=x_ang, dataIdValue=-5.833, " +
            "sn=100000005, type=null, timeStamp=null), MonitorData(groupId=null, dataId=y_ang, dataIdValue=10.223, " +
            "sn=100000005, type=null, timeStamp=null), MonitorData(groupId=null, dataId=temp, dataIdValue=21.237, " +
            "sn=100000005, type=null, timeStamp=null), MonitorData(groupId=null, dataId=elect, dataIdValue=9.0, " +
            "sn=100000005, type=null, timeStamp=null)])";

    private static final String ASCII_ACC_EXPECT = "MonitorReceive(sn=100000005, channelId=null, ip=null, receiveTime=null, " +
            "phoneNumber=1440033931560, electricity=null, temperature=null, receiveSize=1, sendCount=8, receiveMessage=null, " +
            "totalSize=null, dataCollection=[MonitorData(groupId=null, dataId=x_ang, dataIdValue=3.4982, sn=100000005, type=null, " +
            "timeStamp=null), MonitorData(groupId=null, dataId=y_ang, dataIdValue=0.557, sn=100000005, type=null, timeStamp=null), " +
            "MonitorData(groupId=null, dataId=z_ang, dataIdValue=86.4531, sn=100000005, type=null, timeStamp=null), " +
            "MonitorData(groupId=null, dataId=x_acc, dataIdValue=0.0601, sn=100000005, type=null, timeStamp=null), " +
            "MonitorData(groupId=null, dataId=y_acc, dataIdValue=0.0097, sn=100000005, type=null, timeStamp=null), " +
            "MonitorData(groupId=null, dataId=z_acc, dataIdValue=0.9823, sn=100000005, type=null, timeStamp=null), " +
            "MonitorData(groupId=null, dataId=elect, dataIdValue=27.0, sn=100000005, type=null, timeStamp=null), " +
            "MonitorData(groupId=null, dataId=temp, dataIdValue=9.0, sn=100000005, type=null, timeStamp=null)])";

    static {
        noAccProducts.add(new MonitorPrototype("x_ang"));
        noAccProducts.add(new MonitorPrototype("y_ang"));
        noAccProducts.add(new MonitorPrototype("temp"));
        noAccProducts.add(new MonitorPrototype("elect"));
        accProducts.add(new MonitorPrototype("x_ang"));
        accProducts.add(new MonitorPrototype("y_ang"));
        accProducts.add(new MonitorPrototype("z_ang"));
        accProducts.add(new MonitorPrototype("x_acc"));
        accProducts.add(new MonitorPrototype("y_acc"));
        accProducts.add(new MonitorPrototype("z_acc"));
        accProducts.add(new MonitorPrototype("elect"));
        accProducts.add(new MonitorPrototype("temp"));
    }

    static {
        List<DataModelItem> asciiItemsWithNoAcc = new ArrayList<>();
        asciiItemsWithNoAcc.add(new DataModelItem(9,0,1,dataFormat, DataType.STRING));
        for(int i=1;i<5;i++){
            asciiItemsWithNoAcc.add(new DataModelItem(noAccProducts.get(i-1),i,1,DataType.DOUBLE));
        }
        asciiWithNoAcc.setDataItems(asciiItemsWithNoAcc);
        List<DataModelItem> asciiItemsWithAcc = new ArrayList<>();
        asciiItemsWithAcc.add(new DataModelItem(9,0,1,dataFormat, DataType.STRING));
        for(int i=1;i<9;i++){
            asciiItemsWithAcc.add(new DataModelItem(accProducts.get(i-1),i,1,DataType.DOUBLE));
        }
        asciiItemsWithAcc.add(new DataModelItem(9, DataItemKind.IDENTIFY_CODE,DataType.INT));
        asciiWithAcc.setDataItems(asciiItemsWithAcc);
    }

    @Test
    public void testProductDataModelTransform(){
        String testNoAccAscii = "100000005,-005.8330,+010.2230,+021.2370,9";
        Assert.assertEquals("BW ASCII 不含加速度解析错误", ASCII_NO_ACC_EXPECT,asciiWithNoAcc.messageAnalyse(testNoAccAscii).toString());
        String testAccAscii = "100000005,+003.4982,+000.5570,+086.4531,+000.0601,+000.0097,+000.9823,+027.0000,9,1440033931560";
        Assert.assertEquals("BW ASCII 含加速度解析错误", ASCII_ACC_EXPECT,asciiWithAcc.messageAnalyse(testAccAscii).toString());
    }
}
