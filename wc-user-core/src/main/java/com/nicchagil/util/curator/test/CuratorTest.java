package com.nicchagil.util.curator.test;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private CuratorFramework client = null;
	
	public CuratorFramework getConnection() {
		/* 获取连接，如果Curator与ZooKeeper的版本不对会报：java.lang.NoSuchMethodError: org.apache.zookeeper.server.quorum.flexible.QuorumMaj.<init>(Ljava/util/Map;)V */
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
		client.start();
		
		this.logger.info("getted the connection.");
		return client;
	}
	
	@Test
	public void createTest() {
		CuratorFramework client = this.getConnection();
		try {
			client.create().creatingParentsIfNeeded().forPath("/nicchagil/node1", "hello world".getBytes());
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
	}

}
