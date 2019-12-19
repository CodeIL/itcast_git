package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author:
 * @date: 2019/12/14 16:35
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Reference
    private MemberService memberService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Result check(@RequestBody Map map, HttpServletResponse response) {
        try {
            String validateCode = (String) map.get("validateCode");
            String telephone = (String) map.get("telephone");
            String redisCode = jedisPool.getResource().get(RedisMessageConstant.SENDTYPE_LOGIN + "_" + telephone);
            if (telephone == null) {
                return new Result(false, MessageConstant.PARM_ERROR);
            }if (redisCode == null || validateCode == null || !redisCode.equals(validateCode)) {
                return new Result(false, MessageConstant.TELEPHONE_VALIDATECODE_NOTNULL);
            }

            Member member = memberService.check(telephone);
            if (member == null) {
                member = new Member();
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());
                memberService.add(member);
            }
            Cookie cookie = new Cookie("login_member_telephone",telephone);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*60);
            response.addCookie(cookie);
            return new Result(true,MessageConstant.LOGIN_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.LOGIN_FAIL);
        }


    }

}
