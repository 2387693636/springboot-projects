package com.xq.system.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xq.system.constant.MyConstant;
import com.xq.system.mapper.UserMapper;
import com.xq.system.pojo.User;
import com.xq.system.service.UserService;
import com.xq.system.utils.PwdToolUtil;
import com.xq.system.vo.TableDataVO;
import com.xq.system.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: xq2580z
 * @date: 2020/2/28 14:52
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserVO userVO) {

        if (null != userVO) {
            //密码加密 先将数据库中的密码取出来 加密为MD5 e10adc3949ba59abbe56e057f20f883e
            MD5 m = new MD5();
            System.out.println("前台输入密码：" + userVO.getPwd());
            String s = m.digestHex(userVO.getPwd());
            System.out.println("密码明文：" + s);
            System.out.println(userVO.getLoginname() + " " + userVO.getPwd());
            //把密文给userVO 然后匹配数据库中的pwd
            userVO.setPwd(s);
            System.out.println("加密后");
            System.out.println(userVO.getLoginname() + " " + userVO.getPwd());

            //封装起来是
            //PwdToolUtil.setMD5(userVO.getPwd());
            return userMapper.login(userVO);
        } else {
            return null;
        }

    }

    @Override
    public TableDataVO queryAllUser(UserVO userVO) {
        //和新闻公告一样 查出的数据作为json数据传给前端
        //使用PageHelper
        Page<Object> objects = PageHelper.startPage(userVO.getPage(), userVO.getLimit());

        //使用List<>集合存储查出的数据 然后转为TableDataVO json数据传给前端
        List<User> data = userMapper.queryAllUser(userVO);
        TableDataVO tableDataVO = new TableDataVO(objects.getTotal(), data);

        return tableDataVO;
    }

    @Override
    public void addUser(UserVO userVO) {
        //新增user 前端传来的UserVO对象中可以获取到用户的信息
        //前端是不能设置密码的
        String default_pwd_md5 = PwdToolUtil.setMD5(MyConstant.USER_DEFAULT_PWD);
        userVO.setPwd(default_pwd_md5); //密码设置默认密码为 123456

        //设置用户类型  USER_TYPE_NORMAL 一般用户
        userVO.setType(MyConstant.USER_TYPE_NORMAL);

        userMapper.insertSelective(userVO); //写入数据库
    }

    @Override
    public void updateUser(UserVO userVO) {
        userMapper.updateByPrimaryKeySelective(userVO);
    }

    @Override
    public void deleteUser(Integer userid) {
        //通过userid删除用户
        userMapper.deleteByPrimaryKey(userid);

        //用户删除了 也要删除用户角色表中的记录
//        this.roleMapper.deleteRoleUserByUid(userid);

    }

    @Override
    public void deletePartyUser(Integer[] ids) {
        //遍历数组 id[] 删除 user
        for (Integer id : ids) {
            this.deleteUser(id);
        }
    }

    @Override
    public void resetUserPwd(Integer userid) {
        //重置密码 就是将现在密码忘记 变回原来默认的密码 123456的密文
        User user = new User();

        String default_pwd_md5 = PwdToolUtil.setMD5(MyConstant.USER_DEFAULT_PWD);

        user.setUserid(userid);
        user.setPwd(default_pwd_md5); //密码重置为123456的密文 MD5

        userMapper.updateByPrimaryKeySelective(user); //更新现在的数据
    }

    @Override
    public TableDataVO queryUserRole(Integer userid) {
        return null;
    }

    @Override
    public void saveUserRole(UserVO userVO) {

    }
}
