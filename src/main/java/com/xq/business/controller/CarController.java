package com.xq.business.controller;

import com.xq.business.pojo.Car;
import com.xq.business.service.CarService;
import com.xq.business.vo.CarVO;
import com.xq.system.constant.MyConstant;
import com.xq.system.utils.FileToolUtil;
import com.xq.system.vo.ResultVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @auther: xq2580z
 * @date: 2020/3/13 13:57
 * @description: CarController 车辆控制器
 */
@Controller
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 加载车辆列表返回TableDataVO
     */
    @RequestMapping("loadAllCar")
    @ResponseBody
    public TableDataVO loadAllCar(CarVO carVo) {
        return this.carService.queryAllCar(carVo);
    }

    /**
     * 添加车辆
     */
    @RequestMapping("addCar")
    @ResponseBody
    public ResultVO addCar(CarVO carVo) {
        try {
            carVo.setCreatetime(new Date());
            //如果不是默认图片就去掉图片的_temp的后缀
            if (!carVo.getCarimg().equals(MyConstant.DEFAULT_CAR_IMG)) {
                String filePath = FileToolUtil.updateFileName(carVo.getCarimg(), MyConstant.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
            }
            this.carService.addCar(carVo);
            return ResultVO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.ADD_ERROR;
        }
    }

    /**
     * 修改车辆
     */
    @RequestMapping("updateCar")
    @ResponseBody
    public ResultVO updateCar(CarVO carVo) {
        try {
            String carimg = carVo.getCarimg();
            if (carimg.endsWith(MyConstant.FILE_UPLOAD_TEMP)) {
                String filePath = FileToolUtil.updateFileName(carVo.getCarimg(), MyConstant.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
                //把原来的删除
                Car car = this.carService.queryCarByCarNumber(carVo.getCarnumber());
                FileToolUtil.removeFileByPath(car.getCarimg());
            }
            this.carService.updateCar(carVo);
            return ResultVO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.UPDATE_ERROR;
        }
    }

    /**
     * 删除车辆
     */
    @RequestMapping("deleteCar")
    @ResponseBody
    public ResultVO deleteCar(CarVO carVo) {
        try {
            this.carService.deleteCar(carVo.getCarnumber());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }

    /**
     * 批量删除车辆
     */
    @RequestMapping("deletePartyCar")
    @ResponseBody
    public ResultVO deletePartyCar(CarVO carVo) {
        try {
            this.carService.deletePartyCar(carVo.getIds());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }
}
