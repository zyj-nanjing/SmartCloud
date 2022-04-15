package www.bwsensing.com.domain.device.model.data.model;

import www.bwsensing.com.domain.device.model.data.MonitorReceive;
import www.bwsensing.com.domain.device.model.data.MonitorData;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.exception.Assert;
import java.sql.Timestamp;
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
    private static final String CHANGE_LINE = "\n";

    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 数据形式
     */
    private DataForm dataForm;

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
     * 排序
     */
    private Integer weight;

    /**
     * 基础数据单元长度
     */
    private Integer baseDataSize;

    /**
     * 期待数据长度
     */
    private Integer expectDataSize;

    /**
     * 数据项
     */
    private List<DataModelItem> dataItems;

    /**
     * 传感器计算模型
     */
    private List<DataComputational> dataComputations;

    /**
     * 对应数据格式的正则
     */
    private String dataFormat;

    /**
     * 备注
     */
    private String remark;


    public ProductDataModel() {
    }

    public ProductDataModel(Integer carrySystem,String separator, String dataFormat) {
        this.carrySystem = carrySystem;
        this.splitMethod = SplitMethod.BY_SEPARATOR;
        this.separator = separator;
        this.dataFormat = dataFormat;
    }

    public ProductDataModel(Integer carrySystem, Integer baseDataSize,String dataFormat) {
        this.carrySystem = carrySystem;
        this.splitMethod = SplitMethod.BY_DATA_LENGTH;
        this.baseDataSize = baseDataSize;
        this.dataFormat = dataFormat;
    }

    /**
     * @// TODO: 2022/2/27  后续增加对于校验码的适配
     * @param receiveMessage 接收的消息
     * @param timestamp 时间戳
     * @return
     */
    public MonitorReceive messageAnalyse(String receiveMessage,Timestamp timestamp){
        return messageAnalyse(Collections.singletonList(receiveMessage),Collections.singletonList(timestamp));
    }

    /**
     * @// TODO: 2022/2/27  后续增加对于校验码的适配
     * @param receives 接收的消息
     * @return
     */
    public MonitorReceive messageAnalyse(List<String> receives,List<Timestamp> timestamps ){
        MonitorReceive dataReceive = new MonitorReceive();
        Assert.notEmpty(dataItems,"DATA_ITEM_NOT_SET","数据项未设立!");
        Collections.sort(dataItems);
        List<MonitorData> dataCollection = new ArrayList<>();
        switch(dataForm){
            case SINGLE_LINE_DATA:
                dataCollection.addAll(getMonitorDataCollectionWithLine(receives, timestamps,dataReceive));
                break;
            case DOUBLE_LINE_DATA:
                dataCollection.addAll(getMonitorDataCollectionWithDoubleLine(receives, timestamps,dataReceive));
                break;
            case JSON_DATA:
                break;
            default:
                break;
        }
        dataReceive.setDataCollection(dataCollection);
        dataReceive.setSendCount(dataReceive.getDataCollection().size());
        dataReceive.setReceiveSize(receives.size());
        return dataReceive;
    }

    private List<MonitorData> getMonitorDataCollectionWithDoubleLine(List<String> receives,List<Timestamp> timestamps,MonitorReceive dataReceive){
        StringBuilder logMessage = new StringBuilder();
        receives.forEach(message -> logMessage.append(message).append("\n"));
        String [] atrArray =  logMessage.toString().split(CHANGE_LINE);
        List<String> receiveData = new ArrayList<>();
        List<Timestamp> timestampResult = new ArrayList<>();
        boolean isMix = atrArray.length > timestamps.size();
        if (atrArray.length > 0){
            for (int i =0;i<atrArray.length;i++){
                String dataMessage = atrArray[i].replace("\n","").replace("\r","");
                receiveData.add(dataMessage);
                if (!isMix){
                    timestampResult.add(timestamps.get(i));
                } else{
                    int timeIndex = Math.min((i / 2), timestamps.size() - 1);
                    Timestamp currentTime = timestamps.get(timeIndex);
                    if (((i+1)%2)==0) {
                        timestampResult.add(new Timestamp(currentTime.getTime() + (long) 10));
                    } else {
                        timestampResult.add(currentTime);
                    }
                }
            }
        }
        return  getMonitorDataCollectionWithLine(receiveData,timestampResult,dataReceive);
    }




    private List<MonitorData> getMonitorDataCollectionWithLine(List<String> receives,List<Timestamp> timestamps,MonitorReceive dataReceive){
        List<MonitorData> dataCollection = new ArrayList<>();
        for(int index = 0; index < receives.size(); index++) {
            String receiveMessage = receives.get(index).replace("\n","").replace(" ","");
            List<String> dataSplits = getSplitCollects(receiveMessage);
            int dataSize = 0;
            for (int i = 0; i < dataItems.size(); i++) {
                DataModelItem currentItem = dataItems.get(i);
                switch (currentItem.getItemKind()) {
                    case UNIQUE_SN:
                        dataReceive.setSn(currentItem.geyUniqueCode(dataSplits.get(i), carrySystem));
                        break;
                    case DATA_INDEX:
                        MonitorData data = new MonitorData();
                        Double result = currentItem.mathCalculation(dataSplits.get(i), carrySystem);
                        data.setSn(dataReceive.getSn());
                        data.setDataIdValue(result);
                        data.setDataId(currentItem.getProtoItem().getDataId());
                        data.setTimeStamp(timestamps.get(index));
                        dataCollection.add(data);
                        dataSize++;
                        break;
                    case IDENTIFY_CODE:
                        dataReceive.setPhoneNumber(currentItem.getIdentifyCode(dataSplits.get(i)));
                    default:
                        break;
                }
            }
            Assert.isTrue(dataSize == expectDataSize,"解析结果与期待值不同!");
        }
        return dataCollection;
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
                splitCollects.addAll(dataLengthSplit(receiveMessage));
                break;
            default:
                break;
        }
        return splitCollects;
    }

    private List<String> dataLengthSplit(String receiveMessage) {
        List<String> splitCollects =  new ArrayList<>();
        Assert.notNull(baseDataSize,"通过数据长度进行分隔时数据字长不能为空!");
        List<Integer>  codeFormat =  new ArrayList<>();
        for (DataModelItem dataItem : dataItems) {
            Assert.notNull(dataItem.getDataLength(), "通过数据长度进行分隔时数据项长度不能为空!");
            codeFormat.add(dataItem.getDataLength());
        }
        String toNoNull = receiveMessage.replaceAll(" ","");
        try {
            int index = 0;
            for (int i : codeFormat) {
                int endIndex = index + baseDataSize * i;
                String currentBitCode = toNoNull.substring(index, endIndex);
                splitCollects.add(currentBitCode);
                index = endIndex;
            }
        }  catch (StringIndexOutOfBoundsException exception){
            throw new BizException("HEX_FORMAT_NOT_TRUE","接收数据格式不正确");
        }
        return splitCollects;
    }
}
