package com.xq.system.mapper;

import com.xq.system.pojo.News;
import com.xq.system.vo.NewsVO;

import java.util.List;

public interface NewsMapper {

    List<News> queryAllNews(NewsVO newsVO);

    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    void deletePartyNews(Integer id);
}