package www.bwsensing.com.monitor.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import www.bwsensing.com.monitor.export.PositionModelVo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author macos-zyj
 */
public class PositionModelListener extends AnalysisEventListener<PositionModelVo> {
    private List<PositionModelVo> importCollection = new ArrayList<>();
    /**
     * 错误消息
     */
    private Map<Integer,String> errorMsg = new LinkedHashMap<>();

    @Override
    public void invoke(PositionModelVo modelImport, AnalysisContext analysisContext) {
        if (importCollection.contains(modelImport)){
            String error = "测点重复;重复测点:"+modelImport.getName();
            errorMsg.put(importCollection.size()+1,error);
        }
        importCollection.add(modelImport);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    public List<PositionModelVo> getImportCollection(){
        return importCollection;
    }
}
