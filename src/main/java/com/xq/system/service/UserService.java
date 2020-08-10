package com.xq.system.service;

import com.xq.system.pojo.User;
import com.xq.system.vo.TableDataVO;
import com.xq.system.vo.UserVO;

/**
 * @auther: xq2580z
 * @date: 2020/2/28 14:51
 * @description: 用户服务接口
 */
public interface UserService {

    //登陆
    User login(UserVO userVO);

    //查询所有用户
    public TableDataVO queryAllUser(UserVO userVO);

    //添加用户
    public void addUser(UserVO userVO);

    //修改用户
    public void updateUser(UserVO userVO);

    //根据id删除用户
    public void deleteUser(Integer userid);

    //批量删除用户
    public void deletePartyUser(Integer[] ids);

    //重置密码
    public void resetUserPwd(Integer userid);

    //加载用户管理的分配角色的数据
    TableDataVO queryUserRole(Integer userid);

    //保存用户和角色的关系
    void saveUserRole(UserVO userVO);
}
