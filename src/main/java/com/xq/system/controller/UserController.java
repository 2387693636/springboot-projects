package com.xq.system.controller;

import com.xq.system.service.UserService;
import com.xq.system.vo.ResultVO;
import com.xq.system.vo.TableDataVO;
import com.xq.system.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther: xq2580z
 * @date: 2020/3/12 14:16
 * @description: 用户 控制器 增删改查
 */
@Controller
public class UserController {

    //注入UserService
    @Autowired
    private UserService userService;

    /**
     * 加载所有用户 返回 json数据 给前端展示
     */
    @RequestMapping("loadAllUser")
    @ResponseBody
    public TableDataVO loadAllUsers(UserVO userVO) {
        return userService.queryAllUser(userVO);
    }

    /**
     * 添加用户
     */
    @RequestMapping("addUser")
    public ResultVO addUser(UserVO userVO) {
        try {
            this.userService.addUser(userVO);
            return ResultVO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     */
    @RequestMapping("updateUser")
    public ResultVO updateUser(UserVO userVO) {
        try {
            this.userService.updateUser(userVO);
            return ResultVO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.UPDATE_ERROR;
        }
    }


    /**
     * 删除用户
     */
    @RequestMapping("deleteUser")
    public ResultVO deleteUser(UserVO userVO) {
        try {
            this.userService.deleteUser(userVO.getUserid());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }

    /**
     * 批量删除用户
     */
    @RequestMapping("deletePartyUser")
    public ResultVO deletePartyUser(UserVO userVO) {
        try {
            this.userService.deletePartyUser(userVO.getIds());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }

    /**
     * 重置用户密码
     */
    @RequestMapping("resetUserPwd")
    public ResultVO resetUserPwd(UserVO userVO) {
        try {
            this.userService.resetUserPwd(userVO.getUserid());
            return ResultVO.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.RESET_ERROR;
        }
    }


    /**
     * 加载用户管理的分配角色的数据
     */
    @RequestMapping("initUserRole")
    public TableDataVO initUserRole(UserVO userVO) {
        return this.userService.queryUserRole(userVO.getUserid());
    }

    /**
     * 保存用户和角色的关系
     */
    @RequestMapping("saveUserRole")
    public ResultVO saveUserRole(UserVO userVO) {
        try {
            this.userService.saveUserRole(userVO);
            return ResultVO.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DISPATCH_ERROR;
        }
    }
}
