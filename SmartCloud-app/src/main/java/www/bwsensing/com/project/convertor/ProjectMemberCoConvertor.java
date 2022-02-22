package www.bwsensing.com.project.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.project.dto.clientobject.ProjectMemberCO;
import www.bwsensing.com.project.gatewayimpl.database.dataobject.ProjectMemberDO;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class ProjectMemberCoConvertor {
    private static final BeanCopier MEMBER_COPIER = BeanCopier.create(ProjectMemberDO.class, ProjectMemberCO.class,false);

    public static ProjectMemberCO toClientObject(ProjectMemberDO dataObject){
        ProjectMemberCO clientObject = new ProjectMemberCO();
        MEMBER_COPIER.copy(dataObject,clientObject,null);
        return clientObject;
    }

    public static List<ProjectMemberCO> clientObjectArray(List<ProjectMemberDO> dataObjectArray){
        if (null != dataObjectArray){
            return dataObjectArray.stream().map(ProjectMemberCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
