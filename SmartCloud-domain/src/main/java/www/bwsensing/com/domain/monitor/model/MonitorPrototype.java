package www.bwsensing.com.domain.monitor.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import www.bwsensing.com.domain.device.model.ProductDataItem;

import java.util.Date;
import java.util.List;
/**
 * 监测类型
 * @author macos-zyj
 */
@Slf4j
@Data
public class MonitorPrototype {
    /**主键*/
    private Integer id;
    /**类型编码*/
    private String typeCode;
    /**类型名称*/
    private String  typeName;
    /**创建人**/
    private String creator;
    /**创建时间**/
    private Date  createTime;
    /**修改者**/
    private String updater;
    /**修改时间**/
    private Date  updateTime;
    /**监测项目合集**/
    private List<ProductDataItem> itemsList;
    /**是否有效**/
    private boolean isEffective;
    private Integer orderSort;

    public MonitorPrototype() {

    }

    public MonitorPrototype(String typeCode) {
        this.typeCode = typeCode;
    }

    public void create(){
        this.creator = "admin";
        this.createTime = new Date();
    }
    public void update(){
        this.updater = "admin";
        this.updateTime = new Date();
    }

}
