package www.bwsensing.com.common.utills;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.constant.TokenConstant;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bxsheng
 * since JDK 1.8
 */
@Slf4j
@Component
public class TokenUtils  implements InitializingBean {

    private static String SECRET ;
    private static String ISS ;
    /**过期时间3小时**/
    private static Long EXPIRATION = (long) 60 * 60;

    private static final String ROLE = "role";

    @Value("${system.token.secret}")
    private String tokenSecret;
    @Value("${system.token.iss}")
    private String tokenIss;
    @Value("${system.token.exist_time}")
    private String tokenExistTime;

    /**
     * 创建token
     * @param username
     * @param role
     * @param isRememberMe
     * @return
     */
    public static String createToken(String username, String role, boolean isRememberMe){
        Map<String, Object> map = new HashMap<>(16);
        map.put(ROLE, role);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .compact();
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public static String getUserName(String token){
        String username;
        try {
            username = getTokenBody(token).getSubject();
        } catch (    Exception e){
            username = null;
        }
        return username;
    }

    public static String getUserRole(String token){
        return (String) getTokenBody(token).get(ROLE);
    }

    private static Claims getTokenBody(String token){
        try{
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch(ExpiredJwtException | IllegalArgumentException |
                SignatureException | UnsupportedJwtException | MalformedJwtException e){
            log.warn(e.getMessage());
            throw new BizException("TOKEN_EXPIRATION","token已过期");
        }
    }

    /**
     * 是否已过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token){
        try{
            return getTokenBody(token).getExpiration().before(new Date());
        } catch(Exception e){
            log.error("token expiration error  Token:{}",token);
        }
        return true;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        TokenUtils.ISS = this.tokenIss;
        TokenUtils.SECRET = this.tokenSecret;
        TokenUtils.EXPIRATION = Long.parseLong(this.tokenExistTime)* TokenUtils.EXPIRATION;
    }
}
