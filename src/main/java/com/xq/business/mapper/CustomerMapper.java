package com.xq.business.mapper;

import com.xq.business.pojo.Customer;
import com.xq.business.vo.CustomerVO;

import java.util.List;
public interface CustomerMapper {
    int deleteByPrimaryKey(String identity);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String identity);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    //查询所有的用户信息
    List<Customer> queryAllCustomer(CustomerVO customerVO);
}