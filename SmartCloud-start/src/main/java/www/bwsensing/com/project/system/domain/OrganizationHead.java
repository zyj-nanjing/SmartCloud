package www.bwsensing.com.project.system.domain;

import java.util.Map;
import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.common.utils.ServletUtils;
import www.bwsensing.com.common.core.head.OrganizationFilter;
import www.bwsensing.com.domain.system.gateway.AuthenticationGateway;
import www.bwsensing.com.domain.system.gateway.SystemStructureGateway;
import www.bwsensing.com.domain.system.model.organization.SystemStructure;


/**
 * @author macos-zyj
 */
@Component
public class OrganizationHead implements OrganizationFilter {

    private  static Map<String,String> USER_ID_ORGANIZATION_MAP = new ConcurrentHashMap<>();

    private Map<String,SystemStructure>  CODE_ORGANIZATION_MAP = new ConcurrentHashMap<>();

    @Resource
    private AuthenticationGateway authenticationGateway;

    @Resource
    private SystemStructureGateway systemStructureGateway;


    private static final String ORGANIZATION_CODE = "organization";

    @Override
    public String getOrganizationCode() {
        String code = ServletUtils.getHead(ORGANIZATION_CODE);
        if (StringUtils.isNotEmpty(code) ) {
            String accountName = authenticationGateway.getAccountName();
            if ( StringUtils.isNotEmpty(accountName) ) {
                USER_ID_ORGANIZATION_MAP.put(accountName, code);
            }
        }
        return code;
    }

    @Override
    public SystemStructure getOrganization() {
        String accountName = authenticationGateway.getAccountName();
        String code = "";
        if ( StringUtils.isNotEmpty(accountName)) {
            code = USER_ID_ORGANIZATION_MAP.get(accountName);
        }
        if ( StringUtils.isNotEmpty(code)){
            code = getOrganizationCode();
            if ( null != CODE_ORGANIZATION_MAP.get(code) ){
                return CODE_ORGANIZATION_MAP.get(code);
            } else {
                SystemStructure organization = systemStructureGateway.getOrganizationByCode(code);
                CODE_ORGANIZATION_MAP.put(code, organization);
                return organization;
            }
        } else {
            return null;
        }
    }


}
