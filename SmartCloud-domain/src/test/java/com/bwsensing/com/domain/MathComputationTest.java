package com.bwsensing.com.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.util.MockUtil;
import org.powermock.modules.junit4.PowerMockRunner;
import www.bwsensing.com.domain.common.math.MathCalculatorUtil;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;

/***
 * @author macos-zyj
 */
@Slf4j
@PowerMockIgnore
@RunWith(PowerMockRunner.class)
@PrepareForTest({MockUtil.class,MathComputationTest.class})
public class MathComputationTest {

    @Test
    public void testCalculator(){
        long beginTime = System.currentTimeMillis();
        String k ="-(2.5)*(-1)+(-1)*2";
        String k2 ="(-2.5)*(-2)+(-1)*(-2)-2*3";
        String re2 = MathCalculatorUtil.calculator(k2);
        String ss22 = "1+1*2+(10-(2*(5-3)*(2-1))-4)+10/(5-0) + log(10)/9 + 2*log(30)+ max(sin(2*(5+2))*6,pow(2*(2+8),3+2)) * max(-1+3,(5-4)) + min(sin(10), sin(20))";
        String re0 = MathCalculatorUtil.calculator(k);
        String re22 = MathCalculatorUtil.calculator(ss22);
        System.out.println("-(2.5)*(-1)+(-1)*2="+re0);
        System.out.println("(-2.5)*(-2)+(-1)*(-2)-2*3="+re2);
        System.out.println("1+1*2+(10-(2*(5-3)*(2-1))-4)+10/(5-0) + log(10)/9 + 2*log(30)+ max(sin(2*(5+2))*6,pow(2*(2+8),3+2)) * max(-1+3,(5-4)) + min(sin(10), sin(20))="+re22);
        System.out.println("cost时间：" + (System.currentTimeMillis() - beginTime) + "ms");
    }

    @Test
    public void testDisplacement(){
        String formula = "10*sqrt(sin(30)*sin(30)+sin(60)*sin(60))";
        System.out.println(MathCalculatorUtil.calculator(formula));
    }

    @Test
    public void testHeight(){
        String formula = "sin(0.5235987755982989)";
        System.out.println(MathCalculatorUtil.calculator(formula));
        double aaa = Math.pow(Math.cos(Double.parseDouble("30")), 2);
        double bbb = Math.pow(Math.cos(Double.parseDouble("60")), 2);
        System.out.println(aaa + bbb);
    }
}
