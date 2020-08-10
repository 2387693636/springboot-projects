package com.xq.business.vo;

import com.xq.business.pojo.Check;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auther: xq2580z
 * @date: 2020/3/13 13:43
 * @description: CheckVO
 */
public class CheckVO extends Check {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


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
}
