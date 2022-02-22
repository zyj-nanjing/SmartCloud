package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.model.alert.AlertParam;
import www.bwsensing.com.device.dto.command.AlertParamSaveCmd;
import www.bwsensing.com.device.dto.clientobject.AlertParamCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertParamDO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * @author macos-zyj
 */
public class AlertParamCoConvertor {
    private static final BeanCopier PARAM_COPIER = BeanCopier.create(AlertParamSaveCmd.class, AlertParam.class,false);
    private static final BeanCopier PARAM_CLIENT_COPIER = BeanCopier.create(AlertParamDO.class, AlertParamCO.class,false);

    public static AlertParam toDomain(AlertParamSaveCmd saveCmd){
        AlertParam domainParam = new AlertParam();
        PARAM_COPIER.copy(saveCmd,domainParam,null);
        return  domainParam;
    }

    public static List<AlertParam> toDomainCollection(List<AlertParamSaveCmd> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(AlertParamCoConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }


    public static AlertParamCO toClientObject(AlertParamDO dataObject){
        AlertParamCO clientObject = new AlertParamCO();
        PARAM_CLIENT_COPIER.copy(dataObject,clientObject,null);
        clientObject.setFormulas(Arrays.asList(dataObject.getFormulas().split("#")));
        return  clientObject;
    }

    public static List<AlertParamCO> toClientCollection(List<AlertParamDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(AlertParamCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
