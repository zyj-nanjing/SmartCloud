package www.bwsensing.com.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.dcenter.ums.security.core.oauth.config.Auth2AutoConfigurer;
import top.dcenter.ums.security.core.oauth.properties.Auth2Properties;
import www.bwsensing.com.common.cache.redis.RedisService;
import www.bwsensing.com.common.auth.common.jwt.JwtAccessDeniedHandler;
import www.bwsensing.com.common.auth.common.jwt.JwtAuthenticationEntryPoint;
import www.bwsensing.com.common.auth.filter.JwtAuthenticationFilter;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private Auth2AutoConfigurer auth2AutoConfigurer;

    @Resource
    private Auth2Properties auth2Properties;

    @Resource
    private JwtAccessDeniedHandler accessDeniedHandler;

    @Resource
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    private RedisService redisService;
    @Override
    public void configure(WebSecurity web) {
        //ignore
        web.ignoring().antMatchers("/open/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws  Exception{
        return  super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/main/api/**","/swagger-ui.html")
                .permitAll()
                .antMatchers(HttpMethod.GET,
                        auth2Properties.getRedirectUrlPrefix() + "/*",
                        auth2Properties.getAuthLoginUrlPrefix() + "/*",
                        "/swagger-ui/index.html","/tool/swagger",
                        "/swagger-ui/**","/swagger-resources/**",
                        "/v3/**")
                .permitAll()
                .anyRequest().authenticated()
                //授权
                .and()
                // 禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 使用自己定义的拦截机制，拦截jwt
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager(),redisService), UsernamePasswordAuthenticationFilter.class)
                //授权错误信息处理
                .exceptionHandling()
                //用户访问资源没有携带正确的token
                .authenticationEntryPoint(authenticationEntryPoint)
                //用户访问没有授权资源
                .accessDeniedHandler(accessDeniedHandler);
        // ========= start: 使用 justAuth-spring-security-starter 必须步骤 =========
        // 添加 Auth2AutoConfigurer 使 OAuth2(justAuth) login 生效.
        http.apply(this.auth2AutoConfigurer);

    }
}