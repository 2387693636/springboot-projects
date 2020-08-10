package com.xq.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xq.system.mapper.NewsMapper;
import com.xq.system.pojo.News;
import com.xq.system.service.NewsService;
import com.xq.system.vo.NewsVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: xq2580z
 * @date: 2020/3/11 16:39
 * @description:
 */
@Service
public class NewsServiceImpl implements NewsService {

    //注入 NewsMapper
    @Autowired
    private NewsMapper newsMapper;

    //查询所有公告 返回TableDataVO 表格数据对象
    @Override
    public TableDataVO queryAllNews(NewsVO newsVO) {
        //使用分页插件 github的
        System.out.println("page是：" + newsVO.getPage() + " limit是：" + newsVO.getLimit());
        Page<Object> page = PageHelper.startPage(newsVO.getPage(), newsVO.getLimit());
        List<News> data = newsMapper.queryAllNews(newsVO);
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).getContent());
        }
        //data就是 表格数据
        TableDataVO tableDataVO = new TableDataVO(page.getTotal(), data);
        System.out.println(tableDataVO.getData());
        System.out.println(tableDataVO);
        return tableDataVO;
    }

    @Override
    public void addNews(NewsVO newsVO) {
        newsMapper.insertSelective(newsVO);
    }

    @Override
    public void updateNews(NewsVO newsVO) {
        newsMapper.updateByPrimaryKeySelective(newsVO);
    }

    @Override
    public void deleteNews(Integer newsid) {
        newsMapper.deleteByPrimaryKey(newsid);
    }

    //批量删除新闻 多选 删除
    @Override
    public void deletePartyNews(Integer[] ids) {
        for (Integer id : ids) {
            newsMapper.deletePartyNews(id); //遍历 删除
        }
    }

    @Override
    public News queryNewsById(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }
}
