package com.xq.system.service.impl;

import com.xq.system.mapper.QQUserMapper;
import com.xq.system.pojo.QQUser;
import com.xq.system.service.QQUerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: xq2580z
 * @date: 2020/3/3 11:10
 * @description:
 */
@Service
public class QQUserImpl implements QQUerService {
    @Autowired
    private QQUserMapper qqUserMapper;

    @Override
    public int insert(QQUser qqUser) {

        return qqUserMapper.insert(qqUser);
    }
}
