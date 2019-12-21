package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Permission;

public interface PermissionService {
    void add(Permission permission);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
}
