package www.bwsensing.com.domain.device.model.data.model;

import www.bwsensing.com.domain.device.model.data.MonitorReceive;
import www.bwsensing.com.domain.device.model.data.MonitorData;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.exception.Assert;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;


/**
 * @author macos-zyj
 */
@Data
public class ProductDataModel {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 进制
     */
    private Integer carrySystem;

    /**
     * 分隔方式(枚举)
     */
    private SplitMethod splitMethod;

    /**
     * 分隔符
     */
    private String separator;

    /**
     * 基础数据单元长度
     */
    private Integer baseDataSize;

    /**
     * 数据项
     */
    private List<DataModelItem> dataItems;

    /**
     * 备注
     */
    private String remark;


    public ProductDataModel() {
    }

    public ProductDataModel(Integer carrySystem,String separator) {
        this.carrySystem = carrySystem;
        this.splitMethod = SplitMethod.BY_SEPARATOR;
        this.separator = separator;
    }

    public ProductDataModel(Integer carrySystem, Integer baseDataSize) {
        this.carrySystem = carrySystem;
        this.splitMethod = SplitMethod.BY_DATA_LENGTH;
        this.baseDataSize = baseDataSize;
    }

    /**
     * @// TODO: 2022/2/27  后续增加对于校验码的适配
     * @param receiveMessage 接收的消息
     * @return
     */
    public MonitorReceive messageAnalyse(String receiveMessage){
        return messageAnalyse(Collections.singletonList(receiveMessage));
    }

    /**
     * @// TODO: 2022/2/27  后续增加对于校验码的适配
     * @param receives 接收的消息
     * @return
     */
    public MonitorReceive messageAnalyse(List<String> receives){
        MonitorReceive dataReceive = new MonitorReceive();
        List<MonitorData> dataCollection = new ArrayList<>();
        Assert.notEmpty(dataItems,"DATA_ITEM_NOT_SET","数据项未设立!");
        Collections.sort(dataItems);
        for (String  receiveMessage:receives){
            List<String> dataSplits = getSplitCollects(receiveMessage);
            for (int i =0; i<dataItems.size(); i++){
                DataModelItem currentItem = dataItems.get(i);
                switch (currentItem.getItemKind()){
                    case UNIQUE_SN:
                        dataReceive.setSn(currentItem.geyUniqueCode(dataSplits.get(i),carrySystem));
                        break;
                    case FUNCTION_CODE:
                        MonitorData data = new MonitorData();
                        Double result = currentItem.mathCalculation(dataSplits.get(i),carrySystem);
                        data.setSn(dataReceive.getSn());
                        data.setDataIdValue(result);
                        data.setDataId(currentItem.getPrototype().getTypeCode());
                        dataCollection.add(data);
                        break;
                    case IDENTIFY_CODE:
                        dataReceive.setPhoneNumber(currentItem.getIdentifyCode(dataSplits.get(i)));
                    default:
                        break;
                }
            }
        }
        dataReceive.setDataCollection(dataCollection);
        dataReceive.setSendCount(dataReceive.getDataCollection().size());
        dataReceive.setReceiveSize(receives.size());
        return dataReceive;
    }



    private List<String> getSplitCollects(String receiveMessage){
        Assert.notNull(splitMethod,"数据分隔方式不存在");
        List<String> splitCollects =  new ArrayList<>();
        switch(splitMethod){
            case BY_SEPARATOR:
                Assert.notNull(separator,"通过占位符进行分隔时占位符不能为空!");
                String toNoNull = receiveMessage.replaceAll(" ","");
                String[] dataArray = toNoNull.split(separator);
                splitCollects.addAll(Arrays.asList(dataArray));
                break;
            case BY_DATA_LENGTH:
                Assert.notNull(baseDataSize,"通过数据长度进行分隔时数据字长不能为空!");
                List<Integer>  codeFormat =  new ArrayList<>();
                for (DataModelItem dataItem : dataItems) {
                    Assert.notNull(dataItem.getDataLength(), "通过数据长度进行分隔时数据项长度不能为空!");
                    codeFormat.add(dataItem.getDataLength());
                }
                try {
                    int index = 0;
                    for (int i : codeFormat) {
                        int endIndex = index + baseDataSize * i;
                        String currentBitCode = receiveMessage.substring(index, endIndex);
                        splitCollects.add(currentBitCode);
                        index = endIndex;
                    }
                }  catch (StringIndexOutOfBoundsException exception){
                    throw new BizException("HEX_FORMAT_NOT_TRUE","接收数据格式不正确");
                }
                break;
            default:
                break;
        }
        return splitCollects;
    }
}
