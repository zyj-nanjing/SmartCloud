package www.bwsensing.com.common.config;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Objects;

/**
 * @author macos-zyj
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return Response.buildFailure("PARAMS_VALID_ERROR", Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * Biz异常
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Response bizExceptionHandle(BizException e) {
        log.error(e.getMessage(), e);
        return Response.buildFailure(e.getErrCode(),e.getLocalizedMessage());
    }
}
