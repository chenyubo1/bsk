package com.bsk.dao;

import com.bsk.dto.CartDto;
import com.bsk.po.Cart;

import java.util.List;

public interface CartDao {
    int insertCart(Cart cart);

    int updateCart1(Cart cart);

    int updateCart2(Cart cart);

    int delCart(Cart cart);

    Cart getCart(Cart cart);

    List<CartDto> getCarts(Cart cart);

}
