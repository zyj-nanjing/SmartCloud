package www.bwsensing.com.common.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;

/**
 * @author macos-zyj
 */
public interface ISmsService {

    /**
     * 获取客户端
     * @param accessKeyId
     * @param secret
     * @return
     */
    Client getClient(String accessKeyId, String secret);

    /**
     * 发送短信请求
     * @param sendSmsRequest
     * @return
     * @throws Exception
     */
    SendSmsResponse sendSmsRequest(SendSmsRequest sendSmsRequest)
            throws Exception;

    /**
     * 发送请求带秘钥指定的短信
     * @param sendBatchSmsRequest
     * @return
     * @throws Exception
     */
    SendBatchSmsResponse sendSmsBatchRequest(SendBatchSmsRequest sendBatchSmsRequest)
            throws Exception;

    /**
     * 发送请求带秘钥指定的短信
     * @param sendSmsRequest
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    SendSmsResponse sendSmsRequest(SendSmsRequest sendSmsRequest, String accessKeyId,
                                   String accessKeySecret) throws Exception;

    /**
     * 批量发送请求带秘钥指定的短信
     * @param sendSmsRequest
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    SendBatchSmsResponse sendSmsBatchRequest(SendBatchSmsRequest sendSmsRequest,
                                             String accessKeyId, String accessKeySecret)
            throws Exception;

    /**
     * 查询短信发送记录和发送状态等信息
     * @param request
     * @return
     * @throws Exception
     */
    QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest request)
            throws Exception;

    /**
     * 查询带秘钥指定的短信发送记录和发送状态等信息
     * @param request
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest request,
                                              String accessKeyId, String accessKeySecret) throws Exception;

    /**
     * 查询短信发送统计信息
     * @param request
     * @return
     * @throws Exception
     */
    QuerySendStatisticsResponse queryStatisticsRequest(QuerySendStatisticsRequest request) throws Exception;


    /**
     * 查询带秘钥指定的短信发送统计信息
     * @param request
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    QuerySendStatisticsResponse queryStatisticsRequest(QuerySendStatisticsRequest request,
                                              String accessKeyId, String accessKeySecret) throws Exception;

    /**
     * 调用短信AddSmsSign接口申请短信签名
     * @param request
     * @return
     * @throws Exception
     */
    AddSmsSignResponse addSmsSign(AddSmsSignRequest request) throws Exception;

    /**
     * 调用短信AddSmsSign接口申请短信签名
     * @param request
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    AddSmsSignResponse addSmsSign(AddSmsSignRequest request,
                                  String accessKeyId, String accessKeySecret) throws Exception;

    /**
     * 分页获取短信签名列表
     * @param request
     * @return
     * @throws Exception
     */
    QuerySmsSignListResponse querySmsSignList(QuerySmsSignListRequest request) throws Exception;


    /**
     * 分页获取短信签名列表
     * @param request
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    QuerySmsSignListResponse querySmsSignList(QuerySmsSignListRequest request,
                                              String accessKeyId, String accessKeySecret) throws Exception;

    /**
     * 查询短信签名状态，可用于判断签名是否可用
     * @param request
     * @return
     * @throws Exception
     */
    QuerySmsSignResponse querySmsSign(QuerySmsSignRequest request) throws Exception;


    /**
     * 查询短信签名状态，可用于判断签名是否可用
     * @param request
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    QuerySmsSignResponse querySmsSign(QuerySmsSignRequest request,
                                      String accessKeyId, String accessKeySecret) throws Exception;


    /**
     * 分页获取短信模板列表
     * @param request
     * @return
     * @throws Exception
     */
    QuerySmsTemplateListResponse querySmsTemplateList(QuerySmsTemplateListRequest request) throws Exception;


    /**
     * 分页获取短信模板列表
     * @param request
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    QuerySmsTemplateListResponse querySmsTemplateList(QuerySmsTemplateListRequest request,
                                                      String accessKeyId, String accessKeySecret) throws Exception;

    /**
     * 查询短信模板转台，可用于判断模板是否可用
     * @param request
     * @return
     * @throws Exception
     */
    QuerySmsTemplateResponse querySmsTemplate(QuerySmsTemplateRequest request) throws Exception;


    /**
     * 查询短信模板转台，可用于判断模板是否可用
     * @param request
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    QuerySmsTemplateResponse querySmsTemplate(QuerySmsTemplateRequest request,
                                              String accessKeyId, String accessKeySecret) throws Exception;

    /**
     * 查询模板标签
     * @param request
     * @return
     * @throws Exception
     */
    ListTagResourcesResponse listTagResources(ListTagResourcesRequest request) throws Exception;


    /**
     * 查询模板标签
     * @param request
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    ListTagResourcesResponse listTagResources(ListTagResourcesRequest request,
                                              String accessKeyId, String accessKeySecret) throws Exception;
}
