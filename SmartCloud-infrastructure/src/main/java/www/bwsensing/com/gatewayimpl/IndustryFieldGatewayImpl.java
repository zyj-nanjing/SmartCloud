package www.bwsensing.com.gatewayimpl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.convertor.IndustryFieldConvertor;
import www.bwsensing.com.domain.gateway.IndustryFieldGateway;
import www.bwsensing.com.domain.monitor.industry.IndustryField;
import www.bwsensing.com.gatewayimpl.database.IndustryFieldMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.IndustryFieldDO;


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
        saveData.setCreateTime(new Date());
        validateIndustry(saveData);
        industryFieldMapper.save(saveData);
    }

    @Override
    public void updateIndustry(IndustryField indusField) {
        IndustryFieldDO updateData = IndustryFieldConvertor.toDataObject(indusField);
        validateIndustry(updateData);
        industryFieldMapper.update(updateData);
    }

    @Override
    public List<IndustryField> getIndustryFieldsByIds(List<Integer> industryIds) {
        List<IndustryFieldDO> fieldDataCollection = industryFieldMapper.getIndustryFieldsByIds(industryIds);
        return IndustryFieldConvertor.toDomainArray(fieldDataCollection);
    }


    private void validateIndustry(IndustryFieldDO validator){
        IndustryFieldDO queryResult = new IndustryFieldDO();
        if(null != validator.getId()){
            queryResult = industryFieldMapper.selectIndustryById(validator.getId());
            if(StringUtils.isEmpty(validator.getName())|| queryResult.getName().equals(validator.getName())){
                return;
            }
        }
        if(StringUtils.isNotEmpty(validator.getName())){
            IndustryFieldDO validQuery = new IndustryFieldDO();
            validQuery.setName(validator.getName());
            if(StringUtils.isNotEmpty(validator.getCode())){
                validQuery.setCode(validator.getCode());
            } else {
                validQuery.setCode(queryResult.getCode());
            }
            if(industryFieldMapper.validIndustryBySort(validQuery).size() >0){
                throw new BizException("INDUSTRY_HAVE_EXIST","该行业领域已存在!");
            }
        }
    }
}
