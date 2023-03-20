package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.BaseRequest;
import com.dryyzx.backend.controller.request.OrderPageRequest;
import com.dryyzx.backend.entity.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IOrderService {
    List<Order> list();

    PageInfo<Order> page(OrderPageRequest orderPageRequest);

    void save(Order order);

    Order getById(Integer orderID);

    void update(Order order);

    void deleteById(Integer orderID);
}
