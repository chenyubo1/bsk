package com.bsk.service.impl;

import com.bsk.dao.CartDao;
import com.bsk.dao.impl.CartDaoImpl;
import com.bsk.dto.CartDto;
import com.bsk.po.Cart;
import com.bsk.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
    @Override
    public int addCart(Cart cart) {
        Cart c = cartDao.getCart(cart);
        if(c == null){
            return cartDao.insertCart(cart);
        }else{
            return cartDao.updateCart1(cart);
        }
    }

    @Override
    public int update(Cart cart) {
        return cartDao.updateCart2(cart);
    }

    @Override
    public int clear(Cart cart) {
        return cartDao.delCart(cart);
    }


    @Override
    public List<CartDto> getCarts(Cart cart) {
        return cartDao.getCarts(cart);
    }
}
