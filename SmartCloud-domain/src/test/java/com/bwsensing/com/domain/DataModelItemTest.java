package com.bwsensing.com.domain;


import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.util.MockUtil;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import www.bwsensing.com.domain.device.model.data.model.DataModelItem;

/**
 * @author macos-zyj
 */
@Slf4j
@PowerMockIgnore
@RunWith(PowerMockRunner.class)
@PrepareForTest({MockUtil.class,DataModelItemTest.class})
public class DataModelItemTest {

    private static final Double ASCII_TEST_EXPECT = 27.0;
    private static final Double HEX_NO_TRANSFORM_EXPECT = 20330.0;
    private static final Double HEX_TRANSFORM_EXPECT = 0.033;

    @Test
    public void testAsciiDataWithNoTransform(){
        DataModelItem  testItem = new DataModelItem();
        testItem.setDataName("X倾角");
        testItem.setNeedTransform(false);
        Assert.assertEquals("ASCII 10进制计算有误!", ASCII_TEST_EXPECT,testItem.mathCalculation("+027.0000"));
    }


    @Test
    public void testHexDataWithNoTransform(){
        DataModelItem  testItem = new DataModelItem();
        testItem.setDataName("X倾角");
        testItem.setNeedTransform(false);
        Assert.assertEquals("HEX 16进制 不含公式 计算有误!", HEX_NO_TRANSFORM_EXPECT,testItem.mathCalculation("4F6A",16));
    }

    @Test
    public void testHexDataWithTransform(){
        DataModelItem  testItem = new DataModelItem();
        testItem.setDataName("X倾角");
        testItem.setNeedTransform(true);
        testItem.setCalculationFormula("(#{data}-20000)/10000");
        testItem.setPlaceholder("#{data}");
        Assert.assertEquals("HEX 16进制 含公式 计算有误!", HEX_TRANSFORM_EXPECT,testItem.mathCalculation("4F6A",16));
    }

}
