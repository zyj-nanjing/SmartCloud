package www.bwsensing.com.service;

import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.IProjectMemberService;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.gatewayimpl.database.MonitorProjectMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorProjectDO;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Component
public class ProjectMemberServiceImpl implements IProjectMemberService {
    @Resource
    private MonitorProjectMapper projectMapper;
    @Resource
    private TokenGateway tokenGateway;
    @Override
    public String getCurrentProjectAuthCode(Integer projectId) {
        Assert.notNull(projectId,"项目编号不能为空!");
        validProjectExist(projectId);
        TokenData tokenData = tokenGateway.getTokenInfo();
        return projectMapper.getProjectRoleByUserId(projectId, tokenData.getUserId());
    }


    private  void validProjectExist(Integer projectId){
        MonitorProjectDO dataObject = projectMapper.selectMonitorProjectById(projectId);
        if(null == dataObject){
            throw new BizException("PROJECT_NOT_EXIST","项目编号对应的项目不存在请检查项目编号");
        }
    }
}
