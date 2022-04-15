package www.bwsensing.com.domain.device.model.alert.formulas;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */
public enum ParamLogic {
    /**与运算*/
    AND("&&"),
    /**或运算*/
    OR("||"),
    /**按位与运算*/
    BITWISE_AND("&"),
    /**按位或运算*/
    BITWISE_OR("|");;
    /**函数名称*/
    private final String name;

    ParamLogic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ParamLogic getParamLogic(String value){
        for (ParamLogic param:values()){
            if (param.getName().equals(value)){
                return param;
            }
        }
        throw new BizException("PARAM_LOGIC_NOT_FOUND","该逻辑运算不支持");
    }

    public static void main(String[] args) {
        System.out.println(getParamLogic("||"));
    }
}
