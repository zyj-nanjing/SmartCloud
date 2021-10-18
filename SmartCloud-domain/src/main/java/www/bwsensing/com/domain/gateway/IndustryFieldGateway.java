package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.industry.IndustryField;

/**
 * 行业领域 领域网关
 * @author macos-zyj
 */
public interface IndustryFieldGateway {
    /**
     * 保存行业领域
     * @param indusField
     */
    void saveIndustry(IndustryField indusField);

    /**
     * 修改行业领域
     * @param indusField
     */
    void updateIndustry(IndustryField indusField);
}
