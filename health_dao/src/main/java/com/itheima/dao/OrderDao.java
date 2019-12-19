package com.itheima.dao;

import com.itheima.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Order> findByCondition(Order order);

    void add(Order dbOrder);

    Map findById4Detail(Integer id);

    void findById(Integer id);

    int todayOrderNumber(String reportDate);

    int todayVisitsNumber(String reportDate);

    int thisWeekOrderNumber(String thisWeekMonday);

    int thisWeekVisitsNumber(String thisWeekMonday);
}
