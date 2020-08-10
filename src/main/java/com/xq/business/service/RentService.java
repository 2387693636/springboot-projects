package com.xq.business.service;

import com.xq.business.pojo.Rent;
import com.xq.business.vo.RentVO;
import com.xq.system.vo.TableDataVO;

/**
 * @auther: xq2580z
 * @date: 2020/3/13 13:46
 * @description: 租赁的服务接口 RentService
 */

public interface RentService {

    //添加出租单信息
    void addRent(RentVO rentVO);

    //查询 所有租赁信息 返回到前端显示

    TableDataVO queryAllRent(RentVO rentVO);

    //修改出租单
    void updateRent(RentVO rentVO);

    //删除出租单
    void deleteRent(String rentid);

    //根据出租单号查询出租单信息
    Rent queryRentByRentId(String rentid);

}
