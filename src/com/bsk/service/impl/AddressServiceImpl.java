package com.bsk.service.impl;

import com.bsk.dao.impl.AddressDaoImpl;
import com.bsk.po.Address;
import com.bsk.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private AddressDaoImpl addressDao=  new AddressDaoImpl();
    @Override
    public List<Address> getAddress(int uid) {
        return addressDao.getAddress(uid);
    }
    public List<Address> selectAll(Integer userId, Address address) {
        return addressDao.selectAll(userId,address);
    }
    @Override
    public void save(Integer userId, Address address) {
        addressDao.save(userId,address);
    }

    @Override
    public void del(Integer addressId) {
        addressDao.del(addressId);
    }

    @Override
    public void update(Address address) {
        addressDao.update(address);
    }
}
