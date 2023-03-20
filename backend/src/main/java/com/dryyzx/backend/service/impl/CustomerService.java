package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.dto.LoginDTO;
import com.dryyzx.backend.controller.request.CustomerPageRequest;
import com.dryyzx.backend.controller.request.LoginRequest;
import com.dryyzx.backend.entity.Admin;
import com.dryyzx.backend.entity.Customer;
import com.dryyzx.backend.exception.ServiceException;
import com.dryyzx.backend.mapper.CustomerMapper;
import com.dryyzx.backend.service.ICustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> list() {
        return customerMapper.list();
    }

    @Override
    public PageInfo<Customer> page(CustomerPageRequest customerPageRequest) {
        PageHelper.startPage(customerPageRequest.getPageNum(), customerPageRequest.getPageSize());
        List<Customer> customers = customerMapper.listByCondition(customerPageRequest);
        return new PageInfo<>(customers);
    }

    @Override
    public void save(Customer customer) {
//        customer.setStatus("空闲");
//        Date entrydate = new Date();
//        customer.setEntryDate(entrydate);

        customerMapper.save(customer);
    }

    @Override
    public Customer getById(Integer id) {
        return customerMapper.getById(id);
    }

    @Override
    public void update(Customer customer) {
        customerMapper.updateById(customer);
    }

    @Override
    public void deleteById(Integer id) {
        customerMapper.deleteById(id);
    }

    @Override
    public LoginDTO login(LoginRequest request) {
        Customer customer = customerMapper.getByUsernameAndPassword(request);
        if(customer == null) {
            throw new ServiceException("用户名密码错误");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(customer,loginDTO);
        return loginDTO;
    }

}
