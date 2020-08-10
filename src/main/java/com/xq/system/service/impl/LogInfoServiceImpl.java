package com.xq.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xq.system.mapper.LogInfoMapper;
import com.xq.system.pojo.LogInfo;
import com.xq.system.service.LogInfoService;
import com.xq.system.vo.LogInfoVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: xq2580z
 * @date: 2020/3/4 10:51
 * @description:
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Autowired
    private LogInfoMapper logInfoMapper;

    @Override
    public TableDataVO queryAllLogInfo(LogInfoVO logInfoVo) {
        Page<Object> objects = PageHelper.startPage(logInfoVo.getPage(), logInfoVo.getLimit());
        List<LogInfo> logInfos = logInfoMapper.queryAllLogInfo(logInfoVo);
        TableDataVO tableDataVO = new TableDataVO(objects.getTotal(), logInfos);
        return tableDataVO;
    }

    @Override
    public void addLogInfo(LogInfoVO logInfoVo) {
        logInfoMapper.insertSelective(logInfoVo);
    }

    @Override
    public void deleteLogInfo(Integer logInfoid) {
        logInfoMapper.deleteByPrimaryKey(logInfoid);
    }

    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        /*
         *遍历删除
         */
        for (Integer id : ids) {
            deleteLogInfo(id);
        }
    }

    @Override
    public int insert(LogInfo logInfo) {
        return logInfoMapper.insert(logInfo);
    }
}
