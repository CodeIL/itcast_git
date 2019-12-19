package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;

public interface OrderSettingService {
    void batchAdd(List<OrderSetting> orderSettingList);
    void editNumberByOrderDate(OrderSetting orderSetting);

    List getOrderSettingByMonth(String date);
}
