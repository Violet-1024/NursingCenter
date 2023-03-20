package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.dto.LoginDTO;
import com.dryyzx.backend.controller.request.CustomerPageRequest;
import com.dryyzx.backend.controller.request.LoginRequest;
import com.dryyzx.backend.entity.Customer;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICustomerService {
    List<Customer> list();

    PageInfo<Customer> page(CustomerPageRequest customerPageRequest);

    void save(Customer customer);

    Customer getById(Integer id);

    void update(Customer customer);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest request);
}
