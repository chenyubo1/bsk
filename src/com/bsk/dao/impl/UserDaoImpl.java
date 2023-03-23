package com.bsk.dao.impl;

import com.bsk.dao.UserDao;
import com.bsk.po.User;
import com.bsk.util.DataSourceUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtil.getDataSource());




	@Override
	public List<User> getUserList() {

		return jdbcTemplate.query("select * from t_user", new BeanPropertyRowMapper<User>(User.class));
		
	}

	@Override
	public User getUser(User user) {
		String sql = "select * from t_user where 1=1";
		ArrayList<Object> objects = new ArrayList<>();
		if(user.getUserTel() != null){
			sql += " and user_tel = ?";
			objects.add(user.getUserTel());
		}
		if(user.getUserPwd() != null){
			sql += " and user_pwd = ?";
			objects.add(user.getUserPwd());
		}
		if(user.getUserId() != null){
			sql += " and user_id = ?";
			objects.add(user.getUserId());
		}
		User u = null;
		try {
			u = jdbcTemplate.queryForObject(sql, objects.toArray(), new BeanPropertyRowMapper<>(User.class));
			System.out.println(u);
		}catch (Exception e){
			//e.printStackTrace();
		}
		return u;

	}

	@Override
	public List<User> getUserList(User user,int pageNum,int pageSize,String startTime,String endTime) {
		List<User> uesrs = null;
		ArrayList<Object> objects = new ArrayList<>();
		String sql = "select * from t_user where 1=1 ";

		if(user.getUserId()!=null){
			sql += "and user_id = ? ";
			objects.add(user.getUserId());
		}

		if(user.getUserTel()!=null){
			sql += "and user_tel = ? ";
			objects.add(user.getUserTel());
		}

		if(user.getUserPwd()!=null){
			sql += "and user_pwd = ? ";
			objects.add(user.getUserPwd());
		}

		if(user.getUserName()!=null){
			sql += "and user_name = ? ";
			objects.add(user.getUserName());
		}

		if(user.getUserSex()!=null){
			sql += "and user_sex = ? ";
			objects.add(user.getUserSex());
		}

		if(user.getAddTime()!=null){
			sql += "and add_time = ? ";
			objects.add(user.getAddTime());
		}

		if(user.getUserStatus()!=null){
			sql += "and user_status = ? ";
			objects.add(user.getUserStatus());
		}

		if(user.getUserRole()!=null){
			sql += "and user_role = ? ";
			objects.add(user.getUserRole());
		}

		if(startTime != null && endTime != null){
//            sql += "and DATE_FORMAT(add_time,'%Y-%m-%d') >= "+startTime+" and DATE_FORMAT(add_time,'%Y-%m-%d') <= "+endTime+" ";
			sql += "and add_time between ? and ?";
			objects.add(startTime);
			objects.add(endTime);
		}

		if(pageNum != 0 && pageSize != 0){
			sql += " limit "+((pageNum-1)*pageSize)+","+pageSize;
		}

		System.out.println(sql);

		uesrs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), objects.toArray());

		return uesrs;
	}
	@Override
	public User log(User user) {
		try {
			return jdbcTemplate.queryForObject("select * from t_user where user_tel=?", new BeanPropertyRowMapper<User>(User.class),user.getUserTel());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int addUser(User user) {
		String sql = "insert into t_user(user_tel,user_pwd,user_name,user_sex) values(?,?,?,?)";
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(user.getUserTel());
        objects.add(user.getUserPwd());
		objects.add(user.getUserName());
		objects.add(user.getUserSex());
        return jdbcTemplate.update(sql, objects.toArray());

	}

	@Override
    public int updateUser(User user) {
		String sql ="update t_user set ";
		ArrayList<Object> list =new ArrayList<>();
		if(user.getUserSex()!=null){
			sql+=" user_sex = ? , ";
			list.add(user.getUserSex());
		}
		if(user.getUserTel()!=null){
			sql+=" user_tel = ?  ,";
			list.add(user.getUserTel());
		}
		if(user.getUserPwd()!=null){
			sql+=" user_pwd = ?  ,";
			list.add(user.getUserPwd());
		}
		if(user.getUserName()!=null){
			sql+=" user_name = ?  ,";
			list.add(user.getUserName());
		}
		if(user.getUserSex()!=null){
			sql += " user_sex = ? ,";
			list.add(user.getUserSex());
		}

		if(user.getAddTime()!=null){
			sql += " add_time = ? ,";
			list.add(user.getAddTime());
		}

		if(user.getUserStatus()!=null){
			sql += " user_status = ? ,";
			list.add(user.getUserStatus());
		}

		if(user.getUserRole()!=null){
			sql += " user_role = ? ,";
			list.add(user.getUserRole());
		}
		sql=sql.substring(0,sql.length()-1)+" where user_id =? ";
		list.add(user.getUserId());
		return jdbcTemplate.update(sql,list.toArray());

	}

}