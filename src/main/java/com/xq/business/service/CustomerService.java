package com.xq.business.service;

import com.xq.business.pojo.Customer;
import com.xq.business.vo.CustomerVO;
import com.xq.system.vo.TableDataVO;

import java.util.List;

/**
 * @auther: xq2580z
 * @date: 2020/3/13 13:46
 * @description: 客户管理的服务接口 CustomerService
 */

public interface CustomerService {
    //查询所有客户
    public TableDataVO queryAllCustomer(CustomerVO customerVO);

    //添加客户
    public void addCustomer(CustomerVO customerVO);

    //修改客户
    public void updateCustomer(CustomerVO customerVO);

    //根据id删除客户
    public void deleteCustomer(String identity);

    //批量删除客户
    public void deletePartyCustomer(String[] identitys);

    //根据身份证号查询客户信息
    public Customer queryCustomerByIdentity(String identity);

    //查询客户数据返回集合
    public List<Customer> queryAllCustomerForList(CustomerVO customerVO);

}
