package com.xq.system.controller;

import com.xq.system.service.RoleService;
import com.xq.system.vo.ResultVO;
import com.xq.system.vo.RoleVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: xq2580z
 * @date: 2020/2/28 13:36
 * @description: 角色控制器
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 加载角色列表返回TableDataVO
     */
    @RequestMapping("loadAllRole")
    @ResponseBody
    public TableDataVO loadAllRole(RoleVO roleVO) {
        return this.roleService.queryAllRole(roleVO);
    }


    /**
     * 添加角色
     */
    @RequestMapping("addRole")
    @ResponseBody
    public ResultVO addRole(RoleVO roleVO) {
        try {
            this.roleService.addRole(roleVO);
            return ResultVO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.ADD_ERROR;
        }
    }

    /**
     * 修改角色
     */
    @RequestMapping("updateRole")
    @ResponseBody
    public ResultVO updateRole(RoleVO roleVO) {
        try {
            this.roleService.updateRole(roleVO);
            return ResultVO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.UPDATE_ERROR;
        }
    }


    /**
     * 删除角色
     */
    @RequestMapping("deleteRole")
    @ResponseBody
    public ResultVO deleteRole(RoleVO roleVO) {
        try {
            this.roleService.deleteRole(roleVO.getRoleid());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     */
    @RequestMapping("deletePartyRole")
    @ResponseBody
    public ResultVO deletePartyRole(RoleVO roleVO) {
        try {
            this.roleService.deletePartyRole(roleVO.getIds());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }

    /**
     * 加载角色管理分配菜单的json
     */
    @RequestMapping("initRoleMenuTreeJson")
    @ResponseBody
    public TableDataVO initRoleMenuTreeJson(Integer roleid) {
        return this.roleService.initRoleMenuTreeJson(roleid);
    }

    /**
     * 保存角色和菜单的关系
     */
    @RequestMapping("saveRoleMenu")
    @ResponseBody
    public ResultVO saveRoleMenu(RoleVO roleVO) {
        try {

            this.roleService.saveRoleMenu(roleVO);
            return ResultVO.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DISPATCH_ERROR;

        }
    }
}
