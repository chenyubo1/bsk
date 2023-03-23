package com.bsk.service;

import com.bsk.po.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddress(int uid);
    List<Address> selectAll(Integer userId, Address address);
    void save(Integer userId,Address address);
    void del(Integer addressId);
    void update(Address address);
}
