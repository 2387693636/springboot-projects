package com.xq.business.service;

import com.xq.business.vo.CheckVO;
import com.xq.system.vo.TableDataVO;

import java.util.Map;

/**
 * @auther: xq2580z
 * @date: 2020/3/13 13:46
 * @description: CheckService 检查单管理的服务器接口
 */
public interface CheckService {
    //根据出租单号加载检查单的表单数据
    Map<String, Object> initCheckFormData(String rentid);

    //保存检查单数据
    void addCheck(CheckVO checkVO);

    //查询 所有的检查单
    TableDataVO queryAllCheck(CheckVO checkVO);

    //修改检查单
    void updateCheck(CheckVO checkVO);

}
