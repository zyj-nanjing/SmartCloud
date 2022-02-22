package www.bwsensing.com.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.system.api.UserAuthService;
import www.bwsensing.com.system.dto.command.UserLoginCmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Validated
@Slf4j
@CrossOrigin
@RequestMapping("/main/api")
@RestController
public class AuthenticationController {

    @Autowired
    private UserAuthService authService;


    @PostMapping("/login")
    public SingleResponse<String> login(@Valid @RequestBody UserLoginCmd loginCmd){
        return authService.login(loginCmd);
    }

    /**
     * 登出
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public Response logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null != auth) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        //重定向到指定页面
        return authService.loginOut();
    }
}
