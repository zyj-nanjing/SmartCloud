package www.bwsensing.com.gatewayimpl.tcp.impl;

import javax.annotation.Resource;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.config.AlertConfig;
import www.bwsensing.com.common.config.AlertApiConfig;
import www.bwsensing.com.common.utills.HttpUtils;
import www.bwsensing.com.domain.device.alert.AlertRole;
import www.bwsensing.com.gatewayimpl.tcp.AlertRoleQuest;
import java.util.HashMap;
import java.util.Map;


/**
 * @author macos-zyj
 */
@Component
public class AlertRoleQuestImpl implements AlertRoleQuest {

    @Resource
    private AlertConfig alertConfig;

    @Override
    public void submitAlertRole(AlertRole alertRole) {
        String url = alertConfig.getServerHost()+ AlertApiConfig.ADD_OR_EDIT.getUrl();
        JSONObject jsonData = new JSONObject(true);
        jsonData.putAll(alertRole.toHttpRestMap());
        HttpUtils.jsonPost(url,jsonData.toJSONString());
    }

    @Override
    public void deleteAlertRole(String roleName) {
        String url = alertConfig.getServerHost()+ AlertApiConfig.DELETE.getUrl();
        Map<String,String> params = new HashMap<>(2);
        params.put("name",roleName);
        HttpUtils.delete(url,params);
    }
}
