package www.bwsensing.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import www.bwsensing.com.common.utills.StringUtils;

/**
 * swagger 接口
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController
{
    @GetMapping()
    public String index()
    {
        return redirect("/swagger-ui/index.html");
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }
}
