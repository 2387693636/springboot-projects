package com.xq.business.mapper;

import com.xq.business.pojo.Check;
import com.xq.business.vo.CheckVO;

import java.util.List;

public interface CheckMapper {
    int deleteByPrimaryKey(String checkid);

    int insert(Check record);

    int insertSelective(Check record);

    Check selectByPrimaryKey(String checkid);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);

    //查出所有Check 信息
    List<Check> queryAllCheck(CheckVO checkVO);
}