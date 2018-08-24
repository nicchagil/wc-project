package com.nicchagil.util.quartz;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Arrays;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.google.common.collect.Lists;

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
    public SchedulerFactoryBean schedulerFactory(Trigger... fromSpringTriggers) {
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
        bean.setTriggers(this.mergeTriggers(fromSpringTriggers, this.getOriginalTriggers()));
        
        return bean;
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
