package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.POIUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author:
 * @date: 2019/12/10 21:16
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upload(MultipartFile excelFile){
        try {

            List<String[]> listRow = POIUtils.readExcel(excelFile);
            if (listRow != null && listRow.size()>0) {
                List<OrderSetting> orderSettingList = new ArrayList<>();
                for (String[] row : listRow) {
                    String orderDate = row[0];
                    String number = row[1];
                    OrderSetting orderSetting = new OrderSetting(new Date(orderDate),Integer.parseInt(number));
                    orderSettingList.add(orderSetting);
                }
                    orderSettingService.batchAdd(orderSettingList);
            }
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }


    @RequestMapping(value = "/editNumberByDate",method = RequestMethod.POST)
    public Result editNumberByOrderDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.editNumberByOrderDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }



    @RequestMapping(value = "/getOrderSettingByMonth",method = RequestMethod.GET)
    public Result getOrderSettingByMonth(String date){
        List<Map> list = null;

        try {
           list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }
}
