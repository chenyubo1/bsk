package com.bsk.service.impl;

import com.bsk.dao.UserDao;
import com.bsk.dao.impl.UserDaoImpl;
import com.bsk.po.User;
import com.bsk.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User login(User user) {
        return userDao.getUser(user);
    }

    @Override
    public int register(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int update(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public List<User> search(User user,int pageNum,int pageSize,String startTime,String endTime) {
        List<User> users = users = userDao.getUserList(user,pageNum,pageSize,startTime,endTime);
        return users;
    }

    @Override
    public User log(User user) {
        return userDao.log(user);
    }

}
