package com.xq.business.mapper;

import com.xq.business.pojo.Car;
import com.xq.business.vo.CarVO;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(String carnumber);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(String carnumber);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    //查询所有车车信息
    List<Car> queryAllCar(CarVO carVO);
}