package www.bwsensing.com.convertor;

import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.menu.MenuKind;
import www.bwsensing.com.domain.system.menu.SystemMenu;
import www.bwsensing.com.gatewayimpl.database.dataobject.ViewMenuDO;


/**
 * @author macos-zyj
 */
public class ViewMenuConvertor {
    private static final BeanCopier VIEW_COPIER = BeanCopier.create(SystemMenu.class, ViewMenuDO.class,false);
    private static final BeanCopier VIEW_DOMAIN_COPIER = BeanCopier.create(ViewMenuDO.class, SystemMenu.class,false);

    public static ViewMenuDO toDataObject(SystemMenu domain){
        ViewMenuDO dataObject = new ViewMenuDO();
        VIEW_COPIER.copy(domain,dataObject,null);
        dataObject.setMenuKind(domain.getMenuKind().getKind());
        return dataObject;
    }

    public static SystemMenu toDomain(ViewMenuDO dataObject){
        SystemMenu domain = new SystemMenu();
        VIEW_DOMAIN_COPIER.copy(dataObject,domain,null);
        domain.setMenuKind(MenuKind.getMenuKind(dataObject.getMenuKind()));
        return domain;
    }

    public static List<SystemMenu> toDomainArray(List<ViewMenuDO> dataList){
        if (null != dataList){
            return dataList.stream().map(ViewMenuConvertor::toDomain).collect(toList());
        }
        return new ArrayList<>();
    }
}
