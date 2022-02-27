package www.bwsensing.com.common.annotation;

import java.lang.annotation.*;

/**
 * @author macos-zyj
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClientStreamRegister {
    String pushType() default "mqtt";
}
