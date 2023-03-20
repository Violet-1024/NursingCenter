package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.dto.LoginDTO;
import com.dryyzx.backend.controller.request.CustomerPageRequest;
import com.dryyzx.backend.controller.request.LoginRequest;
import com.dryyzx.backend.entity.Customer;
import com.dryyzx.backend.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        LoginDTO login = customerService.login(request);
        return Result.success(login);
    }


    @PostMapping("/save")
    public Result save(@RequestBody Customer customer){

        customerService.save(customer);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Customer customer){
        customerService.update(customer);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        customerService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<Customer> customers = customerService.list();
        return Result.success(customers);
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Customer customer = customerService.getById(id);
        return Result.success(customer);
    }

    @GetMapping("/page")
    public Result page(CustomerPageRequest customerPageRequest) {
        return Result.success(customerService.page(customerPageRequest));
    }

}
