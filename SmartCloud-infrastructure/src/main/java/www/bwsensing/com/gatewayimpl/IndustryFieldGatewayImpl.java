package www.bwsensing.com.gatewayimpl;

import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.convertor.IndustryFieldConvertor;
import www.bwsensing.com.domain.gateway.IndustryFieldGateway;
import www.bwsensing.com.domain.industry.IndustryField;
import www.bwsensing.com.gatewayimpl.database.IndustryFieldMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.IndustryFieldDO;

import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Component
public class IndustryFieldGatewayImpl implements IndustryFieldGateway {
    @Resource
    private IndustryFieldMapper industryFieldMapper;
    @Override
    public void saveIndustry(IndustryField indusField) {
        IndustryFieldDO saveData = IndustryFieldConvertor.toDataObject(indusField);
        validateIndustry(saveData);
        industryFieldMapper.save(saveData);
    }

    @Override
    public void updateIndustry(IndustryField indusField) {
        IndustryFieldDO updateData = IndustryFieldConvertor.toDataObject(indusField);
        validateIndustry(updateData);
        industryFieldMapper.update(updateData);
    }


    private void validateIndustry(IndustryFieldDO validator){
        if(null != validator.getId()){
            IndustryFieldDO queryResult = industryFieldMapper.selectIndustryById(validator.getId());
            if(StringUtils.isEmpty(validator.getName())|| queryResult.getName().equals(validator.getName())){
                return;
            }
        }
        if(StringUtils.isNotEmpty(validator.getName())){
            IndustryFieldDO validQuery = new IndustryFieldDO();
            validQuery.setName(validator.getName());
            if(null != industryFieldMapper.selectIndustryBySort(validQuery)){
                throw new BizException("INDUSTRY_HAVE_EXIST","该行业领域已存在!");
            }
        }
    }
}
