package www.bwsensing.com.common.clientobject;

import java.io.Serializable;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class RSAKeyCO implements Serializable {
    private String publicKey;
    private String privateKey;

    public RSAKeyCO() {
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
