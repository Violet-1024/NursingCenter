package com.dryyzx.backend.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.dryyzx.backend.controller.request.BaseRequest;
import com.dryyzx.backend.controller.request.EmployeePageRequest;
import com.dryyzx.backend.entity.Employee;
import com.dryyzx.backend.mapper.EmployeeMapper;
import com.dryyzx.backend.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> list() {
        return employeeMapper.list();
    }

    @Override
    public PageInfo<Employee> page(EmployeePageRequest employeePageRequest) {
        PageHelper.startPage(employeePageRequest.getPageNum(), employeePageRequest.getPageSize());
        List<Employee> employees = employeeMapper.listByCondition(employeePageRequest);
        return new PageInfo<>(employees);
    }

    @Override
    public void save(Employee employee) {
        employee.setStatus("在职");
        employee.setPassword("123456");
        Date date = new Date();
        employee.setUsername(DateUtil.format(date,"yyyyMMDD")+Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        employeeMapper.save(employee);
    }

    @Override
    public Employee getById(Integer eid) {
        return employeeMapper.getById(eid);
    }

    @Override
    public void update(Employee employee) {
        employeeMapper.updateById(employee);
    }

    @Override
    public void deleteById(Integer eid) {
        employeeMapper.deleteById(eid);
    }


}
