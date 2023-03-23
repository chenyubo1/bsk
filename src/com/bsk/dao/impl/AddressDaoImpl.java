package com.bsk.dao.impl;

import com.bsk.dao.AddressDao;
import com.bsk.po.Address;
import com.bsk.po.User;
import com.bsk.util.DataSourceUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtil.getDataSource());
    @Override
    public List<Address> getAddress(int uid) {
        String sql = "select * from t_address where user_id =?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Address.class),uid );
    }

    @Override

        public List<Address> selectAll(Integer userId, Address address) {
            String sql = "select * from t_address where user_id = ? and address_province=? and address_city=? and address_district= ? and address_describe=?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Address.class), userId , address.getAddressProvince(), address.getAddressCity(), address.getAddressDistrict(), address.getAddressDescribe());
        }



    @Override
    public void save(Integer userId, Address address) {
        String sql = "insert into t_address(address_province,address_city,address_district,address_describe,user_id) values(?,?,?,?,?)";
        Object[] p = {address.getAddressProvince(), address.getAddressCity(), address.getAddressDistrict(), address.getAddressDescribe(),userId};
        jdbcTemplate.update(sql,p);
    }

    @Override
    public void del(Integer addressId) {
        String sql = "delete from t_address where address_id=?";
        jdbcTemplate.update(sql,addressId);
    }

    @Override
    public void update(Address address) {
        String sql = "update t_address set ";
        ArrayList objects = new ArrayList<>();
        if(address.getAddressProvince() != null){
            sql += " address_province = ?,";
            objects.add(address.getAddressProvince());
        }
        if(address.getAddressCity() != null){
            sql += " address_City = ?,";
            objects.add(address.getAddressCity());
        }
        if(address.getAddressDistrict() != null){
            sql += " address_district = ?,";
            objects.add(address.getAddressDistrict());
        }
        if(address.getAddressDescribe() != null){
            sql += " address_describe = ?,";
            objects.add(address.getAddressDescribe());
        }
        sql = sql.substring(0, sql.length() - 1); // 去掉最后一个逗号
        sql += " where address_id = ?";
        objects.add(address.getAddressId());
        jdbcTemplate.update(sql, objects.toArray());
    }
    }

