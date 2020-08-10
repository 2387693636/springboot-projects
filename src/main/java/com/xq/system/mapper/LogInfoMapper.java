package com.xq.system.mapper;

import com.xq.system.pojo.LogInfo;

import java.util.List;

public interface LogInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(LogInfo logInfo);  //插入log记录 登陆log

    int insertSelective(LogInfo record);

    LogInfo selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(LogInfo record);

    int updateByPrimaryKey(LogInfo record);

    /**
     * 查询日志
     */
    List<LogInfo> queryAllLogInfo(LogInfo logInfo);
}