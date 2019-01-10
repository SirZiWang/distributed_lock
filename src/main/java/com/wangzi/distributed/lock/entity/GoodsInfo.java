package com.wangzi.distributed.lock.entity;

import java.io.Serializable;

public class GoodsInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Integer price;
	private Integer repertory;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getRepertory() {
		return repertory;
	}
	public void setRepertory(Integer repertory) {
		this.repertory = repertory;
	}
	
	@Override
	public String toString() {
		return "GoodsInfo [id=" + id + ", name=" + name + ", price=" + price + ", repertory=" + repertory + "]";
	}
	
}
