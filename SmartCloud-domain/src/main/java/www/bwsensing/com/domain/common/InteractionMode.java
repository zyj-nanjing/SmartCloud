package www.bwsensing.com.domain.common;

import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.domain.device.model.DataItemSourceKind;

/**
 * 交互方式 后续可酌情变为数据对象 暂为枚举
 * @author macos-zyj
 */
public enum InteractionMode {
    /**
     * 内部调用
     */
    INTERNAL_CALL("101","内部调用-指本平台间的非消息数据通讯"),
    INTERNAL_CROSS_CALL("102","内部调用-指本平台间的跨传数据通讯"),
    CROSS_PLATFORM_SDK_CALL("103","跨平台SDK调用"),
    CROSS_PLATFORM_WEB_CALL("104","跨平台WebService调用");
    /**
     * 模型编码
     */
    private final String modeCode;
    /**
     * 备注
     */
    private final String remark;

    InteractionMode(String modeCode, String remark) {
        this.modeCode = modeCode;
        this.remark = remark;
    }

    public String getModeCode() {
        return modeCode;
    }

    public String getRemark() {
        return remark;
    }

    public static InteractionMode getInteractionMode(String modeCode){
        for (InteractionMode current:values()){
            if (current.getModeCode().equals(modeCode)) {
                return current;
            }
        }
        throw new BizException("interaction_mode_not_found","交互方式类型不存在");
    }
}
