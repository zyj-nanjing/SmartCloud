package www.bwsensing.com.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Objects;

/**
 * @author macos-zyj
 */
@Data
public class PositionModelVo {
    /**类似于 1.1 格式*/
    @ExcelProperty(value = "测点编号",index = 0)
    private String index;
    /** 位置名称 **/
    @ExcelProperty(value = "测点名称",index = 1)
    private String name;
    /**点位说明**/
    @ExcelProperty(value = "测点描述",index = 2)
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PositionModelVo)) {
            return false;
        }
        PositionModelVo that = (PositionModelVo) o;
        return Objects.equals(getIndex(), that.getIndex()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), getName());
    }
}
