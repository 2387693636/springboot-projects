package com.xq.system.controller;

import com.xq.system.service.LogInfoService;
import com.xq.system.vo.LogInfoVO;
import com.xq.system.vo.ResultVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 日志管理控制器
 * 
 * @author LJH
 *
 */
@RestController
@RequestMapping("logInfo")
public class LogInfoController {

	@Autowired
	private LogInfoService logInfoService;

	/**
	 * 加载日志列表返回TableDataVO
	 */
	@RequestMapping("loadAllLogInfo")
	public TableDataVO loadAllLogInfo(LogInfoVO logInfoVo) {
		return this.logInfoService.queryAllLogInfo(logInfoVo);
	}

	/**
	 * 删除日志
	 */
	@RequestMapping("deleteLogInfo")
	public ResultVO deleteLogInfo(LogInfoVO logInfoVo) {
		try {
			this.logInfoService.deleteLogInfo(logInfoVo.getId());
			return ResultVO.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultVO.DELETE_ERROR;
		}
	}

	/**
	 * 批量删除日志
	 */
	@RequestMapping("deleteBatchLogInfo")
	public ResultVO deleteBatchLogInfo(LogInfoVO logInfoVo) {
		try {
			this.logInfoService.deleteBatchLogInfo(logInfoVo.getIds());
			return ResultVO.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultVO.DELETE_ERROR;
		}
	}

}
