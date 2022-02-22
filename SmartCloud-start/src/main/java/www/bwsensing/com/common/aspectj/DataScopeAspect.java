package www.bwsensing.com.common.aspectj;

import javax.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.common.annotation.DataScope;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.role.UserRole;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.common.core.data.BaseDataObject;

/**
 * 数据过滤处理
 * 
 * @author ruoyi
 */
@Aspect
@Component
public class DataScopeAspect
{

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    @Resource
    private TokenGateway tokenGateway;

    @Before("@annotation(controllerDataScope)")
    public void doBefore(JoinPoint point, DataScope controllerDataScope) throws Throwable
    {
        clearDataScope(point);
        handleDataScope(point, controllerDataScope);
    }

    protected void handleDataScope(final JoinPoint joinPoint, DataScope controllerDataScope)
    {
        // 获取当前的用户
        TokenData currentUser = tokenGateway.getTokenInfo();
        if (currentUser != null)
        {
            // 如果是超级管理员，则不过滤数据
            if (!currentUser.getIsAdmin())
            {
                dataScopeFilter(joinPoint, currentUser, controllerDataScope.deptAlias(),
                        controllerDataScope.userAlias());
            }
        }
    }

    /**
     * 数据范围过滤
     * 
     * @param joinPoint 切点
     * @param user 用户
     * @param deptAlias 部门别名
     * @param userAlias 用户别名
     */
    public static void dataScopeFilter(JoinPoint joinPoint, TokenData user, String deptAlias, String userAlias)
    {
        StringBuilder sqlString = new StringBuilder();

        for (UserRole role : user.getUserRoles())
        {
            switch(role.getDataScope()){
                case DATA_SCOPE_ALL:{
                    sqlString = new StringBuilder();
                    break;
                }
                case DATA_SCOPE_CUSTOM:{
                    sqlString.append(StringUtils.format(
                            role.getDataScope().getSqlFormat(), deptAlias,
                            role.getId()));
                    break;
                }
                case DATA_SCOPE_DEPT:{
                    sqlString.append(StringUtils.format( role.getDataScope().getSqlFormat(), deptAlias, user.getDeptId()));
                    break;
                }
                case DATA_SCOPE_DEPT_AND_CHILD:{
                    sqlString.append(StringUtils.format(
                            role.getDataScope().getSqlFormat(),
                            deptAlias, user.getDeptId(), user.getDeptId()));
                    break;
                }
                case DATA_SCOPE_SELF:{
                    if (StringUtils.isNotBlank(userAlias))
                    {
                        sqlString.append(StringUtils.format(role.getDataScope().getSqlFormat(), userAlias, user.getUserId()));
                    }
                    else
                    {
                        // 数据权限为仅本人且没有userAlias别名不查询任何数据
                        sqlString.append(" OR 1=0 ");
                    }
                    break;
                }
                default:
                    break;
            }
        }

        if (StringUtils.isNotBlank(sqlString.toString()))
        {
            Object params = joinPoint.getArgs()[0];
            if (StringUtils.isNotNull(params) && params instanceof BaseDataObject)
            {
                BaseDataObject baseEntity = (BaseDataObject) params;
                baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
            }
        }
    }

    /**
     * 拼接权限sql前先清空params.dataScope参数防止注入
     */
    private void clearDataScope(final JoinPoint joinPoint)
    {
        Object params = joinPoint.getArgs()[0];
        if (StringUtils.isNotNull(params) && params instanceof BaseDataObject)
        {
            BaseDataObject baseEntity = (BaseDataObject) params;
            baseEntity.getParams().put(DATA_SCOPE, "");
        }
    }
}
