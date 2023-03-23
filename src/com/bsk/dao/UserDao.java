package com.bsk.dao;

import com.bsk.po.User;

import java.util.List;

public interface UserDao {
	/**
	 * 查询所有用户
	 */
	List<User> getUserList();

	/**
	 * 根据属性获取对象
	 * @param user
	 * @return
	 */
	User getUser(User user);
	User log(User user);
//	添加用户
	int addUser(User user);

	int updateUser(User user);
	List<User> getUserList(User user,int pageNum,int pageSize,String startTime,String endTime);
}