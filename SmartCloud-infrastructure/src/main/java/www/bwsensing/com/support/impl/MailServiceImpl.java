package www.bwsensing.com.support.impl;

import com.aliyuncs.dm.model.v20151123.BatchSendMailRequest;
import com.aliyuncs.dm.model.v20151123.BatchSendMailResponse;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.config.AliCloudProperties;
import www.bwsensing.com.common.config.AliCloudMailProperties;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.support.AbstractMailService;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public  class MailServiceImpl extends AbstractMailService {
    @Resource
    private AliCloudMailProperties mailProperties;

    @Resource
    private AliCloudProperties aliCloudProperties;


    @Override
    public SingleSendMailResponse sendMailRequest(SingleSendMailRequest sendMailRequest)
            throws  ClientException {
        return sendMailRequest(sendMailRequest,aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey(), mailProperties.getRegionId());
    }

    @Override
    public SingleSendMailResponse sendMailRequest(SingleSendMailRequest sendMailRequest,
                                                  String accessKeyId, String accessKeySecret,String locationId)
            throws ClientException {
        if (StringUtils.isEmpty(locationId)){
            return getRegionClientProfile(accessKeyId,accessKeySecret).getAcsResponse(sendMailRequest);
        } else {
            return getRegionClientProfile(accessKeyId, accessKeySecret,locationId).getAcsResponse(sendMailRequest);
        }
    }

    @Override
    public BatchSendMailResponse sendMailBatchRequest(BatchSendMailRequest sendBatchMailRequest)
            throws ClientException {
        return sendMailBatchRequest(sendBatchMailRequest,aliCloudProperties.getAccessKey(),
                aliCloudProperties.getSecretKey() , mailProperties.getRegionId());
    }


    @Override
    public BatchSendMailResponse sendMailBatchRequest(BatchSendMailRequest sendBatchMailRequest,
                                                      String accessKeyId, String accessKeySecret,String locationId)
            throws ClientException {
        if (StringUtils.isEmpty(locationId)){
            return getRegionClientProfile(accessKeyId,accessKeySecret).getAcsResponse(sendBatchMailRequest);
        } else {
            return getRegionClientProfile(accessKeyId, accessKeySecret,locationId).getAcsResponse(sendBatchMailRequest);
        }
    }
}
