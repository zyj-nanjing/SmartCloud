package www.bwsensing.com.domain.device.model.data;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author macos-zyj
 */
@Data
public class PointValue {

    private Integer id;

    /**
     * 设备ID，同MySQl中等 设备ID 一致
     */
    private Long deviceId;

    /**
     * 位号ID，同MySQl中等 位号ID 一致
     */
    private Integer pointId;

    /**
     * 处理值，进行过缩放、格式化等操作
     */
    private String value;

    /**
     * 原始值
     */
    private String rawValue;

    /**
     * 计算值
     */
    private Object calculateValue;

    private List<PointValue> children;

    private Short raw;

    private String unit;

    private String type;

    private Integer timeOut = 15;

    private TimeUnit timeUnit = TimeUnit.MINUTES;

    private Boolean multi;

    private Date originTime;

    private Date createTime;
}
