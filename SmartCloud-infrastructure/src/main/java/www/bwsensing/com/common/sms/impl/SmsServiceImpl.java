package www.bwsensing.com.common.sms.impl;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import com.aliyun.dysmsapi20170525.models.*;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.sms.AbstractSmsService;
import www.bwsensing.com.common.config.AliCloudProperties;
/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class SmsServiceImpl extends AbstractSmsService {

    @Resource
    private AliCloudProperties aliCloudProperties;

    @Override
    public SendSmsResponse sendSmsRequest(SendSmsRequest sendSmsRequest) throws Exception {
        return sendSmsRequest(sendSmsRequest, aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey());
    }

    @Override
    public SendBatchSmsResponse sendSmsBatchRequest(SendBatchSmsRequest sendBatchSmsRequest) throws Exception {
        return sendSmsBatchRequest(sendBatchSmsRequest, aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey());
    }

    @Override
    public SendSmsResponse sendSmsRequest(SendSmsRequest sendSmsRequest, String accessKeyId, String accessKeySecret) throws Exception {
        return getClient(accessKeyId, accessKeySecret)
                .sendSms(sendSmsRequest);
    }

    @Override
    public SendBatchSmsResponse sendSmsBatchRequest(SendBatchSmsRequest sendSmsRequest, String accessKeyId, String accessKeySecret) throws Exception {
        return getClient(accessKeyId, accessKeySecret)
                .sendBatchSms(sendSmsRequest);
    }

    @Override
    public QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest request, String accessKeyId, String accessKeySecret) throws Exception {
        return getClient(accessKeyId, accessKeySecret)
                .querySendDetails(request);
    }

    @Override
    public QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest request) throws Exception {
        return querySendDetails(request, aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey());
    }

    @Override
    public QuerySendStatisticsResponse queryStatisticsRequest(QuerySendStatisticsRequest request) throws Exception {
        return queryStatisticsRequest(request,aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey());
    }

    @Override
    public QuerySendStatisticsResponse queryStatisticsRequest(QuerySendStatisticsRequest request, String accessKeyId, String accessKeySecret) throws Exception {
        return getClient(accessKeyId, accessKeySecret)
                .querySendStatistics(request);
    }

    @Override
    public AddSmsSignResponse addSmsSign(AddSmsSignRequest request) throws Exception {
        return addSmsSign(request,aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey());
    }

    @Override
    public AddSmsSignResponse addSmsSign(AddSmsSignRequest request, String accessKeyId, String accessKeySecret) throws Exception {
        return getClient(accessKeyId, accessKeySecret)
                .addSmsSign(request);
    }

    @Override
    public QuerySmsSignListResponse querySmsSignList(QuerySmsSignListRequest request) throws Exception {
        return querySmsSignList(request,aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey());
    }

    @Override
    public QuerySmsSignListResponse querySmsSignList(QuerySmsSignListRequest request, String accessKeyId, String accessKeySecret) throws Exception {
        return getClient(accessKeyId, accessKeySecret)
                .querySmsSignList(request);
    }

    @Override
    public QuerySmsSignResponse querySmsSign(QuerySmsSignRequest request) throws Exception {
        return querySmsSign(request,aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey());
    }

    @Override
    public QuerySmsSignResponse querySmsSign(QuerySmsSignRequest request, String accessKeyId, String accessKeySecret) throws Exception {
        return getClient(accessKeyId, accessKeySecret)
                .querySmsSign(request);
    }

    @Override
    public QuerySmsTemplateListResponse querySmsTemplateList(QuerySmsTemplateListRequest request) throws Exception {
        return querySmsTemplateList(request,aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey());
    }

    @Override
    public QuerySmsTemplateListResponse querySmsTemplateList(QuerySmsTemplateListRequest request, String accessKeyId, String accessKeySecret) throws Exception {
        return getClient(accessKeyId, accessKeySecret)
                .querySmsTemplateList(request);
    }

    @Override
    public QuerySmsTemplateResponse querySmsTemplate(QuerySmsTemplateRequest request) throws Exception {
        return querySmsTemplate(request,aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey());
    }

    @Override
    public QuerySmsTemplateResponse querySmsTemplate(QuerySmsTemplateRequest request, String accessKeyId, String accessKeySecret) throws Exception {
        return getClient(accessKeyId, accessKeySecret)
                .querySmsTemplate(request);
    }

    @Override
    public ListTagResourcesResponse listTagResources(ListTagResourcesRequest request) throws Exception {
        return listTagResources(request,aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey());
    }

    @Override
    public ListTagResourcesResponse listTagResources(ListTagResourcesRequest request, String accessKeyId, String accessKeySecret) throws Exception {
        return getClient(accessKeyId, accessKeySecret)
                .listTagResources(request);
    }
}
