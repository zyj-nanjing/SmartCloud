package www.bwsensing.com.domain.system.model.role;

import com.alibaba.cola.exception.BizException;

/**
 * 数据范围
 * @author macos-zyj
 */
public enum DataScopeEnum {
    /**
     * 数据范围清单
     */
    DATA_SCOPE_ALL("1","所有数据权限",""),
    DATA_SCOPE_CUSTOM("2","自定义数据权限"," OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) "),
    DATA_SCOPE_DEPT("3","本部门数据权限"," OR {}.dept_id = {} "),
    DATA_SCOPE_DEPT_AND_CHILD("4","本部门及以下数据权限"," OR {}.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set( {} , ancestors ))"),
    DATA_SCOPE_SELF("5","仅本人数据权限"," OR {}.user_id = {} ");
    private final String scopeId;
    private final String remark;
    private final String sqlFormat;

    DataScopeEnum(String scopeId, String remark,String sqlFormat) {
        this.scopeId = scopeId;
        this.remark = remark;
        this.sqlFormat = sqlFormat;
    }

    public  static DataScopeEnum getDataScope(String scopeId){
        for (DataScopeEnum currentScope:values()) {
            if (currentScope.getScopeId().equals(scopeId)){
                return currentScope;
            }
        }
        throw new BizException("ENUM_NOT_FOUND","该数据范围类型未找到!");
    }

    public String getScopeId() {
        return scopeId;
    }

    public String getRemark() {
        return remark;
    }

    public String getSqlFormat() {
        return sqlFormat;
    }
}
