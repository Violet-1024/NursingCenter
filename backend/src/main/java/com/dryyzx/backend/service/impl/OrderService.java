package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.BaseRequest;
import com.dryyzx.backend.controller.request.OrderPageRequest;
import com.dryyzx.backend.entity.Order;
import com.dryyzx.backend.mapper.OrderMapper;
import com.dryyzx.backend.service.IOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService{
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> list() {
        return orderMapper.list();
    }

    @Override
    public PageInfo<Order> page(OrderPageRequest orderPageRequest) {
        PageHelper.startPage(orderPageRequest.getPageNum(), orderPageRequest.getPageSize());
        List<Order> orders = orderMapper.listByCondition(orderPageRequest);
        return new PageInfo<>(orders);
    }

    @Override
    public void save(Order order) {
        Date date = new Date();
        order.setOrderTime(date);
        orderMapper.save(order);
    }

    @Override
    public Order getById(Integer orderID) {
        return orderMapper.getById(orderID);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateById(order);
    }

    @Override
    public void deleteById(Integer orderID) {
        orderMapper.deleteById(orderID);
    }
}
