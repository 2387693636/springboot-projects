package com.xq.statistics.service;

import java.util.List;

import com.xq.statistics.pojo.BaseEntity;

/**
 * @auther: xq2580z
 * @date: 2020/3/12 14:29
 * @description: statistics统计接口
 */
public interface StatService {

	/**
	 * 查询客户地区的数据
	 * @return
	 */
	List<BaseEntity> loadCustomerAreaStatList();

	/**
	 * 业务员年度统计数据
	 * @return
	 */
	List<BaseEntity> loadOpernameYearGradeStatList(String year);

	/**
	 * 加载公司年度统计数据
	 * @param year
	 * @return
	 */
	List<Double> loadCompanyYearGradeStatList(String year);

	
}
