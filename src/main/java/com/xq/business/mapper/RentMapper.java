package com.xq.business.mapper;

import com.xq.business.pojo.Customer;
import com.xq.business.pojo.Rent;
import com.xq.business.vo.RentVO;

import java.util.List;

public interface RentMapper {
    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);

    int insertSelective(Rent record);

    Rent selectByPrimaryKey(String rentid);

    int updateByPrimaryKeySelective(Rent record);

    int updateByPrimaryKey(Rent record);

    List<Customer> queryAllRent(RentVO rentVO);
}