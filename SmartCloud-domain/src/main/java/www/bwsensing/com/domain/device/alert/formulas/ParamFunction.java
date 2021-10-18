package www.bwsensing.com.domain.device.alert.formulas;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */
public enum ParamFunction {
    /**平均*/
    AVG("avg"),
    MAX("max"),
    MIN("min");
    /**函数名称*/
    private final String name;

    ParamFunction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ParamFunction getParamFunction(String value){
        for (ParamFunction param:values()){
            if (param.getName().equals(value)){
                return param;
            }
        }
        throw new BizException("PARAM_FUNCTION_NOT_FOUND","该函数不支持");
    }
}
