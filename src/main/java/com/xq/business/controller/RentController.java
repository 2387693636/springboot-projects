package com.xq.business.controller;

import com.xq.business.pojo.Customer;
import com.xq.business.service.CustomerService;
import com.xq.business.service.RentService;
import com.xq.business.vo.RentVO;
import com.xq.system.constant.MyConstant;
import com.xq.system.pojo.User;
import com.xq.system.utils.RandomToolsUtil;
import com.xq.system.utils.WebToolsUtil;
import com.xq.system.vo.ResultVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @auther: xq2580z
 * @date: 2020/3/13  13:57
 * @description: RentController 出租管理的控制器
 */
@Controller
@RequestMapping("rent")
public class RentController {

    //注入 service
    @Autowired
    private RentService rentService;

    @Autowired
    private CustomerService customerService;


    /**
     * 检查身份证号是否存在
     */
    @RequestMapping("checkCustomerExist")
    @ResponseBody
    public ResultVO checkCustomerExist(RentVO rentVo) {
        Customer customer = customerService.queryCustomerByIdentity(rentVo.getIdentity());
        if (null != customer) {
            return ResultVO.STATUS_TRUE;
        } else {
            return ResultVO.STATUS_FALSE;
        }
    }

    /**
     * 初始化添加出租单的表单数据
     */
    @RequestMapping("initRentFrom")
    @ResponseBody
    public RentVO initRentFrom(RentVO rentVo) {
        //生成出租单号
        rentVo.setRentid(RandomToolsUtil.createRandomStringUseTime(MyConstant.CAR_ORDER_CZ));
        //设置起租时间
        rentVo.setBegindate(new Date());

        User user = (User) WebToolsUtil.getSession().getAttribute("user");
        //设置操作员
        rentVo.setOpername(user.getRealname());
        System.out.println(rentVo);
        return rentVo;
    }

    /**
     * 保存出租单信息
     */
    @RequestMapping("saveRent")
    @ResponseBody
    public ResultVO saveRent(RentVO rentVo) {
        try {
            //设置创建时间
            rentVo.setCreatetime(new Date());
            //设置归还状态
            rentVo.setRentflag(MyConstant.RENT_BACK_FALSE);

            //保存
            this.rentService.addRent(rentVo);

            return ResultVO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.ADD_ERROR;
        }
    }


    /***************出租单管理*****************/

    /**
     * 查询
     */
    @RequestMapping("loadAllRent")
    @ResponseBody
    public TableDataVO loadAllRent(RentVO rentVo) {
        return this.rentService.queryAllRent(rentVo);
    }


    /**
     * 修改出租单信息
     */
    @RequestMapping("updateRent")
    @ResponseBody
    public ResultVO updateRent(RentVO rentVo) {
        try {
            //保存
            this.rentService.updateRent(rentVo);

            return ResultVO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.UPDATE_ERROR;
        }
    }


    /**
     * 删除租单信息
     */
    @RequestMapping("deleteRent")
    @ResponseBody
    public ResultVO deleteRent(RentVO rentVo) {
        try {
            this.rentService.deleteRent(rentVo.getRentid());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }


}
