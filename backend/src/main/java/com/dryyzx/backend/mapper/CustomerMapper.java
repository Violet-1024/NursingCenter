package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.CustomerPageRequest;
import com.dryyzx.backend.controller.request.LoginRequest;
import com.dryyzx.backend.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    List<Customer> list();

    List<Customer> listByCondition(CustomerPageRequest customerPageRequest);

    void save(Customer customer);

    Customer getById(Integer id);

    void updateById(Customer customer);

    void deleteById(Integer id);

    Customer getByUsernameAndPassword(LoginRequest request);

}
