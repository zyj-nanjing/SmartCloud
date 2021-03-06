package www.bwsensing.com.monitor.command;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.util.stream.Collectors;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import www.bwsensing.com.domain.monitor.gateway.StructureModelGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.monitor.model.model.MonitorPositionModel;
import www.bwsensing.com.domain.monitor.model.model.MonitorStructureModel;
import www.bwsensing.com.monitor.export.PositionModelVo;
import www.bwsensing.com.monitor.export.StructureModelVo;
import www.bwsensing.com.common.clientobject.ImportResultCO;
import www.bwsensing.com.monitor.gatewayimpl.database.MonitorStructureModelMapper;

import static java.util.stream.Collectors.toList;


/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class StructureImportCmdExo {
    @Resource
    private StructureModelGateway structureModelGateway;
    @Resource
    private MonitorStructureModelMapper structureModelMapper;
    @Resource
    private TokenGateway tokenGateway;
    public SingleResponse<ImportResultCO> execute(List<StructureModelVo> structureCollection,
                                                  List<PositionModelVo> positionCollection){
        List<StructureModelVo> importCollection = initStructureModel(structureCollection,positionCollection);
        Map<Integer,String> errorInfoMap = new LinkedHashMap<>();
        List<StructureModelVo> checkedImportList = importListFilter(importCollection,errorInfoMap);
        int  successSize  = saveCheckedModelImport(checkedImportList);
        ImportResultCO result = new ImportResultCO(importCollection.size(),successSize,errorInfoMap);
        return SingleResponse.of(result);
    }
    private  List<StructureModelVo> initStructureModel(List<StructureModelVo> structureCollection,
                                                       List<PositionModelVo> positionCollection){
        for(int i =0;i< structureCollection.size();i++){
            String indexPrefix = i+1+".";
            List<PositionModelVo> result = positionCollection.stream()
                    .filter(position -> position.getIndex().startsWith(indexPrefix)).collect( Collectors.toList() );
            structureCollection.get(i).setPositionCollection(result);
        }
        return structureCollection;
    }
    private Integer saveCheckedModelImport(List<StructureModelVo> importCollection){
        AtomicReference<Integer> totalSaved = new AtomicReference<>(0);
        importCollection.forEach(modelData -> {
            if (checkStructureNotRepeat(modelData.getName())){
                structureModelGateway.saveModel(convertorImportToDomain(modelData));
                log.warn("??????????????? Excel???????????? ??????:{}",modelData.getName());
                totalSaved.getAndSet(totalSaved.get() + 1);
            } else {
                log.warn("??????????????? Excel???????????? ????????????: {}?????????",modelData.getName());
            }
        });
        return totalSaved.get();
    }

    private MonitorStructureModel convertorImportToDomain(StructureModelVo importObject){
        MonitorStructureModel saveObject = new MonitorStructureModel();
        BeanUtils.copyProperties(importObject,saveObject);
        saveObject.saveOrUpdate(tokenGateway.getTokenInfo().getAccountName());
        saveObject.setPositionList(importToPositionModels(importObject.getPositionCollection()));
        return saveObject;
    }

    private List<MonitorPositionModel> importToPositionModels(List<PositionModelVo> importObjects){
        return importObjects.stream().map(this::toPositionDomainModel).collect(toList());
    }

    private MonitorPositionModel toPositionDomainModel(PositionModelVo modelVo){
        MonitorPositionModel positionModel = new MonitorPositionModel();
        BeanUtils.copyProperties(modelVo,positionModel);
        return positionModel;
    }


    private boolean checkStructureNotRepeat(String modelName){
        return structureModelMapper.selectStructureModel(tokenGateway.getTokenInfo().getGroupId())
                .stream().noneMatch(model->model.getName().equals(modelName));
    }

    private List<StructureModelVo> importListFilter(List<StructureModelVo> importCollection, Map<Integer,String> errorInfoMap){
        if (importCollection.size() > 0){
            AtomicInteger filterIndex = new AtomicInteger();
            return  importCollection.stream().filter(
                    customerImport-> {
                        filterIndex.addAndGet(1);
                        String errorInfo = modelImportCheckError(customerImport);
                        if (StringUtils.isEmpty(errorInfo)){
                            return true;
                        }else {
                            errorInfoMap.put(filterIndex.get(),errorInfo);
                            return false;
                        }
                    }
            ).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private String modelImportCheckError(StructureModelVo modelImport){
        if (StringUtils.isEmpty(modelImport.getName())){
            return "?????????????????????????????????";
        }
        else if (!checkStructureNotRepeat(modelImport.getName())){
            return "????????????????????????";
        }
        else if (positionFilter(modelImport.getPositionCollection())){
            return "??????????????????!";
        }
        else {
            return "";
        }
    }

    private Boolean positionFilter(List<PositionModelVo> positionCollection){
        AtomicReference<Boolean> isValid = new AtomicReference<>(false);
        if(positionCollection.size() == 0){
            return false;
        }
        positionCollection.forEach(position->{
            if(StringUtils.isEmpty(position.getName())){
                isValid.set(true);
            }
        });
        return isValid.get();
    }
}
