package com.xq.system.mapper;

import com.xq.system.pojo.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    // 查询所有的可用的菜单
    List<Menu> queryAllMenu(Menu menu);

    // 根据 角色ID查询当前角色拥有的菜单
    List<Menu> queryMenuByRoleId(Integer availableTrue, Integer roleid);

    // 根据 用户ID查询当前角色拥有的菜单
    List<Menu> queryMenuByUid(Integer available, Integer userId);

    // 根据 menu表中的pid 查询当前角色拥有的菜单
    Integer queryMenuByPid(Integer pid);

    //根据菜单id删除sys_role_menu里面的数据
    void deleteRoleMenuByMid(Integer id);
}