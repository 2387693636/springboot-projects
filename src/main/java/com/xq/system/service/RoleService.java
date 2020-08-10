package com.xq.system.service;

import com.xq.system.pojo.Role;
import com.xq.system.vo.RoleVO;
import com.xq.system.vo.TableDataVO;

import java.util.List;

/**
 * @auther: xq2580z
 * @date: 2020/3/12 17:07
 * @description: 角色
 */
public interface RoleService {

    /**
     * 查询所有角色返回
     * List<Role>
     */
    public List<Role> queryAllRoleForList(RoleVO roleVO);

    /**
     * 根据用户id查询用户的可用角色
     */
    public List<Role> queryRoleByUserIdForList(RoleVO roleVO, Integer userId);

    //查询所有角色
    public TableDataVO queryAllRole(RoleVO roleVO);

    //添加角色
    public void addRole(RoleVO roleVO);

    //修改角色
    public void updateRole(RoleVO roleVO);

    //根据id删除角色
    public void deleteRole(Integer roleid);

    //批量删除角色
    public void deletePartyRole(Integer[] ids);

    // 加载角色管理分配菜单的json
    public TableDataVO initRoleMenuTreeJson(Integer roleid);

    //保存角色和菜单的关系
    public void saveRoleMenu(RoleVO roleVO);
}
