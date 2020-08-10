package com.xq.system.controller;

import com.xq.system.pojo.News;
import com.xq.system.pojo.User;
import com.xq.system.service.NewsService;
import com.xq.system.utils.WebToolsUtil;
import com.xq.system.vo.NewsVO;
import com.xq.system.vo.ResultVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @auther: xq2580z
 * @date: 2020/3/11 20:20
 * @description: 新闻 首页控制器
 */
@Controller
@RequestMapping("news")
public class NewsController {

    //注入NewsService
    @Autowired
    private NewsService newsService;

    /**
     * 加载新闻公告 返回TableDataVO 数据
     */
    @RequestMapping("loadAllNews")
    @ResponseBody
    public TableDataVO loadAllNews(NewsVO newsVO) {
        System.out.println("进入/news/loadAllNews.action");
        return this.newsService.queryAllNews(newsVO);
    }


    /**
     * 新增新闻公告 返回成功或者失败的提示信息
     */
    @RequestMapping("addNews")
    @ResponseBody
    public ResultVO addNews(NewsVO newsVO) {

        try {
            newsVO.setCreatetime(new Date());
            User user = (User) WebToolsUtil.getSession().getAttribute("user");
            //给userVO
            newsVO.setOpername(user.getRealname());
            newsService.addNews(newsVO);
            //新增成功
            return ResultVO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            //出现Exception 则新增失败
            return ResultVO.ADD_ERROR;
        }
    }

    /**
     * 修改公告
     */
    @RequestMapping("updateNews")
    @ResponseBody
    public ResultVO updateNews(NewsVO newsVO) {
        try {
            this.newsService.updateNews(newsVO);
            return ResultVO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.ADD_ERROR;
        }
    }

    /**
     * 删除公告
     */
    @RequestMapping("deleteNews")
    @ResponseBody
    public ResultVO deleteNews(NewsVO newsVO) {
        try {
            this.newsService.deleteNews(newsVO.getId());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     */
    @RequestMapping("deletePartyNews")
    @ResponseBody
    public ResultVO deletePartyNews(NewsVO newsVO) {
        try {
            this.newsService.deletePartyNews(newsVO.getIds());
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }

    /**
     * 根据id查询公告
     */
    @RequestMapping("loadNewsById")
    public @ResponseBody
    News loadNewsById(Integer id) {
        return this.newsService.queryNewsById(id);
    }

}