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
                //??????
                .and()
                // ??????session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // ??????????????????????????????????????????jwt
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager(),redisService), UsernamePasswordAuthenticationFilter.class)
                //????????????????????????
                .exceptionHandling()
                //???????????????????????????????????????token
                .authenticationEntryPoint(authenticationEntryPoint)
                //??????????????????????????????
                .accessDeniedHandler(accessDeniedHandler);
        // ========= start: ?????? justAuth-spring-security-starter ???????????? =========
        // ?????? Auth2AutoConfigurer ??? OAuth2(justAuth) login ??????.
        http.apply(this.auth2AutoConfigurer);

    }
}