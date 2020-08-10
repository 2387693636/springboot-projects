package com.xq.system.mapper;

import com.xq.system.pojo.QQUser;

/**
 * @author zxq
 */
public interface QQUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QQUser record);

    int insertSelective(QQUser record);

    QQUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QQUser record);

    int updateByPrimaryKey(QQUser record);
}