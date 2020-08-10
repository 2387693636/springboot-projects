package com.xq.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xq.business.mapper.CarMapper;
import com.xq.business.pojo.Car;
import com.xq.business.service.CarService;
import com.xq.business.vo.CarVO;
import com.xq.system.constant.MyConstant;
import com.xq.system.utils.FileToolUtil;
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
public class CarServiceImpl implements CarService {
    //注入
    @Autowired
    private CarMapper carMapper;

    @Override
    public TableDataVO queryAllCar(CarVO carVO) {
        Page<Object> objects = PageHelper.startPage(carVO.getPage(), carVO.getLimit());
        List<Car> data = carMapper.queryAllCar(carVO);
        return new TableDataVO(objects.getTotal(), data);
    }

    @Override
    public void addCar(CarVO carVO) {
        carMapper.insertSelective(carVO);
    }

    @Override
    public void updateCar(CarVO carVO) {
        carMapper.updateByPrimaryKeySelective(carVO);
    }

    @Override
    public void deleteCar(String carnumber) {
        //先删除图片
        Car car = this.carMapper.selectByPrimaryKey(carnumber);
        //如果不是默认图片就删除
        if (!car.getCarimg().equals(MyConstant.DEFAULT_CAR_IMG)) {
            FileToolUtil.deleteFileUsePath(car.getCarimg());
        }
        //删除数据库的数据
        carMapper.deleteByPrimaryKey(carnumber);
    }

    @Override
    public void deletePartyCar(String[] carnumbers) {
        //遍历删除
        for (String carnumber : carnumbers) {
            deleteCar(carnumber);
        }
    }

    @Override
    public Car queryCarByCarNumber(String carnumber) {
        return carMapper.selectByPrimaryKey(carnumber);
    }
}
