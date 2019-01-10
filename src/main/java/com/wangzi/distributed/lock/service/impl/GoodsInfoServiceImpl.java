package com.wangzi.distributed.lock.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzi.distributed.lock.dao.GoodsInfoDao;
import com.wangzi.distributed.lock.entity.GoodsInfo;
import com.wangzi.distributed.lock.rao.RedisRao;
import com.wangzi.distributed.lock.service.GoodsInfoService;
import com.wangzi.distributed.lock.util.RedisLockUtil;

import redis.clients.jedis.Jedis;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService{
	
	private static final String LOCK_KEY = "lock:goods:";
	private static final int EXPIRE_TIME = 200;
	private static final String REQUEST_ID = UUID.randomUUID().toString();
	
	@Autowired
	private GoodsInfoDao goodsInfoDao;
	@Autowired
	private RedisRao redisRao;
	@Autowired
	private Jedis jedis;
	
	@Override
	public String buyGoods(String id) {
		GoodsInfo goodsInfo = goodsInfoDao.getGoodsInfo(id);
		//获取库存
		int redisRepertory = redisRao.getRepertory(id);
		int dbRepertory = goodsInfo.getRepertory();
		if(redisRepertory <=0 && dbRepertory <= 0 ){
			System.out.println("库存不足");
			return "库存不足";
		}
		redisRao.insertRepertory(id, goodsInfo.getRepertory());
		try {
			//获取锁
			boolean lock = RedisLockUtil.tryGetDistributedLock(jedis, LOCK_KEY+id, REQUEST_ID, EXPIRE_TIME);
			if(lock){
				//更新缓存库存
				Long repertory = redisRao.updateRepertory(id);
				if(repertory < 0){
					System.out.println("库存不足");
					return "库存不足";
				}
				System.out.println("库存剩余："+ repertory);
				//更新数据库
				goodsInfoDao.updateRepertory(id);
			}
		} finally {
			//释放锁
			RedisLockUtil.releaseDistributedLock(jedis, LOCK_KEY+id, REQUEST_ID);
		}
		System.out.println("购买成功");
		return "success";
	}

}
