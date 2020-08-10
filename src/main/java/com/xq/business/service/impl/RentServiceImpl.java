package com.xq.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xq.business.mapper.CarMapper;
import com.xq.business.mapper.RentMapper;
import com.xq.business.pojo.Car;
import com.xq.business.pojo.Customer;
import com.xq.business.pojo.Rent;
import com.xq.business.service.RentService;
import com.xq.business.vo.RentVO;
import com.xq.system.constant.MyConstant;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: xq2580z
 * @date: 2020/3/13 13:57
 * @description:
 */
@Service
public class RentServiceImpl implements RentService {

    //注入
    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public void addRent(RentVO rentVO) {
        this.rentMapper.insertSelective(rentVO);
        //更改车辆的出租状态
        Car car = new Car();
        car.setCarnumber(rentVO.getCarnumber());
        car.setIsrenting(MyConstant.RENT_CAR_TRUE);
        carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public TableDataVO queryAllRent(RentVO rentVO) {
        //使用PageHelper
        Page<Object> objects = PageHelper.startPage(rentVO.getPage(), rentVO.getLimit());
        List<Customer> data = rentMapper.queryAllRent(rentVO);
        TableDataVO tableDataVO = new TableDataVO(objects.getTotal(), data);
        //返回给前端 nice
        return tableDataVO;
    }

    @Override
    public void updateRent(RentVO rentVO) {
        this.rentMapper.updateByPrimaryKeySelective(rentVO);
    }

    @Override
    public void deleteRent(String rentid) {
        //更改汽车的状态
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        car.setIsrenting(MyConstant.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(car);
        //删除出租单
        this.rentMapper.deleteByPrimaryKey(rentid);
    }

    @Override
    public Rent queryRentByRentId(String rentid) {
        return this.rentMapper.selectByPrimaryKey(rentid);
    }
}
