package www.bwsensing.com.domain.monitor;


/**
 * @author macos-zyj
 */
public enum PositionStatus {
    /**绑定*/
    BIND(1),
    NOT_BIND(0);
    /**状态*/
    private Integer status;

    PositionStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
