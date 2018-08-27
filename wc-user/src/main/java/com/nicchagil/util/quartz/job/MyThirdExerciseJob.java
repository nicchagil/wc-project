package com.nicchagil.util.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nicchagil.util.mail.reliability.MailLogSendOpsService;
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
        MailLogSendOpsService mailLogService = ApplicationContextUtils.getBean(MailLogSendOpsService.class);
        mailLogService.sendRemainMail();
    }

}
