package com.xq.system.service;

import com.xq.system.pojo.Menu;
import com.xq.system.vo.MenuVO;
import com.xq.system.vo.TableDataVO;

import java.util.List;

/**
 * @auther: xq2580z
 * @date: 2020/3/12 17:07
 * @description: 菜单
 */
public interface MenuService {

    //查询所有菜单返回
    public List<Menu> queryAllMenuForList(MenuVO menuVO);

    /**
     * 根据用户id查询用户的可用菜单
     */
    public List<Menu> queryMenuByUserIdForList(MenuVO menuVO, Integer userId);

    //查询所有菜单
    public TableDataVO queryAllMenu(MenuVO menuVO);

    // 添加菜单
    public void addMenu(MenuVO menuVO);

    //修改菜单
    public void updateMenu(MenuVO menuVO);

    //根据pid查询菜单数量
    public Integer queryMenuByPid(Integer pid);

    //根据id删除菜单
    public void deleteMenu(MenuVO menuVO);


}
