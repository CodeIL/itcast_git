package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author:
 * @date: 2019/12/9 16:47
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping(value = "/findPage",method = RequestMethod.POST)
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
        return pageResult;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@RequestBody Setmeal setmeal,Integer[] ids){
        try {
            setmealService.add(setmeal,ids);
            System.out.println("setmeal                        "+setmeal.getImg());
            System.out.println("setmeal                        "+setmeal.getImg());
            System.out.println("setmeal                        "+setmeal.getImg());
            System.out.println("setmeal                        "+setmeal.getImg());
            System.out.println("setmeal                        "+setmeal.getImg());
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());

            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);


        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)

    public Result upload(@RequestParam("imgFile") MultipartFile multipartFile){
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString()+suffix;
            QiniuUtils.upload2Qiniu(multipartFile.getBytes(),newFileName);
            System.out.println("new FIle Name                 "+newFileName);
            System.out.println("new FIle Name                 "+newFileName);
            System.out.println("new FIle Name                 "+newFileName);
            System.out.println("new FIle Name                 "+newFileName);
            System.out.println("new FIle Name                 "+newFileName);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,newFileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
}
