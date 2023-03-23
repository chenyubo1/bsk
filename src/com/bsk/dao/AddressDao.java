package com.bsk.dao;

import com.bsk.po.Address;

import java.util.List;

public interface AddressDao {
    List<Address> getAddress(int uid);
    /**
     * 保存地址
     */

    List<Address> selectAll(Integer userId, Address address);

    void save(Integer userId, Address address);


    /**
     * 删除
     */
    void del(Integer addressId);

    /**
     * 修改
     */
    void update(Address address);
}
