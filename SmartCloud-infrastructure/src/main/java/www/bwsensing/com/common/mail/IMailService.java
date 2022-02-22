package www.bwsensing.com.common.mail;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.dm.model.v20151123.BatchSendMailRequest;
import com.aliyuncs.dm.model.v20151123.BatchSendMailResponse;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;

/**
 * @author macos-zyj
 */
public interface IMailService {
    /**
     * 获取默认节点信息
     * @param accessKeyId
     * @param secret
     * @return
     */
    IAcsClient getRegionClientProfile(String accessKeyId, String secret);

    /**
     * 获取自定义节点信息
     * @param accessKeyId
     * @param secret
     * @param location
     * @return
     */
    IAcsClient getRegionClientProfile(String accessKeyId, String secret,String location);
    /**
     * 发送单一消息
     * @param sendMailRequest
     * @return
     * @throws ServerException
     * @throws ClientException
     */
    SingleSendMailResponse sendMailRequest(SingleSendMailRequest sendMailRequest)
            throws ServerException, ClientException;

    /**
     * 批量发送
     * @param sendBatchMailRequest
     * @return
     * @throws ServerException
     * @throws ClientException
     */
    BatchSendMailResponse sendMailBatchRequest(BatchSendMailRequest sendBatchMailRequest)
            throws ServerException, ClientException;

    /**
     * 发送单一消息
     * @param sendMailRequest
     * @param accessKeyId
     * @param accessKeySecret
     * @param locationId
     * @return
     * @throws ServerException
     * @throws ClientException
     */
    SingleSendMailResponse sendMailRequest(SingleSendMailRequest sendMailRequest, String accessKeyId,
                                   String accessKeySecret,String locationId)
            throws ServerException, ClientException;

    /**
     * 批量发送
     * @param sendBatchMailRequest
     * @param accessKeyId
     * @param accessKeySecret
     * @param locationId
     * @return
     * @throws ServerException
     * @throws ClientException
     */
    BatchSendMailResponse sendMailBatchRequest(BatchSendMailRequest sendBatchMailRequest,
                                             String accessKeyId, String accessKeySecret,String locationId)
            throws ServerException, ClientException;
}
