package xyz.comfyz.learning.quartz.example1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author :    comfy
 * @date :      2018-04-19 15:51:47
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public class HelloJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.error("Hello Quartz!");
    }
}
