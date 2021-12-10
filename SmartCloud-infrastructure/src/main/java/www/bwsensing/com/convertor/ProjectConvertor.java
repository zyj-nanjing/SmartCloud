package www.bwsensing.com.convertor;

import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.project.MonitorProject;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorProjectDO;



/**
 * @author macos-zyj
 */
public class ProjectConvertor {
    private static final BeanCopier PROJECT_COPY = BeanCopier.create(MonitorProject.class,
            MonitorProjectDO.class,false);

    private static final BeanCopier PROJECT_DOMAIN_COPY = BeanCopier.create(MonitorProjectDO.class,
            MonitorProject.class,false);

    public static MonitorProjectDO toDataObject(MonitorProject project){
        MonitorProjectDO dataObject = new MonitorProjectDO();
        PROJECT_COPY.copy(project,dataObject,null);
        dataObject.setOwnerId(project.getOwner().getId());
        return dataObject;
    }

    public static MonitorProject toDomain(MonitorProjectDO dataObject){
        MonitorProject domainProject = new MonitorProject();
        PROJECT_DOMAIN_COPY.copy(dataObject,domainProject,null);
        return domainProject;
    }

    public static List<MonitorProject> toDomainArray(List<MonitorProjectDO> dataObjects){
        if (null != dataObjects){
            return dataObjects.stream().map(ProjectConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
