package com.bsk.service;

import com.bsk.dto.CartDto;
import com.bsk.po.Cart;

import java.util.List;

public interface CartService {
    int addCart(Cart cart);

    int update(Cart cart);

    int clear(Cart cart);

    List<CartDto> getCarts(Cart cart);
}
