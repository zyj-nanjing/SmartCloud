package www.bwsensing.com.project.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
@SuppressWarnings("all")
public class ProjectCO extends DTO {
    /**主键*/
    private Integer id;
    /**图片*/
    private String picture;
    /**名称*/
    private String name;
    /**描述*/
    private String comment;
    /**结构物集合*/
    private List<StructureCO> structureArray;
    /**人员集合*/
    private List<ProjectMemberCO> memberArray;
    /**创建时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
