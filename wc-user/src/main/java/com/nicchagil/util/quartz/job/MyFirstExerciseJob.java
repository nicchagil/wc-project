package com.nicchagil.util.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyFirstExerciseJob implements Job {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        this.myJobBusinessMethod();
    }

    public void myJobBusinessMethod() {
        this.logger.info("壹，触发啦");
    }

}
