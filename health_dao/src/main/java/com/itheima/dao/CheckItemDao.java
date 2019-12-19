package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {


    void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(String queryString);

    int findCountByCheckItemId(Integer id);

    void deleteById(Integer id);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

    List<CheckItem> findAll();
}
