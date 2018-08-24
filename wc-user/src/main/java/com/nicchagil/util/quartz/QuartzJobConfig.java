package com.nicchagil.util.quartz;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import com.nicchagil.util.quartz.job.MyFirstExerciseJob;
import com.nicchagil.util.quartz.job.MySecondExerciseJob;

@Configuration
public class QuartzJobConfig {

    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "myFirstExerciseJobBean")
    public JobDetailFactoryBean myFirstExerciseJobBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setName("general-myFirstExerciseJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setJobClass(MyFirstExerciseJob.class);
        jobDetail.setDurability(true);
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "myFirstExerciseJobTrigger")
    public CronTriggerFactoryBean myFirstExerciseJobTrigger(@Qualifier("myFirstExerciseJobBean") JobDetailFactoryBean myFirstExerciseJobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(myFirstExerciseJobBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-myFirstExerciseJobTrigger");
        return tigger;
    }

    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "mySecondExerciseJobBean")
    public JobDetailFactoryBean mySecondExerciseJobBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setName("general-mySecondExerciseJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setJobClass(MySecondExerciseJob.class);
        jobDetail.setDurability(true);
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "mySecondExerciseJobTrigger")
    public CronTriggerFactoryBean mySecondExerciseJobTrigger(@Qualifier("mySecondExerciseJobBean") JobDetailFactoryBean mySecondExerciseJobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(mySecondExerciseJobBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-mySecondExerciseJobTrigger");
        return tigger;
    }

}
