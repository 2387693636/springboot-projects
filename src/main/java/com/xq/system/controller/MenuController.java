package com.xq.system.controller;

import com.xq.system.constant.MyConstant;
import com.xq.system.pojo.Menu;
import com.xq.system.pojo.User;
import com.xq.system.service.MenuService;
import com.xq.system.utils.TreeNode;
import com.xq.system.utils.TreeNodeBuilder;
import com.xq.system.utils.WebToolsUtil;
import com.xq.system.vo.MenuVO;
import com.xq.system.vo.ResultVO;
import com.xq.system.vo.TableDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @auther: xq2580z
 * @date: 2020/2/28 13:36
 * @description: 菜单控制器
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVO menuVo) {
        //得到当前登陆的用户对象
        User user = (User) WebToolsUtil.getSession().getAttribute("user");
        List<Menu> list = null;
        menuVo.setAvailable(MyConstant.AVAILABLE_TRUE);//只查询可用的
        if (user.getType().equals(MyConstant.USER_TYPE_SUPER)) {
            list = this.menuService.queryAllMenuForList(menuVo);
        } else {
            list = this.menuService.queryMenuByUserIdForList(menuVo, user.getUserid());
        }
        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread().equals(MyConstant.SPREAD_TRUE) ? true : false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return TreeNodeBuilder.builder(nodes, 1);
    }


    /**
     * 加载菜单管理左边的菜单树
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    @ResponseBody
    public TableDataVO loadMenuManagerLeftTreeJson(MenuVO menuVO) {
        //只查询可用的
        menuVO.setAvailable(MyConstant.AVAILABLE_TRUE);
        List<Menu> list = this.menuService.queryAllMenuForList(menuVO);
        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread().equals(MyConstant.SPREAD_TRUE) ? true : false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return new TableDataVO(nodes);
    }

    /**
     * 加载菜单列表返回TableDataVO
     */
    @RequestMapping("loadAllMenu")
    @ResponseBody
    public TableDataVO loadAllMenu(MenuVO menuVO) {
        return this.menuService.queryAllMenu(menuVO);
    }


    /**
     * 添加菜单
     */
    @RequestMapping("addMenu")
    @ResponseBody
    public ResultVO addMenu(MenuVO menuVo) {
        try {
            this.menuService.addMenu(menuVo);
            return ResultVO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     */
    @RequestMapping("updateMenu")
    @ResponseBody
    public ResultVO updateMenu(MenuVO menuVO) {
        try {
            this.menuService.updateMenu(menuVO);
            return ResultVO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.UPDATE_ERROR;
        }
    }

    /**
     * 根据id判断当前菜单有没有子节点
     * 有返回code>=0
     * 没有 返回code<0
     */
    @RequestMapping("checkMenuHasChildren")
    @ResponseBody
    public ResultVO checkMenuHasChildren(MenuVO menuVO) {
        //根据pid查询菜单数量
        Integer count = this.menuService.queryMenuByPid(menuVO.getId());
        if (count > 0) {
            return ResultVO.STATUS_TRUE;
        } else {
            return ResultVO.STATUS_FALSE;
        }
    }

    /**
     * 删除菜单
     */
    @RequestMapping("deleteMenu")
    @ResponseBody
    public ResultVO deleteMenu(MenuVO menuVO) {
        try {
            this.menuService.deleteMenu(menuVO);
            return ResultVO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.DELETE_ERROR;
        }
    }

}
