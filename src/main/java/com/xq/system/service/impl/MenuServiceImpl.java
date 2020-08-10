package com.xq.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xq.system.mapper.MenuMapper;
import com.xq.system.pojo.Menu;
import com.xq.system.service.MenuService;
import com.xq.system.vo.MenuVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: xq2580z
 * @date: 2020/3/12 17:18
 * @description:
 */
@Service
public class MenuServiceImpl implements MenuService {


    //注入MenuMapper
    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> queryAllMenuForList(MenuVO menuVO) {
        return menuMapper.queryAllMenu(menuVO);
    }

    @Override
    public List<Menu> queryMenuByUserIdForList(MenuVO menuVO, Integer userId) {
        return menuMapper.queryMenuByUid(menuVO.getAvailable(), userId);
    }

    @Override
    public TableDataVO queryAllMenu(MenuVO menuVO) {
        Page<Object> page = PageHelper.startPage(menuVO.getPage(), menuVO.getLimit());
        List<Menu> data = this.menuMapper.queryAllMenu(menuVO);
        return new TableDataVO(page.getTotal(), data);
    }

    @Override
    public void addMenu(MenuVO menuVO) {
        menuMapper.insertSelective(menuVO);
    }

    @Override
    public void updateMenu(MenuVO menuVO) {
        menuMapper.updateByPrimaryKeySelective(menuVO);
    }

    @Override
    public Integer queryMenuByPid(Integer pid) {
        return menuMapper.queryMenuByPid(pid);
    }

    @Override
    public void deleteMenu(MenuVO menuVO) {
        //删除菜单表的数据
        this.menuMapper.deleteByPrimaryKey(menuVO.getId());

        //根据菜单id删除sys_role_menu里面的数据
        this.menuMapper.deleteRoleMenuByMid(menuVO.getId());
    }
}
