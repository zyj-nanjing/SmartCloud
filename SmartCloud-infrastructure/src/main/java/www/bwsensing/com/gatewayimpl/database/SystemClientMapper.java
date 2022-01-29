package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.SystemClientDO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 客户信息Mapper接口
 *
 * @author mac_zyj
 * @date 2022-01-19
 */
public interface SystemClientMapper
{
    /**
     * 查询客户信息
     *
     * @param id 客户信息主键
     * @return 客户信息
     */
    SystemClientDO getClientById(Integer id);

    /**
     * 查询客户信息列表
     *
     * @param sysClient 客户信息
     * @return 客户信息集合
     */
    List<SystemClientDO> selectClientBySort(SystemClientDO sysClient);

    /**
     * 校验使用
     * @param sysClient
     * @return
     */
    List<SystemClientDO> validSystemClientBySort(SystemClientDO sysClient);
    /**
     * 新增客户信息
     *
     * @param sysClient 客户信息
     * @return 结果
     */
     int addClient(SystemClientDO sysClient);

    /**
     * 修改客户信息
     *
     * @param sysClient 客户信息
     * @return 结果
     */
     int updateClient(SystemClientDO sysClient);

    /**
     * 删除客户信息
     *
     * @param id 客户信息主键
     * @return 结果
     */
     int deleteClientById(Integer id);

    /**
     * 批量删除客户信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
     int deleteClientByIds(String[] ids);

    /**
     * 添加客户信息与行业领域关联
     * @param clientId 客户ID
     * @param fieldId 行业领域ID
     * @return
     */
     int addClientFieldRelate(@Param("clientId")Integer clientId, @Param("fieldId")Integer fieldId);

    /**
     * 修改权重
     * @param order
     * @param id
     * @return
     */
     int updateClientOrder(@Param("order")Integer order, @Param("id")Integer id);

    /**
     * 添加权值
     * @param weight
     * @param id
     * @return
     */
     int addClientWeight(@Param("weight")Integer weight, @Param("id")Integer id);


    /**
     * 清空排序值 注意条件
     * @return
     */
     int clearClientOrder();

    /**
     * 删除关联
     * @param clientId
     * @return
     */
     int deleteClientFieldRelate(Integer clientId);
}