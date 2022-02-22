package www.bwsensing.com.system.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import www.bwsensing.com.system.dto.clientobject.UserInfoCO;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
@SuppressWarnings("all")
public class OperateGroupCO extends DTO {
    /**主键*/
    private Integer id;
    /**组名*/
    private String groupName;
    /**组号*/
    private String groupCode;
    /**人员数量*/
    private Integer personNumber;
    /**是否为内部部门*/
    private Boolean isInner;
    /**公司名称*/
    private String companyName;
    /**是否有效**/
    private Boolean isEnabled;
    /**创建时间**/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    /***用户集合*/
    private List<UserInfoCO> userInfoList;
}
