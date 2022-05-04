package www.bwsensing.com.domain.device.model.data.model;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */

public enum NetworkKind {
    /**
     * UDP 协议
     */
    UDP("UDP","UDP 协议"),
    /**
     * TCP 协议
     */
    TCP("TCP","TCP 协议"),
    /**
     * MQTT 协议
     */
    MQTT("MQTT","MQTT 协议");

    private final String typeName;
    private final String remark;

    NetworkKind(String typeName, String remark) {
        this.typeName = typeName;
        this.remark = remark;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getRemark() {
        return remark;
    }

    public static NetworkKind getNetworkKind(String type) {
        for (NetworkKind  currentKind : values()){
            if (currentKind.getTypeName().equals(type)){
                return currentKind;
            }
        }
        throw new BizException("network_kind_not_found","网络类型不存在");
    }
}
