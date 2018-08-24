package com.nicchagil.util.quartz;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

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
    public SchedulerFactoryBean schedulerFactory( Trigger... triggers) {
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
        // 注册触发器
        bean.setTriggers(triggers);
        return bean;
    }

}
