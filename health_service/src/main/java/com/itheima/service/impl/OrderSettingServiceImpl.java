package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.tools.internal.xjc.reader.Ring.add;

/**
 * @author:
 * @date: 2019/12/10 21:18
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void batchAdd(List<OrderSetting> orderSettingList) {
        if (orderSettingList != null && orderSettingList.size() > 0) {
            for (OrderSetting orderSetting : orderSettingList) {
                editNumberByOrderDate(orderSetting);
            }
        }
    }
    @Override
    public void editNumberByOrderDate(OrderSetting orderSetting) {
        int count = orderSettingDao.findCountByOrderDate(orderSetting);
        if (count == 0) {

            orderSettingDao.add(orderSetting);
        }else {
            /**/

                /**/
                orderSettingDao.editNumberByOrderDate(orderSetting);
            }
        }

/*
    date: 1, number: 120, reservations: 1 },
*/
/*    @Override
    public List getOrderSettingByMonth(String date) {
        String beginDate = date + "-01";
        String endDate = date + "-31";
        Map map = new HashMap();
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        List<OrderSetting> orderSettingList = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> rsMap = new ArrayList<>();
        if (orderSettingList != null && orderSettingList.size() > 0) {
            for (OrderSetting orderSetting : orderSettingList) {
                Map map1 = new HashMap();
                map1.put("date",orderSetting.getOrderDate().getDate());
                map1.put("number",orderSetting.getNumber());
                map1.put("reservations",orderSetting.getReservations());
                rsMap.add(map1);
            }
        }
        return rsMap;

    }*/

    @Override
    public List getOrderSettingByMonth(String date) {
        String beginDate = date + "-01";
        String endDate = date + "-31";
        Map map = new HashMap();
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        List<OrderSetting> orderSettingList = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> rsMap = new ArrayList<>();
        if (orderSettingList != null && orderSettingList.size() > 0) {
            for (OrderSetting orderSetting : orderSettingList) {
                Map map1 = new HashMap();
                map1.put("date",orderSetting.getOrderDate().getDate());
                map1.put("number",orderSetting.getNumber());
                map1.put("reservations",orderSetting.getReservations());
                rsMap.add(map1);
            }
        }
        return rsMap;

    }
}
