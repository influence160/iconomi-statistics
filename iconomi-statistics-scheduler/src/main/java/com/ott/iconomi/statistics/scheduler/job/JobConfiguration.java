package com.ott.iconomi.statistics.scheduler.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.text.ParseException;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class JobConfiguration {
//
//    @Bean
//    public JobDetail jobDetail() {
//        return JobBuilder.newJob().ofType(DataImporterJob.class)
//                .storeDurably()
//                .withIdentity("DataImporterJob")
//                .build();
//    }

    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DataImporterJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public CronTrigger jobTrigger(@Value("${iconomi.statistics.scheduler.cron-expression}") String cronExpression
                                 , JobDetail jobDetail) throws ParseException {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setName("dataImporter");
        cronTriggerFactoryBean.setCronExpression(cronExpression);
        cronTriggerFactoryBean.setJobDetail(jobDetail);
//        cronTriggerFactoryBean.setBeanName("dataImporterJob");
        cronTriggerFactoryBean.afterPropertiesSet();
        return cronTriggerFactoryBean.getObject();
    }

//    @Bean
//    public Trigger trigger2(JobDetail jobDetail) {
//        return TriggerBuilder.newTrigger().forJob(jobDetail)
//                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(5))
//                .build();
//    }
}
