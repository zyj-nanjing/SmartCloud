package www.bwsensing.com.domain.device.model.data.model;

import com.alibaba.cola.exception.BizException;

/**
 * 数据项类型
 * @author macos-zyj
 */
public enum DataItemKind {
    /***
     * 唯一编码
     */
    UNIQUE_SN(0),
    /***
     * 功能编码
     */
    FUNCTION_CODE(1),
    /***
     * 字节数
     */
    DATA_SIZE(2),
    /***
     * 数据位
     */
    DATA_INDEX(3),
    /***
     * CRC校验
     */
    CRC_CHECK(4),
    /**
     * 身份码
     */
    IDENTIFY_CODE(5);

    private final Integer type;


    DataItemKind(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public DataItemKind getDataItemKind(Integer type) {
        for (DataItemKind  currentKind : values()){
            if (currentKind.getType().equals(type)){
                return currentKind;
            }
        }
        throw new BizException("data_item_kind_not_found","数据类型不存在");
    }
}
