package com.bwsensing.com.domain;


import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import org.junit.runner.RunWith;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.util.MockUtil;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import www.bwsensing.com.domain.device.model.alert.AlertParam;


@PowerMockIgnore
@RunWith(PowerMockRunner.class)
@PrepareForTest({MockUtil.class,AlertParamTest.class})
@Slf4j
/**
 * @author macos-zyj
 */
public class AlertParamTest {
    private static final String ALERT_EXPR_EXPECT = "max(data_value) > 80 || min(data_value) < 10";
    @Test
    public void testParamAlertExpr(){
        AlertParam param= new AlertParam();
        param.setFormulas(new ArrayList<>());
        param.getFormulas().add("max,>,80,||");
        param.getFormulas().add("min,<,10");
        Assert.assertEquals("预警计算表达式实例化有误!", ALERT_EXPR_EXPECT,param.toAlertExpr());
    }

}
