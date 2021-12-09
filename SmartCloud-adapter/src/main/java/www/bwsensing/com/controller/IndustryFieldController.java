package www.bwsensing.com.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.bwsensing.com.api.IIndustryFieldService;
import www.bwsensing.com.dto.clientobject.IndustryFieldCO;
import www.bwsensing.com.dto.command.IndustryFieldSaveCmd;
import www.bwsensing.com.dto.command.IndustryFieldUpdateCmd;
import www.bwsensing.com.dto.command.query.IndustryFileSortQuery;

/**
 * 行业领域
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/industry/filed")
@RestController
public class IndustryFieldController {

    @Autowired
    private IIndustryFieldService industryFieldService;

    /**
     * 保存行业领域
     * @param saveCmd
     * @return
     */
    public Response saveIndustryField(IndustryFieldSaveCmd saveCmd){
        return industryFieldService.saveIndustryField(saveCmd);
    }


    /**
     * 修改行业领域
     * @param updateCmd
     * @return
     */
    public Response updateIndustryField(IndustryFieldUpdateCmd updateCmd){
        return industryFieldService.updateIndustryField(updateCmd);
    }

    /**
     * 根据 id 查询
     * @param id
     * @return
     */
    public SingleResponse<IndustryFieldCO> selectIndustryFieldById(Integer id){
        return industryFieldService.selectIndustryFieldById(id);
    }

    /**
     * 查询所有
     * @return
     */
    public MultiResponse<IndustryFieldCO> selectIndustryFileQuery(){
        return industryFieldService.selectIndustryFileQuery();
    }

    /**
     * 表格查询
     * @param sortQuery
     * @return
     */
    public PageResponse<IndustryFieldCO> selectIndustryFileBySortPage(IndustryFileSortQuery sortQuery){
        return industryFieldService.selectIndustryFileBySortPage(sortQuery);
    }
}
