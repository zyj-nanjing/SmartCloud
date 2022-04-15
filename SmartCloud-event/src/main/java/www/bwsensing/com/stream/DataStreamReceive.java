package www.bwsensing.com.stream;

import lombok.Data;
import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import www.bwsensing.com.stream.object.ClientStream;
import www.bwsensing.com.stream.object.DataMessage;
import www.bwsensing.com.stream.object.HandlerCode;

/**
 * @author macos-zyj
 */
@Data
public class DataStreamReceive extends ClientStream {
    private static final String JOB_NAME = "DATA_RECEIVE_";
    /**
     * 接收方式
     */
    private String receiveMethod;

    /**
     * 唯一标识(TCP 以及 UDP 为 端口 MQTT 为对应的topic)
     */
    private String uniqueCode;

    /***
     * 产品型号
     */
    private Integer modelId;

    /**
     * 接收时间
     */
    private Date receiveTime;

    /**
     * 发送端
     */
    private String sender;

    /***
     * 对应的HostName
     */
    private String hostName;

    /**
     * 接收的数据
     */
    private List<DataMessage> receiveData;

    public DataStreamReceive() {

    }

    public DataStreamReceive(String messageKind) {
        super(messageKind);
    }

    @Override
    public String getJobName() {
        return JOB_NAME +modelId+"_"+receiveMethod;
    }

    @Override
    public String getTopic() {
        return  hostName + "-" +  receiveMethod + "-" + uniqueCode+"-" +sender;
    }

    @Override
    public String getHandlerCode() {
        return HandlerCode.DATA_MESSAGE.getHandleCode();
    }

    @Override
    public String getMessage() {
        return JSONObject.toJSONString(receiveData);
    }
}
