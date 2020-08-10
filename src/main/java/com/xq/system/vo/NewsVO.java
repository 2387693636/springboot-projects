package com.xq.system.vo;

import com.xq.system.pojo.News;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auther: xq2580z
 * @date: 2020/3/11 19:42
 * @description:
 */
public class NewsVO extends News {

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    /**
     * 分页参数 page limit
     */
    private Integer page;
    private Integer limit;

    /**
     * 时间 startTime endTime
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


    //接收多个id
    private Integer[] ids;
}
