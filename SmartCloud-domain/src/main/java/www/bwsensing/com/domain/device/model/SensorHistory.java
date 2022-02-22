package www.bwsensing.com.domain.device.model;

import com.alibaba.cola.exception.Assert;
import lombok.Data;
import java.util.Date;

/**
 * 传感器登录历史
 * @author macos-zyj
 */
@Data
public class SensorHistory extends SensorChannel{
    private Integer id;
    /**在线时间**/
    private Date onlineTime;
    /**重连时间**/
    private Date relineTime;
    /**下线时间**/
    private Date offLineTime;
    /**刷新时间**/
    private Date refreshTime;

    public SensorHistory() {

    }

    public SensorHistory(SensorChannel sensorChannel) {
        super.setChannelId(sensorChannel.getChannelId());
        super.setIpAddress(sensorChannel.getIpAddress());
        super.setSnCode(sensorChannel.getSnCode());

    }

    public  static SensorHistory online(SensorChannel sensorChannel){
        Assert.notNull(sensorChannel,"sensorChannel 不能为空");
        SensorHistory history = new SensorHistory(sensorChannel);
        history.setOnlineTime(new Date());
        return history;
    }
    public  static SensorHistory offline(SensorChannel sensorChannel){
        Assert.notNull(sensorChannel,"sensorChannel 不能为空");
        SensorHistory history = new SensorHistory(sensorChannel);
        history.setOffLineTime(new Date());
        return history;
    }

    public  static SensorHistory reline(SensorChannel sensorChannel){
        Assert.notNull(sensorChannel,"sensorChannel 不能为空");
        SensorHistory history = new SensorHistory(sensorChannel);
        history.setRelineTime(new Date());
        return history;
    }

    public   static SensorHistory refresh(SensorChannel sensorChannel){
        Assert.notNull(sensorChannel,"sensorChannel 不能为空");
        SensorHistory history = new SensorHistory(sensorChannel);
        history.setRefreshTime(new Date());
        return history;
    }
}
