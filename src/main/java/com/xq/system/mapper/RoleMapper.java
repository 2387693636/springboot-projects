package com.xq.system.mapper;

import com.xq.system.pojo.Role;
import com.xq.system.vo.RoleVO;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //查询所有角色
    List<Role> queryAllRole(RoleVO roleVO);

    //通过id查角色
    List<Role> queryRoleByUserIdForList(Integer userId);

    // 根据角色id删除sys_role_meun里面的数据
    void deleteRolMenuByRid(Integer roleid);


    // 根据角色id删除sys_role_user里面的数据
    void deleteRoleUserByRid(Integer roleid);

    //保存角色和菜单的关系
    void insertRoleMenu(Integer rid, Integer mid);
}