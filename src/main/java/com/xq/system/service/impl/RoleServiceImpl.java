package com.xq.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xq.system.constant.MyConstant;
import com.xq.system.mapper.MenuMapper;
import com.xq.system.mapper.RoleMapper;
import com.xq.system.pojo.Menu;
import com.xq.system.pojo.Role;
import com.xq.system.service.RoleService;
import com.xq.system.utils.TreeNode;
import com.xq.system.vo.RoleVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: xq2580z
 * @date: 2020/3/12 17:17
 * @description: 角色管理 服务类
 */
@Service
public class RoleServiceImpl implements RoleService {

    //注入RoleMapper
    @Autowired
    private RoleMapper roleMapper;

    //注入MenuMapper
    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Role> queryAllRoleForList(RoleVO roleVO) {
        return roleMapper.queryAllRole(roleVO);
    }

    @Override
    public List<Role> queryRoleByUserIdForList(RoleVO roleVO, Integer userId) {
        return roleMapper.queryRoleByUserIdForList(userId);
    }

    @Override
    public TableDataVO queryAllRole(RoleVO roleVO) {
        Page<Object> page = PageHelper.startPage(roleVO.getPage(), roleVO.getLimit());
        List<Role> data = this.roleMapper.queryAllRole(roleVO);
        return new TableDataVO(page.getTotal(), data);
    }

    @Override
    public void addRole(RoleVO roleVO) {
        roleMapper.insertSelective(roleVO);
    }

    @Override
    public void updateRole(RoleVO roleVO) {
        roleMapper.updateByPrimaryKeySelective(roleVO);
    }

    @Override
    public void deleteRole(Integer roleid) {
        // 删除角色表的数据
        this.roleMapper.deleteByPrimaryKey(roleid);
        // 根据角色id删除sys_role_meun里面的数据
        this.roleMapper.deleteRolMenuByRid(roleid);
        // 根据角色id删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByRid(roleid);
    }

    @Override
    public void deletePartyRole(Integer[] ids) {
        for (Integer rid : ids) {
            deleteRole(rid);
        }
    }

    @Override
    public TableDataVO initRoleMenuTreeJson(Integer roleid) {
        // 查询所有的可用的菜单
        Menu menu = new Menu();
        menu.setAvailable(MyConstant.AVAILABLE_TRUE);
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        // 根据角色ID查询当前角色拥有的菜单
        List<Menu> roleMenu = menuMapper.queryMenuByRoleId(MyConstant.AVAILABLE_TRUE, roleid);

        List<TreeNode> data = new ArrayList<>();
        for (Menu m1 : allMenu) {
            String checkArr = MyConstant.CODE_ZERO + "";
            for (Menu m2 : roleMenu) {
                if (m1.getId() == m2.getId()) {
                    checkArr = MyConstant.CODE_ONE + "";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread() == MyConstant.SPREAD_TRUE ? true : false;
            data.add(new TreeNode(id, pid, title, spread, checkArr));
        }
        return new TableDataVO(data);
    }

    @Override
    public void saveRoleMenu(RoleVO roleVO) {
        Integer rid = roleVO.getRoleid();
        Integer[] mids = roleVO.getIds();
        //根据rid删除sys_role_menu里面所有数据
        this.roleMapper.deleteRolMenuByRid(rid);
        //保存角色和菜单的关系
        for (Integer mid : mids) {
            this.roleMapper.insertRoleMenu(rid, mid);
        }
    }
}
