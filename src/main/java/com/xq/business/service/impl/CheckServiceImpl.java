package com.xq.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xq.business.mapper.CarMapper;
import com.xq.business.mapper.CheckMapper;
import com.xq.business.mapper.CustomerMapper;
import com.xq.business.mapper.RentMapper;
import com.xq.business.pojo.Car;
import com.xq.business.pojo.Check;
import com.xq.business.pojo.Customer;
import com.xq.business.pojo.Rent;
import com.xq.business.service.CheckService;
import com.xq.business.vo.CheckVO;
import com.xq.system.constant.MyConstant;
import com.xq.system.pojo.User;
import com.xq.system.utils.RandomToolsUtil;
import com.xq.system.utils.WebToolsUtil;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: xq2580z
 * @date: 2020/3/13 13:58
 * @description:
 */
@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private CarMapper carMapper;

    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        //查询出租单
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        //查询客户
        Customer customer = this.customerMapper.selectByPrimaryKey(rent.getIdentity());
        //查询车辆
        Car car = this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        //组装Check
        Check check = new Check();
        check.setCheckid(RandomToolsUtil.createRandomStringUseTime(MyConstant.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        User user = (User) WebToolsUtil.getSession().getAttribute("user");
        check.setOpername(user.getRealname());

        //新建map集合 存储 rent  customer  car  check 信息
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("rent", rent);
        map.put("customer", customer);
        map.put("car", car);
        map.put("check", check);

        return map;
    }

    @Override
    public void addCheck(CheckVO checkVO) {
        this.checkMapper.insertSelective(checkVO);
        //更改出租单的状态
        Rent rent = this.rentMapper.selectByPrimaryKey(checkVO.getRentid());
        rent.setRentflag(MyConstant.RENT_BACK_TRUE);
        this.rentMapper.updateByPrimaryKeySelective(rent);
        //更改汽车的状态
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        car.setIsrenting(MyConstant.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public TableDataVO queryAllCheck(CheckVO checkVO) {
        Page<Object> objects = PageHelper.startPage(checkVO.getPage(), checkVO.getLimit());
        List<Check> data = checkMapper.queryAllCheck(checkVO);
        TableDataVO tableDataVO = new TableDataVO(objects.getTotal(), data);
        return tableDataVO;
    }

    @Override
    public void updateCheck(CheckVO checkVO) {
        this.checkMapper.updateByPrimaryKeySelective(checkVO);
    }
}
