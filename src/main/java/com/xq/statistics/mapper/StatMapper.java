package com.xq.statistics.mapper;

import java.util.List;

import com.xq.statistics.pojo.BaseEntity;
/**
 * @auther: xq2580z
 * @date: 2020/3/12 14:29
 * @description: statistics统计
 */
public interface StatMapper {

	/**
	 * 查询客户地区的数据
	 * @return
	 */
	List<BaseEntity> queryCustomerAreaStat();

	/**
	 * 查询业务员年度业绩
	 * @param year
	 * @return
	 */
	List<BaseEntity> queryOpernameYearGradeStat(String year);

	/**
	 * 加载公司年度统计数据
	 * @param year
	 * @return
	 */
	List<Double> queryCompanyYearGradeStat(String year);

}
