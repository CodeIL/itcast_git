package com.itheima.service;

import com.itheima.pojo.Member;

public interface MemberService {
    Member check(String telephone);

    void add(Member member);
}
