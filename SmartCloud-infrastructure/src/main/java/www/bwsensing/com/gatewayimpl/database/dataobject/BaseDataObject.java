package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;

import java.util.Map;

/**
 * @author macos-zyj
 */
@Data
public class BaseDataObject {
    /** 请求参数 */
    private Map<String, Object> params;
}
