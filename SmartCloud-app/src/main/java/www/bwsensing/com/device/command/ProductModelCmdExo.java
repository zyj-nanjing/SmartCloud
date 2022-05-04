package www.bwsensing.com.device.command;

import com.alibaba.cola.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductModelDO;
import www.bwsensing.com.monitor.convertor.PrototypeConvertor;
import www.bwsensing.com.domain.device.model.ProductModel;
import www.bwsensing.com.domain.device.gateway.ProductModelGateway;
import www.bwsensing.com.domain.monitor.model.MonitorPrototype;
import www.bwsensing.com.device.export.ProductModelVO;
import www.bwsensing.com.common.clientobject.ImportResultCO;
import www.bwsensing.com.monitor.gatewayimpl.database.MonitorPrototypeMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductModelMapper;
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
public class ProductModelCmdExo {
    private static final List<MonitorPrototypeDO> PROTOTYPE_CACHE = new ArrayList<>();

    @Resource
    private ProductModelGateway productModelGateway;
    @Resource
    private MonitorPrototypeMapper prototypeMapper;
    @Resource
    private ProductModelMapper productModelMapper;

    public SingleResponse<ImportResultCO> execute(List<ProductModelVO> importCollection){
        Map<Integer,String> errorInfoMap = new LinkedHashMap<>();
        List<ProductModelVO> checkedImportList = importListFilter(importCollection,errorInfoMap);
        int  successSize  = saveCheckedModelImport(checkedImportList);
        ImportResultCO result = new ImportResultCO(importCollection.size(),successSize,errorInfoMap);
        return SingleResponse.of(result);
    }

    private Integer saveCheckedModelImport(List<ProductModelVO> importCollection){
        AtomicReference<Integer> totalSaved = new AtomicReference<>(0);
        importCollection.forEach(modelData -> {
            if (checkModelNotRepeat(modelData.getModelName())){
                productModelGateway.saveModel(convertorImportToDo(modelData));
                log.warn("产品型号 Excel导入成功 型号名:{}",modelData.getModelName());
                totalSaved.getAndSet(totalSaved.get() + 1);
            } else {
                log.warn("产品型号 Excel导入错误 错误原因: {}已存在",modelData.getModelName());
            }
        });
        return totalSaved.get();
    }

    private ProductModel convertorImportToDo(ProductModelVO importObject){
        ProductModel saveObject = new ProductModel();
        BeanUtils.copyProperties(importObject,saveObject);
        List<String> typeCollection = prototypeSplit(importObject.getPrototype());
        return saveObject;
    }


    private boolean checkModelNotRepeat(String modelName){
        return productModelMapper.queryProductModelBySort(new ProductModelDO()).stream().noneMatch(model->model.getProductName().equals(modelName));
    }

    private List<ProductModelVO> importListFilter(List<ProductModelVO> importCollection, Map<Integer,String> errorInfoMap){
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

    private String modelImportCheckError(ProductModelVO modelImport){
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
