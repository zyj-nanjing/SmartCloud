package www.bwsensing.com.domain.device.model;

import lombok.Data;

/**
 * 产品类型 类似于 无线倾角 DTU GNSS之类
 * @author macos-zyj
 */
@Data
public class ProductKind {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
}
