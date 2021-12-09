package www.bwsensing.com.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.IIndustryFieldService;
import www.bwsensing.com.dto.clientobject.IndustryFieldCO;
import www.bwsensing.com.dto.command.IndustryFieldSaveCmd;
import www.bwsensing.com.dto.command.IndustryFieldUpdateCmd;
import www.bwsensing.com.dto.command.query.IndustryFileSortQuery;

/**
 * @author macos-zyj
 */
@Component
public class IndustryFieldServiceImpl implements IIndustryFieldService {

    @Override
    public Response saveIndustryField(IndustryFieldSaveCmd saveCmd) {
        return null;
    }

    @Override
    public Response updateIndustryField(IndustryFieldUpdateCmd updateCmd) {
        return null;
    }

    @Override
    public SingleResponse<IndustryFieldCO> selectIndustryFieldById(Integer id) {
        return null;
    }

    @Override
    public MultiResponse<IndustryFieldCO> selectIndustryFileQuery() {
        return null;
    }

    @Override
    public PageResponse<IndustryFieldCO> selectIndustryFileBySortPage(IndustryFileSortQuery sortQuery) {
        return null;
    }
}
