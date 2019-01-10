package com.wangzi.distributed.lock.rao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRao {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public Long updateRepertory(String id){
		String key = "goodsInfo:" + id;
		Long increment = stringRedisTemplate.opsForValue().increment(key, -1L);
		if(increment<=0){
			return 0L;
		}
		return increment;
	}
	
	public int getRepertory(String id){
		String key = "goodsInfo:" + id;
		String repertory = stringRedisTemplate.opsForValue().get(key);
		if(repertory != null){
			return Integer.valueOf(repertory);
		}
		return 0;
	}
	
	public void insertRepertory(String id, int repertory){
		String key = "goodsInfo:" + id;
		stringRedisTemplate.opsForValue().set(key, String.valueOf(repertory));
	}
}
