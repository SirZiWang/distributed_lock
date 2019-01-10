package com.wangzi.distributed.lock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangzi.distributed.lock.service.GoodsInfoService;

@RestController
public class GoodsInfoController {
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	@RequestMapping(value="/buy", method = RequestMethod.GET)
	public String buyGoods(String id){
		 new Thread() {
	            public void run() {
	                for (int i = 0; i < 100; i++) {
	                	goodsInfoService.buyGoods(id);
	                }
	            }
	        }.start();
		return "Success";
	}
}
