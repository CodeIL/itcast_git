package com.itheima.dao;

import com.itheima.pojo.Member;

public interface MemberDao {
    Member findByTelephone(String telephone);

    void add(Member member);

    Member check(String telephone);

    int todayNewMember(String reportDate);

    int totalMember();

    int findMemberCountAfterDate(String thisWeekMonday);

    int findByRegTime(String month);
}
