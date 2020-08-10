package com.xq.statistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq.statistics.pojo.BaseEntity;
import com.xq.statistics.mapper.StatMapper;
import com.xq.statistics.service.StatService;
/**
 * @auther: xq2580z
 * @date: 2020/3/12 14:29
 * @description: statistics统计
 */
@Service
public class StatServiceImpl implements StatService {
	
	
	@Autowired
	private StatMapper statMapper;

	@Override
	public List<BaseEntity> loadCustomerAreaStatList() {
		return statMapper.queryCustomerAreaStat();
	}

	@Override
	public List<BaseEntity> loadOpernameYearGradeStatList(String year) {
		return this.statMapper.queryOpernameYearGradeStat(year);
	}

	@Override
	public List<Double> loadCompanyYearGradeStatList(String year) {
		return this.statMapper.queryCompanyYearGradeStat(year);
	}

}
