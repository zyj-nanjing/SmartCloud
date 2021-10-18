package www.bwsensing.com.common.exception;

/**
 * @author ytt
 */
public interface ErrorCodeI {
    /**
     * 获取错误码
     * @return
     */
    String getErrCode();

    /**
     * 获取描述
     * @return
     */
    String getErrDesc();
}
