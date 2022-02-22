package www.bwsensing.com.monitor.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import www.bwsensing.com.monitor.export.StructureModelVo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author macos-zyj
 */
@Slf4j
public class StructureModelDataListener extends AnalysisEventListener<StructureModelVo> {

    private List<StructureModelVo> importCollection = new ArrayList<>();
    /**
     * 错误消息
     */
    private Map<Integer,String> errorMsg = new LinkedHashMap<>();

    @Override
    public void invoke(StructureModelVo modelImport, AnalysisContext analysisContext) {
        if (importCollection.contains(modelImport)){
            String error = "产品型号重复;重复型号:"+modelImport.getName();
            errorMsg.put(importCollection.size()+1,error);
        }
        importCollection.add(modelImport);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }

    public List<StructureModelVo> getImportCollection(){
        return importCollection;
    }
}
