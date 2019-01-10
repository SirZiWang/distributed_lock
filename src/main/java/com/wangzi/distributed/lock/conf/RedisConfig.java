package com.wangzi.distributed.lock.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {
	@Value("${spring.redis.host}") 
	private String host; 
	@Value("${spring.redis.port}") 
	private int port; 
	@Value("${spring.redis.timeout}") 
	private int timeout; 
	@Value("${spring.redis.pool.max-active}") 
	private int maxActive; 
	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;
	@Value("${spring.redis.pool.min-idle}") 
	private int minIdle; 
	@Value("${spring.redis.pool.max-wait}") 
	private long maxWaitMillis; 

	@Bean 
	public Jedis getJedis(){ 
		Jedis jedis = new Jedis(host, port, timeout);
		System.out.println("JedisPool注入成功"); 
		System.out.println("redis地址：" + host + ":" + port); 
		return jedis; 
	}
}

