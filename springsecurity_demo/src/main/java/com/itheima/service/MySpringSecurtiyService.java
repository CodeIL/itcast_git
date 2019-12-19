package com.itheima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class MySpringSecurtiyService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.itheima.pojo.User dbUser = findUserByUname(username);
        if (dbUser == null) {
            return null;
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        grantedAuthorityList.add(new SimpleGrantedAuthority("add"));
        return new User(username,dbUser.getPassword(),grantedAuthorityList);

    }

    private com.itheima.pojo.User findUserByUname(String username){
        if ("admin".equals(username)){
            com.itheima.pojo.User user = new com.itheima.pojo.User();
            System.out.println(encoder.encode("123456"));
            System.out.println(encoder.encode("123456"));
            System.out.println(encoder.encode("123456"));
            System.out.println(encoder.encode("123456"));
            System.out.println(encoder.encode("123456"));
            user.setPassword(encoder.encode("123456"));
            user.setUsername(username);

        return user;
        }
        return null;
    }


}
