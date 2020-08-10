package com.xq.business.controller;

import com.xq.business.pojo.Rent;
import com.xq.business.service.CheckService;
import com.xq.business.service.RentService;
import com.xq.business.vo.CheckVO;
import com.xq.system.vo.ResultVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * @auther: xq2580z
 * @date: 2020/3/13  13:57
 * @description: CheckController 检查单控制器
 */
@Controller
@RequestMapping("check")
public class CheckController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CheckService checkService;

    /***
     * 根据出租单号查询出租单信息
     */
    @RequestMapping("checkRentExist")
    @ResponseBody
    public Rent checkRentExist(String rentid) {
        Rent rent = rentService.queryRentByRentId(rentid);//null   返回对象
        return rent;
    }


    /**
     * 根据出租单号加载检查单的表单数据
     */
    @RequestMapping("initCheckFormData")
    @ResponseBody
    public Map<String, Object> initCheckFormData(String rentid) {
        return this.checkService.initCheckFormData(rentid);
    }

    /**
     * 保存检查单数据
     */
    @RequestMapping("saveCheck")
    @ResponseBody
    public ResultVO saveCheck(CheckVO checkVo) {
        try {
            checkVo.setCreatetime(new Date());
            this.checkService.addCheck(checkVo);
            return ResultVO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.ADD_ERROR;
        }
    }

    //查询所有的检查单
    @RequestMapping("loadAllCheck")
    @ResponseBody
    public TableDataVO loadAllCheck(CheckVO checkVo) {
        return this.checkService.queryAllCheck(checkVo);
    }

    /**
     * 修改检查单数据
     */
    @RequestMapping("updateCheck")
    @ResponseBody
    public ResultVO updateCheck(CheckVO checkVo) {
        try {
            this.checkService.updateCheck(checkVo);
            return ResultVO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.UPDATE_ERROR;
        }
    }

}
