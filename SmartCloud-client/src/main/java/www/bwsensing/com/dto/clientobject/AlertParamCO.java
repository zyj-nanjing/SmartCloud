package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import lombok.Data;
import java.util.List;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class AlertParamCO extends DTO {
    /**主键*/
    private Integer id;
    /**告警名称**/
    private String alertName;
    /**监测参数编号 注数据库编号*/
    private Integer paramNo;
    /**
     * 每组格式为 (max,>,10,&&)  第一位为 函数 可以为 max avg min  第二位为 运算符 可以为 < <= >  >= != == 第三位为具体数据 第四位为 逻辑或按位 运算符  || | && & 注意数组末尾不要加加了校验不会让你过的
     **/
    private List<String> formulas;
    /**问题发生后多久开始预警*/
    private String lastTime;
    /**监测周期*/
    private String period;
    /**颜色*/
    private String color;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlertName() {
        return alertName;
    }

    public void setAlertName(String alertName) {
        this.alertName = alertName;
    }

    public Integer getParamNo() {
        return paramNo;
    }

    public void setParamNo(Integer paramNo) {
        this.paramNo = paramNo;
    }

    public List<String> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<String> formulas) {
        this.formulas = formulas;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
