package com.nicchagil.util.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nicchagil.util.mail.reliability.MailLogService;
import com.nicchagil.util.spring.ApplicationContextUtils;

@Component
public class MyThirdExerciseJob implements Job {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        this.myJobBusinessMethod();
    }

    public void myJobBusinessMethod() {
        this.logger.info("叁，触发啦");
        MailLogService mailLogService = ApplicationContextUtils.getBean(MailLogService.class);
        mailLogService.sendRemainMail();
    }

}
