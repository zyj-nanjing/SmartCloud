package www.bwsensing.com.device.command;

import com.alibaba.cola.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.monitor.convertor.PrototypeConvertor;
import www.bwsensing.com.domain.device.model.SensorModel;
import www.bwsensing.com.domain.device.gateway.SensorModelGateway;
import www.bwsensing.com.domain.monitor.model.MonitorPrototype;
import www.bwsensing.com.device.export.SensorModelVO;
import www.bwsensing.com.common.clientobject.ImportResultCO;
import www.bwsensing.com.monitor.gatewayimpl.database.MonitorPrototypeMapper;
import www.bwsensing.com.device.gatewayimpl.database.SensorModelMapper;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorPrototypeDO;
import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.*;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class SensorModelCmdExo {
    private static final List<MonitorPrototypeDO> PROTOTYPE_CACHE = new ArrayList<>();

    @Resource
    private SensorModelGateway sensorModelGateway;
    @Resource
    private MonitorPrototypeMapper prototypeMapper;
    @Resource
    private SensorModelMapper sensorModelMapper;

    public SingleResponse<ImportResultCO> execute(List<SensorModelVO> importCollection){
        Map<Integer,String> errorInfoMap = new LinkedHashMap<>();
        List<SensorModelVO> checkedImportList = importListFilter(importCollection,errorInfoMap);
        int  successSize  = saveCheckedModelImport(checkedImportList);
        ImportResultCO result = new ImportResultCO(importCollection.size(),successSize,errorInfoMap);
        return SingleResponse.of(result);
    }

    private Integer saveCheckedModelImport(List<SensorModelVO> importCollection){
        AtomicReference<Integer> totalSaved = new AtomicReference<>(0);
        importCollection.forEach(modelData -> {
            if (checkModelNotRepeat(modelData.getModelName())){
                sensorModelGateway.saveModel(convertorImportToDo(modelData));
                log.warn("产品型号 Excel导入成功 型号名:{}",modelData.getModelName());
                totalSaved.getAndSet(totalSaved.get() + 1);
            } else {
                log.warn("产品型号 Excel导入错误 错误原因: {}已存在",modelData.getModelName());
            }
        });
        return totalSaved.get();
    }

    private SensorModel convertorImportToDo(SensorModelVO importObject){
        SensorModel saveObject = new SensorModel();
        BeanUtils.copyProperties(importObject,saveObject);
        List<String> typeCollection = prototypeSplit(importObject.getPrototype());
        saveObject.setPrototypeList(prototypeHandle(typeCollection));
        return saveObject;
    }


    private boolean checkModelNotRepeat(String modelName){
        return sensorModelMapper.selectAllProductModels().stream().noneMatch(model->model.getModelName().equals(modelName));
    }

    private List<SensorModelVO> importListFilter(List<SensorModelVO> importCollection,Map<Integer,String> errorInfoMap){
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

    private String modelImportCheckError(SensorModelVO modelImport){
        if (StringUtils.isEmpty(modelImport.getModelName())){
            return "产品型号名称格式错误";
        }
        else if (!checkModelNotRepeat(modelImport.getModelName())){
            return "产品型号已存在";
        }
        else if (!prototypeFilter(modelImport.getPrototype())){
            return "产品监控类型格式错误";
        }
        else {
            return "";
        }
    }

    private Boolean prototypeFilter(String prototype){
        if (StringUtils.isNotEmpty(prototype)){
            if (PROTOTYPE_CACHE.isEmpty()){
                PROTOTYPE_CACHE.addAll(prototypeMapper.selectPrototypeBySort(new MonitorPrototypeDO()));
            }
            String[] prototypes = prototype.split("\\|");
            if (prototypes.length <=0) {
                return false;
            }
            for (String  type:prototypes){
                if (PROTOTYPE_CACHE.stream().
                        noneMatch(typeData -> typeData.getTypeName().equals(type)))
                {
                    return false;
                }
            }
            return true;
        }
        return false;

    }
    private List<String> prototypeSplit(String prototype){
        String[] prototypes = prototype.split("\\|");
        return  Arrays.asList(prototypes);
    }

    private  List<MonitorPrototype> prototypeHandle(List<String> typeCollection){
        List<MonitorPrototype> prototypeList = new ArrayList<>();
        typeCollection.forEach(prototype->{
            PROTOTYPE_CACHE.forEach(cache->{
                if (cache.getTypeName().equals(prototype)){
                    prototypeList.add(PrototypeConvertor.toDomain(cache));
                }
            });
        });
        return prototypeList;
    }

}
