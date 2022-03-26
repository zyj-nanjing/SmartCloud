package www.bwsensing.com.domain.common;

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
    private String modeCode;
    /**
     * 备注
     */
    private String remark;

    InteractionMode(String modeCode, String remark) {
        this.modeCode = modeCode;
        this.remark = remark;
    }

}
