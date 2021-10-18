package www.bwsensing.com.common.exception;

/**
 * @author macos-zyj
 */
public enum SensorDataErrorCode implements ErrorCodeI{
    /** 无效数据 */
    INVALID_DATA("INVALID_DATA","无效数据"),
    /**数据长度过短*/
    DATA_LENGTH_NOT_ENOUGH("DATA_LENGTH_NOT_ENOUGH","数据长度过短"),
    /** 数据长度过长*/
    DATA_LENGTH_TOO_LONG("DATA_LENGTH_TOO_LONG","数据长度过长"),
    /** 错误数据 */
    ERROR_DATA("ERROR_DATA","错误数据"),
    /** 十六进制格式不正确*/
    HEX_INCORRECT_FORMAT("HEX_INCORRECT_FORMAT","十六进制格式不正确"),
    /**数据格式错误 */
    DATA_FORMAT_ERROR("DATA_FORMAT_ERROR","数据格式错误"),
    /** 角度解析异常*/
    ABNORMAL_ANGLE_ERROR("ABNORMAL_ANGLE_ERROR","角度解析异常"),
    /** 加速度解析异常 */
    ABNORMAL_ACCELERATION("ABNORMAL_ACCELERATION","加速度解析异常"),
    /**  温度异常 */
    ABNORMAL_TEMPERATURE("ABNORMAL_TEMPERATURE","温度异常");
    private final String errCode;
    private final String errDesc;

    private SensorDataErrorCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    @Override
    public String getErrCode() {
        return errCode;
    }

    @Override
    public String getErrDesc() {
        return errDesc;
    }
}
