package www.bwsensing.com.domain.device.model;

import lombok.Data;
import java.util.Date;
import java.util.List;
import www.bwsensing.com.domain.common.InteractionMode;
import www.bwsensing.com.domain.monitor.model.MonitorPrototype;
import www.bwsensing.com.domain.device.model.manufacturer.ProductManufacturer;
/**
 * 传感器型号
 * @author macos-zyj
 */
@Data
public class SensorModel {
    /**主键**/
    private Integer id;

    /**模型名称*/
    private String modelName;

    /**是非为北微产品*/
    private Boolean isInner;

    /**产品品牌**/
    private ProductManufacturer manufacturer;

    /**模型路劲**/
    private String modelKind;

    /**型号图片*/
    private String picture;

    /**说明**/
    private String comment;

    /**监控类型*/
    private List<MonitorPrototype> prototypeList;

    /**交互模式*/
    private InteractionMode interactionMode;

    /**版本*/
    private Double version;

    /**创建时间*/
    private Date createTime;

    /**是否有效**/
    private boolean isEffective;

    public  void  create(){
        this.version = 0.01;
        this.createTime = new Date();
        this.isEffective = true;
    }
}
