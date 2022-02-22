package www.bwsensing.com.device.export;

import com.alibaba.excel.annotation.ExcelProperty;
import java.util.Objects;
import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class SensorModelVO {
    /**模型名称*/
    @ExcelProperty(value = "模型名称",index = 0)
    private String modelName;
    /**模型类型**/
    @ExcelProperty(value = "模型类型",index = 1)
    private String modelKind;
    /**型号图片路由*/
    @ExcelProperty(value = "图片",index = 2)
    private String picture;
    /**监控类型 多种类型使用|进行间隔 类似于 加速度|倾角|........之类*/
    @ExcelProperty(value = "监控类型",index = 3)
    private String prototype;
    /**说明**/
    @ExcelProperty(value = "说明",index = 4)
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SensorModelVO)) {
            return false;
        }
        SensorModelVO that = (SensorModelVO) o;
        return Objects.equals(getModelName(), that.getModelName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModelName());
    }
}
