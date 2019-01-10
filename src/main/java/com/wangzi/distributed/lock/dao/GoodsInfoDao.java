package com.wangzi.distributed.lock.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wangzi.distributed.lock.entity.GoodsInfo;

@Repository
public interface GoodsInfoDao {
	public GoodsInfo getGoodsInfo(@Param("id") String id);
	public void updateRepertory(@Param("id") String id);
}
