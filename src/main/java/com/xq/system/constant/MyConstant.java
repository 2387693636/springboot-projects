package com.xq.system.constant;

/**
 * @auther: xq2580z
 * @date: 2020/2/28 15:17
 * @description: 常量 记录着错误的信息 ERROR种类 以便提示 以及其他常量信息
 */
public interface MyConstant {
    String LOGIN_ERR_INFO = "用户名或者密码有误！";
    String LOGIN_CODE_ERR_INFO = "验证码有误！";

    /**
    OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
     */
    String SESSION_STATE = "_SESSION_STATE_QQ_";

    String QQ_ACCESS_TOKEN = "accessToken";
    String QQ_OPENID = "openid";

    //可用状态
    Integer AVAILABLE_TRUE = 1;
    Integer AVAILABLE_FALSE = 0;

    /**
     * 用户类型
     * 1 超级管理员
     * 2 一般管理员
     */
    Integer USER_TYPE_SUPER = 1;
    Integer USER_TYPE_NORMAL = 2;

    //是否展开
    Integer SPREAD_TRUE = 1;
    Integer SPREAD_FALSE = 0;


    //操作状态
    String ADD_SUCCESS = "添加成功";
    String ADD_ERROR = "添加失败";

    String UPDATE_SUCCESS = "更新成功";
    String UPDATE_ERROR = "更新失败";

    String DELETE_SUCCESS = "删除成功";
    String DELETE_ERROR = "删除失败";

    String RESET_SUCCESS = "重置成功";
    String RESET_ERROR = "重置失败";

    String DISPATCH_SUCCESS = "分配成功";
    String DISPATCH_ERROR = "分配失败";

    /**
     * 0 操作成功
     * -1 操作失败
     */

    Integer CODE_SUCCESS = 0;
    Integer CODE_ERROR = -1;


    //公用常量
    Integer CODE_ZERO = 0;
    Integer CODE_ONE = 1;
    Integer CODE_TWO = 2;
    Integer CODE_THREE = 3;

    //默认密码配置
    String USER_DEFAULT_PWD = "123456";

    //临时文件标记
    String FILE_UPLOAD_TEMP = "_temp";

    /**
     *  默认图片地址
     */
    Object DEFAULT_CAR_IMG = "${xq2580z}/static/images/defaultcarimage.jpg";

    //单号的前缀
    String CAR_ORDER_CZ = "CZ";
    String CAR_ORDER_JC = "JC";

    //归还状态
    Integer RENT_BACK_FALSE = 0;
    Integer RENT_BACK_TRUE = 1;

    //出租状态
    Integer RENT_CAR_TRUE = 1;
    Integer RENT_CAR_FALSE = 0;


}
