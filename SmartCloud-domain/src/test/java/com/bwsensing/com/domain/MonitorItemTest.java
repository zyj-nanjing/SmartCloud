package com.bwsensing.com.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.MockUtil;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import www.bwsensing.com.domain.device.model.ProductDataItem;

/**
 * @author macos-zyj
 */
@PowerMockIgnore
@RunWith(PowerMockRunner.class)
@PrepareForTest({MockUtil.class,MonitorItemTest.class})
@Slf4j
public class MonitorItemTest {

    @Test
    public void testItemTransformation() {
        ProductDataItem productDataItem = new ProductDataItem();
        productDataItem.setDataId("x_angle");
        productDataItem.setNeedTransform(true);
        productDataItem.setCalculationFormula("3.14159265358979323846*{data}/180");
        productDataItem.setPlaceholder("{data}");
        System.out.println(productDataItem.getDataCalculation("30"));
    }
}
