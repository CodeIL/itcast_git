package com.itheima.dao;

import java.util.List;
import java.util.Map;

public interface ReportDao {
    int findMemberCountBeforeDate(String newYearMonth);

    List<Map> setmealCount();
}
