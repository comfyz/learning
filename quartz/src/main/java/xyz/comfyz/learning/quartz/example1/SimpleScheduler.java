package xyz.comfyz.learning.quartz.example1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author :    comfy
 * @date :      2018-04-19 15:54:16
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public class SimpleScheduler {

    private final Logger logger = LoggerFactory.getLogger(SimpleScheduler.class);

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        new SimpleScheduler().run();
    }

    private void run() throws SchedulerException, InterruptedException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
        Date runTime = DateBuilder.evenMinuteDateAfterNow();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "gourp1").startNow().build();
        scheduler.scheduleJob(job, trigger);
        logger.info("job start");
        scheduler.start();
        Thread.sleep(10000);
        logger.info("shutdown");
        scheduler.shutdown(true);
    }

}
