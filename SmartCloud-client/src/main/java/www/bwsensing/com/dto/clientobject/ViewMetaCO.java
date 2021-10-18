package www.bwsensing.com.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
/**
 * @author macos-zyj
 */
@Data
@SuppressWarnings("all")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewMetaCO extends DTO {
    /**标题*/
    private String title;
    /**图标*/
    private String icon;

    public ViewMetaCO() {
    }

    public ViewMetaCO(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
}
