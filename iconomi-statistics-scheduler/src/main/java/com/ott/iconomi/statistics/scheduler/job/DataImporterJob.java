package com.ott.iconomi.statistics.scheduler.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class DataImporterJob extends QuartzJobBean {

    private JobService jobService;

    public DataImporterJob(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        jobService.importData();
    }
}
