package com.bsk.service;

import com.bsk.po.User;

import java.util.List;

public interface UserService {

    User login(User user);
    int   register(User user);
    int   update(User user);
    User log(User user);
    List<User> search(User user,int pageNum,int pageSize,String startTime,String endTime);
}
