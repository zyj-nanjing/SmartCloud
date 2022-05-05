package www.bwsensing.com.common.quartz.database.dataobject;

import www.bwsensing.com.common.constant.ScheduleConstants;
import www.bwsensing.com.common.quartz.util.CronUtils;
import www.bwsensing.com.common.utills.StringUtils;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度表 sys_job
 * 
 * @author ruoyi
 */
public class SysJob  implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long jobId;

    /** 任务名称 */
    private String jobName;

    /** 任务组名 */
    private String jobGroup;

    /**
     * 业务线
     */
    private String businessLine;

    /** 调用目标字符串 */
    private String invokeTarget;

    /** cron执行表达式 */
    private String cronExpression;

    /** cron计划策略 0=默认,1=立即触发执行,2=触发一次执行,3=不触发立即执行*/
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /** 是否并发执行（0允许 1禁止） */
    private String concurrent;

    /** 任务状态（0正常 1暂停） */
    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    public Long getJobId()
    {
        return jobId;
    }

    public void setJobId(Long jobId)
    {
        this.jobId = jobId;
    }


    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobGroup()
    {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }

    public String getBusinessLine() {
        return businessLine;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }

    public String getInvokeTarget()
    {
        return invokeTarget;
    }

    public void setInvokeTarget(String invokeTarget)
    {
        this.invokeTarget = invokeTarget;
    }

    public String getCronExpression()
    {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }

    public Date getNextValidTime()
    {
        if (StringUtils.isNotEmpty(cronExpression))
        {
            return CronUtils.getNextExecution(cronExpression);
        }
        return null;
    }

    public String getMisfirePolicy()
    {
        return misfirePolicy;
    }

    public void setMisfirePolicy(String misfirePolicy)
    {
        this.misfirePolicy = misfirePolicy;
    }

    public String getConcurrent()
    {
        return concurrent;
    }

    public void setConcurrent(String concurrent)
    {
        this.concurrent = concurrent;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysJob{");
        sb.append("jobId=").append(jobId);
        sb.append(", jobName='").append(jobName).append('\'');
        sb.append(", jobGroup='").append(jobGroup).append('\'');
        sb.append(", businessLine='").append(businessLine).append('\'');
        sb.append(", invokeTarget='").append(invokeTarget).append('\'');
        sb.append(", cronExpression='").append(cronExpression).append('\'');
        sb.append(", misfirePolicy='").append(misfirePolicy).append('\'');
        sb.append(", concurrent='").append(concurrent).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", createBy='").append(createBy).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy='").append(updateBy).append('\'');
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark='").append(remark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}