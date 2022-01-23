package www.bwsensing.com.convertor;

import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.StructureTemplateCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SysStructureTemplateDO;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
public class StructureTemplateCoConvertor {
    private static final BeanCopier TEMPLATE_DATA_COPIER = BeanCopier.create(SysStructureTemplateDO.class, StructureTemplateCO.class,false);

    public static List<StructureTemplateCO> toClientObjectArray(List<SysStructureTemplateDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    StructureTemplateCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static StructureTemplateCO toClientObject(SysStructureTemplateDO sensorModel){
        StructureTemplateCO clientObject = new StructureTemplateCO();
        TEMPLATE_DATA_COPIER.copy(sensorModel,clientObject,null);
        return clientObject;
    }
}
