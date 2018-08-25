package com.nicchagil.util.quartz;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Arrays;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.google.common.collect.Lists;
import com.nicchagil.util.quartz.job.MyThirdExerciseJob;

@Configuration
public class QuartzConfig {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier(value = "primaryDataSource")
    private DataSource primaryDataSource;

    /**
     * 调度器工厂Bean
     */
    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(Trigger... triggers) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();

        Properties p = new Properties();
        try {
            p.load(this.getClass().getClassLoader().getResourceAsStream("com/nicchagil/util/quartz/quartz.properties"));
        } catch (IOException e) {
           this.logger.error("加载quartz.properties失败", e);
           throw new Error(e);
        }
        bean.setQuartzProperties(p);
        
        // 设置数据源
        bean.setDataSource(primaryDataSource);

        // 覆盖已存在的任务
        bean.setOverwriteExistingJobs(true);
        // 延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(15);
        
        /* 注册触发器 */
        bean.setTriggers(triggers);
        
        return bean;
    }
    
	/**
	 * Quartz调度器
	 */
	@Bean
	public Scheduler scheduler(Trigger... triggers) {
		Scheduler scheduler = schedulerFactory(triggers).getScheduler();
		
		final String JOB_DETAIL_IDENTIRY = "MyThirdExerciseJob";
		final String TRIGGER_IDENTITY = JOB_DETAIL_IDENTIRY + "Trigger";
		
		// 实例化任务
		JobDetail jobDetail = JobBuilder.newJob(MyThirdExerciseJob.class)
				.withIdentity(JOB_DETAIL_IDENTIRY, null).build();

		// 实例化触发器
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_IDENTITY, null)
				.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();

		/* 注册任务 */
		try {
			if (scheduler.checkExists(trigger.getKey())) {
				scheduler.unscheduleJob(trigger.getKey());
			}
			
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			throw new RuntimeException("创建定时任务失败", e);
		}

		return scheduler;
	}
    
    /**
     * 合并数据 
     */
    public Trigger[] mergeTriggers(Trigger[]... triggerArrays) {
    	if (triggerArrays == null || triggerArrays.length == 0) {
    		return new Trigger[] {};
    	}
    	
    	List<Trigger> triggerList = Lists.newArrayList();
    	for (Trigger[] triggerArray : triggerArrays) {
    		if (Arrays.isNullOrEmpty(triggerArray)) {
    			continue;
    		}
    		
    		triggerList.addAll(Lists.newArrayList(triggerArray));
    	}
    	
    	return this.toTriggerArray(triggerList);
    }
    
    /**
     * 获取继承Job的全部触发器
     */
    public Trigger[] getOriginalTriggers() {
    	List<Trigger> triggerList = Lists.newArrayList();
    	
    	Trigger[] triggerArray = new Trigger[triggerList.size()];
    	if (CollectionUtils.isNotEmpty(triggerList)) {
    		triggerList.toArray(triggerArray);
    	}
    	
    	return triggerArray;
    }
    
    /**
     * Trigger集合转换为Trigger数据
     */
    public Trigger[] toTriggerArray(List<Trigger> triggerList) {
    	if (triggerList == null || triggerList.size() == 0) {
    		return new Trigger[] {};
    	}
    	
    	Trigger[] triggerArray = new Trigger[triggerList.size()];
    	if (CollectionUtils.isNotEmpty(triggerList)) {
    		triggerList.toArray(triggerArray);
    	}
    	return triggerArray;
    }

}
