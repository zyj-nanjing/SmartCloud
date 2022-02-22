package www.bwsensing.com.device.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import www.bwsensing.com.device.api.SensorModelService;
import www.bwsensing.com.device.export.SensorModelVO;
import www.bwsensing.com.common.clientobject.ImportResultCO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author macos-zyj
 */
public class SensorModelDataListener extends AnalysisEventListener<SensorModelVO> {
    private static final Logger logger = LoggerFactory.getLogger(SensorModelDataListener.class);

    private static final int BATCH_COUNT = 100;

    private SensorModelService sensorModelService;

    private List<SensorModelVO> importCollection = new ArrayList<>();

    private ImportResultCO importResult;
    /**
     * 错误消息
     */
    private Map<Integer,String> errorMsg = new LinkedHashMap<>();

    private SensorModelDataListener() {

    }

    public SensorModelDataListener(SensorModelService sensorModelService) {
        this.sensorModelService = sensorModelService;
    }

    @Override
    public void invoke(SensorModelVO modelImport, AnalysisContext analysisContext) {
        if (importCollection.contains(modelImport)){
            String error = "产品型号重复;重复型号:"+modelImport.getModelName();
            errorMsg.put(importCollection.size()+1,error);
        }
        importCollection.add(modelImport);
        if (importCollection.size() >= BATCH_COUNT) {
            saveData();
            importCollection.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        logger.info("所有数据解析完成！");
    }

    /**
     *存储数据库
     */
    private void saveData() {
        logger.info("{}条数据，开始存储数据库！", importCollection.size());
        ImportResultCO result = sensorModelService.importModels(importCollection).getData();
        logger.info("存储数据库成功---导入总条数:{},实际保存数量:{},保存失败数:{}",result.getImportSize(),result.getSuccessSize(),result.getFailedSize());
        errorMsg.putAll(result.getErrorMsg());
        result.setErrorMsg(errorMsg);
        importResult = result;
    }

    public ImportResultCO getImportResult(){
        return importResult;
    }
}

