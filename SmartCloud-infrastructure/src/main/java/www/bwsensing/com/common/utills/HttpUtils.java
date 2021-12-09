package www.bwsensing.com.common.utills;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author macos-zyj
 */
@Slf4j
public class HttpUtils {

    private static final String ENCODING = "UTF-8";


    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            log.error("http request failed",e);
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                log.error("",e);
            }
        }
        return responseText;
    }

    public static String get(String url, Map<String, String> paramsMap) {
        return getMethod(url,paramsMap,true);
    }


    public static String delete(String url,Map<String,String> paramsMap){
        return getMethod(url,paramsMap,false);
    }


    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param contentType 编码类型
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String contentType)
    {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try
        {
            String urlNameString = url + "?" + param;
            log.info("sendGet - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), contentType);
            in = new BufferedReader(inputStreamReader);
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            log.info("recv - {}", result);
            inputStreamReader.close();
            in.close();
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception ex)
            {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }


    private static String getMethod(String url,Map<String,String> paramsMap,Boolean isGet){
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            StringBuilder getUrl = new StringBuilder(url + "?");
            if (paramsMap != null) {
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    getUrl.append(param.getKey()).append("=").append(URLEncoder.encode(param.getValue(), ENCODING)).append("&");
                }
            }
            if (isGet ){
                HttpGet method = new HttpGet(getUrl.toString());
                response = client.execute(method);
            } else {
                HttpDelete method = new HttpDelete(getUrl.toString());
                response = client.execute(method);
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            log.error("http request failed",e);
        } finally {
            try {
                assert response != null;
                response.close();
            } catch (Exception e) {
                log.error("",e);
            }
        }
        return responseText;
    }

    /**
     * post请求参数为json格式
     * @param url
     * @param json
     * @return
     */
    public static String jsonPost(String url, String json) {
        String returnValue = "这是默认返回值，接口调用失败";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try{
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json,"utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            //第四步：发送HttpPost请求，获取返回值
            returnValue = httpClient.execute(httpPost,responseHandler);

        }
        catch(Exception e)
        {
            log.warn("Http Json Post error info:{}",e.getLocalizedMessage());
        }

        finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.warn("HttpClient close error info:{}",e.getLocalizedMessage());
            }
        }
        //第五步：处理返回值
        return returnValue;
    }


}