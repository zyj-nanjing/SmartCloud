package www.bwsensing.com.domain.device.model;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */

public enum OnlineStatus {
    /**在线状态**/
    ON_LINE(0),
    OFF_LINE(1);
    private Integer statusId;



    OnlineStatus(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public  static  OnlineStatus getStatusById(Integer statusId){
        for (OnlineStatus status : values()){
            if(status.getStatusId().equals(statusId)){
                return status;
            }
        }
        throw new BizException("ONLINE_STATUS_CODE_NOT_FOUNT");
    }
}
