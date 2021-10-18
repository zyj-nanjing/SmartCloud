package www.bwsensing.com.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author macos-zyj
 */
@Data
public class StructureModelVo {
    /** 名称 */
    @ExcelProperty(value = "结构物名称",index = 0)
    private String name;
    @ExcelProperty(value = "结构物图片",index = 1)
    private String picture;
    /**是否为公有结构物*/
    @ExcelProperty(value = "结构物状态",index = 2)
    private Boolean isPublic;
    /**是否包含手机号*/
    @ExcelProperty(value = "手机号状态",index = 3)
    private Boolean isContainMobile;
    /**是否包含经纬度*/
    @ExcelProperty(value = "经纬度状态",index = 4)
    private Boolean isContainPosition;
    /**描述*/
    @ExcelProperty(value = "描述",index = 5)
    private String comment;
    /**测点信息*/
    private List<PositionModelVo> positionCollection;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StructureModelVo)) {
            return false;
        }
        StructureModelVo that = (StructureModelVo) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
