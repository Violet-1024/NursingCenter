package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.EmployeePageRequest;
import com.dryyzx.backend.entity.Employee;
import com.dryyzx.backend.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @PostMapping("/save")
    public Result save(@RequestBody Employee employee){
        employeeService.save(employee);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Employee employee){
        employeeService.update(employee);
        return Result.success();
    }

    @DeleteMapping("/delete/{eid}")
    public Result delete(@PathVariable Integer eid) {
        employeeService.deleteById(eid);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<Employee> employees = employeeService.list();
        return Result.success(employees);
    }
    @GetMapping("/{eid}")
    public Result getById(@PathVariable Integer eid){
        Employee employee = employeeService.getById(eid);
        return Result.success(employee);
    }

    @GetMapping("/page")
    public Result page(EmployeePageRequest employeePageRequest) {
        return Result.success(employeeService.page(employeePageRequest));
    }

}
