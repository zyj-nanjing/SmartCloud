package www.bwsensing.com.device.dto.command.query;

import com.alibaba.cola.dto.Query;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author macos-zyj
 */
public class ProductDataTableQuery extends Query {
    /**设备唯一编码*/
    @NotBlank(message = "设备唯一编码不能为空!")
    private String uniqueCode;

    /**选中日期*/
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @NotNull(message = "设备唯一编码不能为空!")
    private Date selectDate;

    public ProductDataTableQuery() {
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public Date getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(Date selectDate) {
        this.selectDate = selectDate;
    }
}
