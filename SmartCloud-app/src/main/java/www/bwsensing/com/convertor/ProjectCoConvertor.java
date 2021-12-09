package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.ProjectCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorProjectDO;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * @author macos-zyj
 */
public class ProjectCoConvertor {
    private static final BeanCopier PROJECT_COPIER = BeanCopier.create(MonitorProjectDO.class, ProjectCO.class,false);
    public static ProjectCO toClientObject(MonitorProjectDO dataObject){
        ProjectCO clientObject = new ProjectCO();
        PROJECT_COPIER.copy(dataObject,clientObject,null);
        return  clientObject;
    }

    public static List<ProjectCO> toClientCollection(List<MonitorProjectDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(ProjectCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
