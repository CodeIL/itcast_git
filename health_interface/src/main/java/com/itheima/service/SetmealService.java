package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void add(Setmeal setmeal, Integer[] ids);

    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);
}
