package www.bwsensing.com.common.client.database.dataobject;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class DataStreamSource {
    private Integer id;
    private String namespace;
    private String jobName;
}
