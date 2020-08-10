package com.xq.system.vo;

import com.xq.system.constant.MyConstant;

/**
 * @auther: xq2580z
 * @date: 2020/3/11 20:28
 * @description: 返回信息结果
 */
public class ResultVO {

    private Integer code = 0;
    private String msg;

    /**
     * 添加成功
     */
    public static final ResultVO ADD_SUCCESS = new ResultVO(MyConstant.CODE_SUCCESS, MyConstant.ADD_SUCCESS);
    /**
     * 添加失败
     */
    public static final ResultVO ADD_ERROR = new ResultVO(MyConstant.CODE_ERROR, MyConstant.ADD_ERROR);
    /**
     * 更新成功
     */
    public static final ResultVO UPDATE_SUCCESS = new ResultVO(MyConstant.CODE_SUCCESS, MyConstant.UPDATE_SUCCESS);
    /**
     * 更新失败
     */
    public static final ResultVO UPDATE_ERROR = new ResultVO(MyConstant.CODE_ERROR, MyConstant.UPDATE_ERROR);
    /**
     * 删除成功
     */
    public static final ResultVO DELETE_SUCCESS = new ResultVO(MyConstant.CODE_SUCCESS, MyConstant.DELETE_SUCCESS);
    /**
     * 删除失败
     */
    public static final ResultVO DELETE_ERROR = new ResultVO(MyConstant.CODE_ERROR, MyConstant.DELETE_ERROR);

    /**
     * 重置成功
     */
    public static final ResultVO RESET_SUCCESS = new ResultVO(MyConstant.CODE_SUCCESS, MyConstant.RESET_SUCCESS);
    /**
     * 重置失败
     */
    public static final ResultVO RESET_ERROR = new ResultVO(MyConstant.CODE_ERROR, MyConstant.RESET_ERROR);
    /**
     * 分配成功
     */
    public static final ResultVO DISPATCH_SUCCESS = new ResultVO(MyConstant.CODE_SUCCESS, MyConstant.DISPATCH_SUCCESS);
    /**
     * 分配失败
     */
    public static final ResultVO DISPATCH_ERROR = new ResultVO(MyConstant.CODE_ERROR, MyConstant.DISPATCH_ERROR);


    /**
     * 状态码0
     */
    public static final ResultVO STATUS_TRUE = new ResultVO(MyConstant.CODE_SUCCESS);
    /**
     * 状态码-1
     */
    public static final ResultVO STATUS_FALSE = new ResultVO(MyConstant.CODE_ERROR);


    private ResultVO(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    private ResultVO(Integer code) {
        super();
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
