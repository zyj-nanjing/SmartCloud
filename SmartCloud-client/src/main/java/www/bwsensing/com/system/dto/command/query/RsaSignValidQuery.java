package www.bwsensing.com.system.dto.command.query;

import com.alibaba.cola.dto.Query;

import javax.validation.constraints.NotBlank;

/**
 * RSA数字签名校验
 * @author macos-zyj
 */
public class RsaSignValidQuery extends Query {
    /**
     * 16进制数据
     */
    @NotBlank(message = "16进制数据为空")
    private String hexData;

    /**
     * 签名
     */
    @NotBlank(message = "签名不能为空")
    private String sign;

    public String getHexData() {
        return hexData;
    }

    public void setHexData(String hexData) {
        this.hexData = hexData;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
