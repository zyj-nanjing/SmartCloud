package www.bwsensing.com.domain.device.model.data.model;

import com.alibaba.cola.exception.BizException;

/**
 * @author macos-zyj
 */

public enum MessageFormat {
    /**
     * ASCII
     */
    ASCII("ASCII","北微ASCII协议"),
    HEX("HEX","北微HEX协议"),
    JSON("JSON","JSON格式数据");
    private final String type;

    private final String remark;

    MessageFormat(String type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }

    public static MessageFormat getMessageFormat(String type) {
        for (MessageFormat  current : values()){
            if (current.getType().equals(type)){
                return current;
            }
        }
        throw new BizException("message_format_not_found","消息类型不存在");
    }
}
