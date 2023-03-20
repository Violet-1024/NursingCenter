package com.dryyzx.backend.controller;


import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.OrderPageRequest;
import com.dryyzx.backend.entity.Order;
import com.dryyzx.backend.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
     IOrderService orderService;

    @PostMapping("/save")
    public Result save(@RequestBody Order order){
        orderService.save(order);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Order order){
        orderService.update(order);
        return Result.success();
    }

    @DeleteMapping("/delete/{orderID}")
    public Result delete(@PathVariable Integer orderID) {
        orderService.deleteById(orderID);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<Order> orders = orderService.list();
        return Result.success(orders);
    }
    @GetMapping("/{orderID}")
    public Result getById(@PathVariable Integer orderID){
        Order order = orderService.getById(orderID);
        return Result.success(order);
    }

    @GetMapping("/page")
    public Result page(OrderPageRequest orderPageRequest) {
        return Result.success(orderService.page(orderPageRequest));
    }
}
