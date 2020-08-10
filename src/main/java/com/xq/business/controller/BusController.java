package com.xq.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @auther: xq2580z
 * @date: 2020/3/13 13:57
 * @description:
 */
@Controller
@RequestMapping("bus")
public class BusController {
//   是跳转是的我服了啊 是你的错啊 啊啊啊
    @RequestMapping("toCustomerManager")
    public String toCustomerManager() {
        return "business/customer/customerManager";
    }

    /**
     * 跳转到车辆管理的页面
     */
    @RequestMapping("toCarManager")
    public String toCarManager() {
        return "business/car/carManager";
    }


    /**
     * 跳转到车辆出租的页面
     */
    @RequestMapping("toRentCarManager")
    public String toRentCarManager() {
        return "business/rent/rentCarManager";
    }

    /**
     * 跳转到出租单管理的页面
     */
    @RequestMapping("toRentManager")
    public String toRentManager() {
        return "business/rent/rentManager";
    }

    /**
     * 跳转到自行车入库管理的页面
     */
    @RequestMapping("toCheckCarManager")
    public String toCheckCarManager() {
        return "business/check/checkCarManager";
    }

    /**
     * 跳转到检查单管理的页面ss 是的我爱你都是你的
     */
    @RequestMapping("toCheckManager")
    public String toCheckManager() {
        return "business/check/checkManager";
    }
}
