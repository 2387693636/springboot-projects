package com.xq.system.service;

import com.xq.system.pojo.LogInfo;
import com.xq.system.vo.LogInfoVO;
import com.xq.system.vo.TableDataVO;

import javax.swing.text.TabableView;

/**
 * @auther: xq2580z
 * @date: 2020/3/4 10:51
 * @description:
 */
public interface LogInfoService {
    /**
     * 查询所有日志
     * @param logInfoVo
     * @return
     */
    public TableDataVO queryAllLogInfo(LogInfoVO logInfoVo);
    /**
     * 添加日志
     * @param logInfoVo
     */
    public void addLogInfo(LogInfoVO logInfoVo);
    /**
     * 根据id删除日志
     * @param logInfoid
     */
    public void deleteLogInfo(Integer logInfoid);
    /**
     * 批量删除日志
     */
    public void deleteBatchLogInfo(Integer [] ids);

    public int insert(LogInfo logInfo);  //插入log记录 登陆log


}
