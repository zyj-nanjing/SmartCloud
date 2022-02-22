package www.bwsensing.com.domain.device.model;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */

public enum TemperatureStatus {
    /**温度状态**/
    NORMAL(0,"正常"),
    HIGH(1,"过高"),
    LOW(2,"");
    private Integer statusId;
    private String statusName;

    public  static  TemperatureStatus getStatusById(Integer statusId){
        for (TemperatureStatus status : values()){
            if(status.getStatusId().equals(statusId)){
                return status;
            }
        }
        throw new BizException("TEMPERATURE_STATUS_CODE_NOT_FOUNT");
    }


    TemperatureStatus(Integer statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }
}
