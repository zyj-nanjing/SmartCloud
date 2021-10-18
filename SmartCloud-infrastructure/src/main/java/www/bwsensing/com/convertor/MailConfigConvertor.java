package www.bwsensing.com.convertor;

import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import www.bwsensing.com.gatewayimpl.database.dataobject.MailConfigDO;

/**
 * @author macos-zyj
 */
public class MailConfigConvertor {
    public static SingleSendMailRequest initNoReplyRequest(MailConfigDO mailConfig){
        SingleSendMailRequest request = new SingleSendMailRequest();
        request.setAccountName(mailConfig.getAccountName());
        request.setSubject(mailConfig.getSubscribe());
        request.setFromAlias(mailConfig.getFromAlias());
        request.setTagName(mailConfig.getTagName());
        request.setReplyToAddress(false);
        request.setClickTrace("0");
        request.setAddressType(0);
        return request;
    }


}
