package com.xq.business.service;

import com.xq.business.pojo.Car;
import com.xq.business.vo.CarVO;
import com.xq.system.vo.TableDataVO;

/**
 * @auther: xq2580z
 * @date: 2020/3/13 13:46
 * @description: CarService
 */
public interface CarService {
    //查询所有车辆
    public TableDataVO queryAllCar(CarVO carVO);

    // 添加车辆
    public void addCar(CarVO carVO);

    //修改车辆
    public void updateCar(CarVO carVO);

    //根据id删除车辆
    public void deleteCar(String carnumber);

    //批量删除车辆
    public void deletePartyCar(String[] carnumbers);

    //根据车牌号查询
    public Car queryCarByCarNumber(String carnumber);
}
