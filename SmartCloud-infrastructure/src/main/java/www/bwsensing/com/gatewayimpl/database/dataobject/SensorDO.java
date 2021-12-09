package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;

import java.util.Date;
/**
 * @author macos-zyj
 */
@Data
public class SensorDO {
    private Integer id;
    /**设备Sn码*/
    private String sn;
    /**传感器名称*/
    private String name;
    /**传感器编号*/
    private String sensorNo;
    /**管理员编号*/
    private Integer managerId;
    /**管理员编号*/
    private String managerName;
    /**小组分组编号*/
    private Integer memberGroupId;
    /**模型编号*/
    private Integer modelId;
    /**模型名称*/
    private String modelName;
    /**项目编号*/
    private Integer projectId;
    /**测点编号*/
    private Integer positionId;
    /**经度**/
    private Double longitude;
    /**维度**/
    private Double latitude;
    private Boolean isOnline;
    /**安装时间*/
    private Date assignTime;
    /**安装时间**/
    private Date  installTime;
    /**初始提交时间*/
    private Date firstOnlineTime;
    /**
     * 接收数量
     */
    private Integer lastSendSize;
    /**
     * 数据接收全量
     */
    private Integer totalSendSize;
    /**
     * 接收数据全量
     */
    private Integer lastSendCount;
    /**
     * 发送地址
     */
    private String lastSendAddress;

    /**
     * 上一次发送时间
     */
    private Date lastSendTime;
    /**
     * 数据全量
     */
    private Integer totalSize;
    /**电量**/
    private Float electricity;
    /**温度**/
    private Float temperature;
    /**
     * 手机号
     */
    private String phoneNumber;

    private String createUser;

    /**按照上线时间排序**/
    private Boolean isOrderByOnlineTime;
    /**按照下线时间排序**/
    private Boolean isOrderByUpLineTime;
}
