package com.xq.system.vo;

/**
 * @auther: xq2580z
 * @date: 2020/3/11 19:45
 * @description: layui的表格数据对象
 */
public class TableDataVO {

    private Integer code = 0;
    private String msg = "";

    private Long count;
    private Object data;

    public TableDataVO() {
    }

    public TableDataVO(Long count, Object data) {
        super();
        this.count = count;
        this.data = data;
    }

    public TableDataVO(Object data) {
        super();
        this.data = data;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
