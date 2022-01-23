package www.bwsensing.com.domain.system.menu;

import com.alibaba.cola.exception.BizException;

/**
 * 菜单类型
 * @author macos-zyj
 */
public enum MenuKind {
    /**
     * 菜单类型
     */
    FUNCTION("F","按钮"),
    CATALOGUE("C","目录"),
    MENU("M","菜单"),
    API("A","后端API");
    private final String kind;
    private final String remark;

    MenuKind(String kind, String remark) {
        this.kind = kind;
        this.remark = remark;
    }

    public static MenuKind getMenuKind(String kind){
        for (MenuKind current:values()){
            if (current.getKind().equals(kind)){
                return current;
            }
        }
        throw new BizException("ENUM_NOT_FOUND","该菜单类型未找到!");
    }

    public String getKind() {
        return kind;
    }

    public String getRemark() {
        return remark;
    }
}
