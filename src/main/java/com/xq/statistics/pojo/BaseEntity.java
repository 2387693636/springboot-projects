package com.xq.statistics.pojo;

/**
 * @auther: xq2580z
 * @date: 2020/3/12 14:29
 * @description: statistics统计 基本
 */
public class BaseEntity {

	private String name;
	private Double value;
	public BaseEntity() {
	}
	
	public BaseEntity(String name, Double value) {
		super();
		this.name = name;
		this.value = value;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	
}
