package com.xq.system.mapper;

import com.xq.system.pojo.User;
import com.xq.system.vo.UserVO;

import java.util.List;

/**
 * @author zxq
 */
public interface UserMapper {

    //登陆
    User login(User user);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //查询所有用户
    List<User> queryAllUser(UserVO userVO);
}