package www.bwsensing.com.domain.system.model.organization;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */
public enum DeptStatus {
    /**正常*/
    NORMAL(0,"正常"),
    /**停用*/
    DEACTIVATED(1,"停用");

    private final Integer status;
    private final String remark;

    DeptStatus(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public  static DeptStatus getDeptStatue(Integer status){
        for (DeptStatus currentStatus:values()) {
            if (currentStatus.getStatus().equals(status)){
                return currentStatus;
            }
        }
        throw new BizException("ENUM_NOT_FOUND","该部门状态未找到!");
    }
}
