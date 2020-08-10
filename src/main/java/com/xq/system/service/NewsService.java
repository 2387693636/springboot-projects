package com.xq.system.service;

import com.xq.system.pojo.News;
import com.xq.system.vo.NewsVO;
import com.xq.system.vo.TableDataVO;

/**
 * @auther: xq2580z
 * @date: 2020/3/11 16:39
 * @description:
 */
public interface NewsService {

    /**
     * 查询所有公告
     *
     * @param newsVO
     * @return TableDataVO 表格数据对象
     */
    public TableDataVO queryAllNews(NewsVO newsVO);

    //添加公告
    public void addNews(NewsVO newsVO);

    // 修改公告
    public void updateNews(NewsVO newsVO);

    //根据id删除公告
    public void deleteNews(Integer newsid);

    //批量删除公告
    public void deletePartyNews(Integer[] ids);

    //查询一个公告
    public News queryNewsById(Integer id);

}
