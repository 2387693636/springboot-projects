package com.xq.system.vo;

import com.xq.system.pojo.User;

/**
 * @auther: xq2580z
 * @date: 2020/2/28 14:50
 * @description: UserVO 对象
 */
public class UserVO extends User {

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    //分页参数
    private Integer page;
    private Integer limit;

    //验证码
    private String code;

    // 接收多个角色id
    private Integer[] ids;
}
