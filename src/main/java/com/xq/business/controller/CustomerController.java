package com.xq.business.controller;

import com.xq.business.service.CustomerService;
import com.xq.business.vo.CustomerVO;
import com.xq.system.vo.ResultVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @auther: xq2580z
 * @date: 2020/3/13  13:57
 * @description: CustomerController 客户控制器
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 加载客户列表返回TableDataVO
     */
    @RequestMapping("loadAllCustomer")
    public TableDataVO loadAllCustomer(CustomerVO customerVo) {
        return this.customerService.queryAllCustomer(customerVo);
    }

    /**
     * 添加客户
     */
    @RequestMapping("addCustomer")
    public ResultVO addCustomer(CustomerVO customerVo) {
        try {
            customerVo.setCreatetime(new Date());
            this.customerService.addCustomer(customerVo);
            return ResultVO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.ADD_ERROR;
        }
    }

    /**
     * 修改客户
     */
    @RequestMapping("updateCustomer")
    public ResultVO updateCustomer(CustomerVO customerVo) {
        try {
            this.customerService.updateCustomer(customerVo);
            return ResultVO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.UPDATE_ERROR;
        }
    }

    /**
     * 删除客户
     */
    @RequestMapping("deleteCustomer")
    public ResultVO deleteCustomer(CustomerVO customerVo) {
        try {
            this.customerService.deleteCustomer(customerVo.getIdentity());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }

    /**
     * 批量删除客户
     */
    @RequestMapping("deletePartyCustomer")
    public ResultVO deletePartyCustomer(CustomerVO customerVo) {
        try {
            this.customerService.deletePartyCustomer(customerVo.getIds());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }
}
