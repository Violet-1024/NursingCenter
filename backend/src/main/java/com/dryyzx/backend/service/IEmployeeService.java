package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.BaseRequest;
import com.dryyzx.backend.controller.request.EmployeePageRequest;
import com.dryyzx.backend.entity.Employee;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEmployeeService {
    List<Employee> list();

    PageInfo<Employee> page(EmployeePageRequest employeePageRequest);

    void save(Employee employee);

    Employee getById(Integer eid);

    void update(Employee employee);

    void deleteById(Integer eid);
}
