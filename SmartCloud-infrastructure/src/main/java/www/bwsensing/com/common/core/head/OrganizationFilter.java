package www.bwsensing.com.common.core.head;

import www.bwsensing.com.domain.system.model.organization.SystemStructure;

/**
 * @author macos-zyj
 */
public interface OrganizationFilter {
    /**
     * 获取
     * @return
     */
    String getOrganizationCode();

    /**
     * 获取对应的组织
     * @return
     */
    SystemStructure getOrganization();
}
