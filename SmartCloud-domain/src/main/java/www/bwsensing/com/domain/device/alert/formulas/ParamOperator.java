package www.bwsensing.com.domain.device.alert.formulas;

import com.alibaba.cola.exception.BizException;
/**
 * 参数运算符号
 * @author macos-zyj
 */
public enum ParamOperator {
    /**大于*/
    GREATER(">"),
    GREATER_EQUAL(">="),
    LESS("<"),
    LESS_EQUAL("<="),
    EQUAL("=="),
    NOT_EQUAL("!=");
    /**操作符*/
    private final String name;

    ParamOperator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ParamOperator getParamOperator(String value){
        for (ParamOperator param:values()){
            if (param.getName().equals(value)){
                return param;
            }
        }
        throw new BizException("PARAM_OPERATOR_NOT_FOUND","该运算符不支持");
    }
}
