package www.bwsensing.com.dto.clientobject;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class IndustryFieldCO {
    /**主键*/
    private Integer id;
    /**名称*/
    private String name;
    /**行业编码*/
    private String code;
    /**创建时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
