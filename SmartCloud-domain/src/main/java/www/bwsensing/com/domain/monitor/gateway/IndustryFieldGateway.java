package www.bwsensing.com.domain.monitor.gateway;

import www.bwsensing.com.domain.monitor.model.industry.IndustryField;
import java.util.List;

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

    /**
     * 根据行业领域编号获取对应的行业领域
     * @param industryIds
     * @return
     */
    List<IndustryField> getIndustryFieldsByIds(List<Integer> industryIds);
}
