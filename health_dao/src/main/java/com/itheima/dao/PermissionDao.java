package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    Set<Permission> findByRoleId(Integer id);

    void add(Permission permission);

    Page<Permission> selectByCondition(String queryString);
}
