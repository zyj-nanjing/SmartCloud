package www.bwsensing.com.domain.device;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */

public enum ElectricityStatus {
    /**电量状态**/
    FULL(0,"满电"),
    NORMAL(1,"正常"),
    LACK(2,"电量不足");
    private Integer statusId;
    private String name;

    public  static  ElectricityStatus getStatusById(Integer statusId){
        for (ElectricityStatus status : values()){
            if(status.getStatusId().equals(statusId)){
                return status;
            }
        }
        throw new BizException("ONLINE_STATUS_CODE_NOT_FOUNT");
    }


    ElectricityStatus(int statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public String getName() {
        return name;
    }
}
