package com.nicchagil.util.curator.test;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorTest {

	private static Logger logger = LoggerFactory.getLogger(CuratorTest.class);
	
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
	
	@Test
	public void listeningTest() {
		CuratorFramework client = this.getConnection();
		
		NodeCache nodeCache = new NodeCache(client, "/nicchagil", false);
		try {
			nodeCache.start(true);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		
		/* 添加监听 */
		nodeCache.getListenable().addListener(new NodeCacheListener() {
			@Override
			public void nodeChanged() throws Exception {
				logger.info("node {} changed.", nodeCache.getCurrentData().getPath());
			}
		});
		
		/* 添加子节点 */
		try {
			client.create().creatingParentsIfNeeded().forPath("/nicchagil/node2", "hello world".getBytes());
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
	}

}
