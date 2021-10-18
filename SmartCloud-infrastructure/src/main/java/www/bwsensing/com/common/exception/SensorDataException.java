package www.bwsensing.com.common.exception;

import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zzk sjt
 */
@Slf4j
public class SensorDataException extends BizException {


    public SensorDataException(SensorDataErrorCode errorCode) {
        super(errorCode.getErrCode(),errorCode.getErrDesc());
    }

    public SensorDataException(String message) {
        super(message);
    }
}
