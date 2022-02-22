package www.bwsensing.com.monitor.gatewayimpl.database;

import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.IndustryFieldDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface IndustryFieldMapper {
    /**
     * 保存
     * @param industryFieldDO
     */
    void save(IndustryFieldDO industryFieldDO);


    /**
     * 修改
     * @param industryFieldDO
     */
    void update(IndustryFieldDO industryFieldDO);

    /**
     * 根据Id 进行查询
     * @param id
     * @return
     */
    IndustryFieldDO selectIndustryById(Integer id);

    /**
     * 根据Id编号获取行业领域
     * @param ids
     * @return
     */
    List<IndustryFieldDO> getIndustryFieldsByIds(List<Integer> ids);
    /**
     * 组合查询
     * @param querySort
     * @return
     */
    List<IndustryFieldDO> selectIndustryBySort(IndustryFieldDO querySort);


    /**
     * 非like 查询
     * @param querySort
     * @return
     */
    List<IndustryFieldDO> validIndustryBySort(IndustryFieldDO querySort);
}
