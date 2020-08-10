package com.xq.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xq.business.mapper.CustomerMapper;
import com.xq.business.pojo.Customer;
import com.xq.business.service.CustomerService;
import com.xq.business.vo.CustomerVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: xq2580z
 * @date: 2020/3/13 13:59
 * @description:
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    //    注入mapper
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public TableDataVO queryAllCustomer(CustomerVO customerVO) {

        //使用PageHelper
        Page<Object> objects = PageHelper.startPage(customerVO.getPage(), customerVO.getLimit());
        List<Customer> data = customerMapper.queryAllCustomer(customerVO);
        TableDataVO tableDataVO = new TableDataVO(objects.getTotal(), data);
        //返回给前端 nice
        return tableDataVO;
    }

    @Override
    public void addCustomer(CustomerVO customerVO) {
        customerMapper.insertSelective(customerVO);
    }

    @Override
    public void updateCustomer(CustomerVO customerVO) {
        customerMapper.updateByPrimaryKeySelective(customerVO);
    }

    @Override
    public void deleteCustomer(String identity) {
        customerMapper.deleteByPrimaryKey(identity);
    }

    @Override
    public void deletePartyCustomer(String[] identitys) {
        //遍历删除
        for (String identity : identitys) {
            deleteCustomer(identity);
        }
    }

    @Override
    public Customer queryCustomerByIdentity(String identity) {
        return customerMapper.selectByPrimaryKey(identity);
    }

    @Override
    public List<Customer> queryAllCustomerForList(CustomerVO customerVO) {
        return this.customerMapper.queryAllCustomer(customerVO);
    }
}
