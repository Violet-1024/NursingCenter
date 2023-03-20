package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.BaseRequest;
import com.dryyzx.backend.controller.request.OrderPageRequest;
import com.dryyzx.backend.entity.Order;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface OrderMapper {

    List<Order> list();

    List<Order> listByCondition(OrderPageRequest orderPageRequest);

    void save(Order order);

    Order getById(Integer orderID);

    void updateById(Order order);

    void deleteById(Integer orderID);
}
