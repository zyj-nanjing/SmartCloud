package www.bwsensing.com.common.quartz.util;

import org.quartz.JobExecutionContext;
import www.bwsensing.com.common.quartz.database.dataobject.SysJob;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author ruoyi
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
