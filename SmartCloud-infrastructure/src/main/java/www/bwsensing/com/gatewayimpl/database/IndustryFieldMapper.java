package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.IndustryFieldDO;

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
     * 组合查询
     * @param querySort
     * @return
     */
    List<IndustryFieldDO> selectIndustryBySort(IndustryFieldDO querySort);

}
