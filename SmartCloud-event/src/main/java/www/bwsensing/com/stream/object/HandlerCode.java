package www.bwsensing.com.stream.object;

/**
 * @author macos-zyj
 */

public enum HandlerCode {
    /**
     * 操作
     */
    DATA_MESSAGE("DATA_MESSAGE"),
    NOTIFICATION("NOTIFICATION"),
    DEVICE_MESSAGE("DEVICE_MESSAGE"),
    DEVICE_OPERATION("DEVICE_OPERATION");
    private String handleCode;

    HandlerCode(String handleCode) {
        this.handleCode = handleCode;
    }

    public String getHandleCode() {
        return handleCode;
    }
}
