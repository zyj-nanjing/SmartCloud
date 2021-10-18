package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;

import java.util.Map;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ImportResultCO extends DTO {
    /**
     * 导入量
     */
    private Integer importSize;
    /**
     * 成功量
     */
    private Integer successSize;
    /**
     * 失败量
     */
    private Integer failedSize;
    /**
     * 错误消息
     */
    private Map<Integer,String> errorMsg;

    public ImportResultCO() {

    }

    public ImportResultCO(Integer importSize, Integer successSize, Map<Integer, String> errorMsg) {
        this.importSize = importSize;
        this.successSize = successSize;
        this.errorMsg = errorMsg;
        this.failedSize = this.importSize - this.successSize;
    }

    public Integer getImportSize() {
        return importSize;
    }

    public void setImportSize(Integer importSize) {
        this.importSize = importSize;
    }

    public Integer getSuccessSize() {
        return successSize;
    }

    public void setSuccessSize(Integer successSize) {
        this.successSize = successSize;
    }

    public Integer getFailedSize() {
        return failedSize;
    }

    public void setFailedSize(Integer failedSize) {
        this.failedSize = failedSize;
    }

    public Map<Integer, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<Integer, String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ImportResultCO{" +
                "importSize=" + importSize +
                ", successSize=" + successSize +
                ", failedSize=" + failedSize +
                ", errorMsg=" + errorMsg +
                '}';
    }
}
