package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")

public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.add(checkGroup, checkitemIds);
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        System.out.println(queryPageBean.getCurrentPage());
        PageResult pageResult = checkGroupService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
        return pageResult;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public Result findById(Integer id){
        try {
           CheckGroup checkGroup =  checkGroupService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping(value = "/findCheckItemIdsByCheckGroupId",method = RequestMethod.GET)
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer groupId){
        List<Integer> list = null;
        try {
           list = checkGroupService.findCheckItemIdsByCheckGroupId(groupId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Result edit(Integer[] ids,@RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.edit(ids,checkGroup);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Result findAll(){
        try {
            System.out.println("1");
            System.out.println("1");
            System.out.println("1");
            System.out.println("1");


            List<CheckGroup> checkGroupList =  checkGroupService.findAll();


            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupList);
        } catch (Exception e) {
            System.out.println("2");
            System.out.println("2");
            System.out.println("2");
            System.out.println("2");
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

}

