package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.BaseRequest;
import com.dryyzx.backend.controller.request.EmployeePageRequest;
import com.dryyzx.backend.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<Employee> list();

    List<Employee> listByCondition(EmployeePageRequest employeePageRequest);

    void save(Employee employee);

    Employee getById(Integer eid);

    void updateById(Employee employee);

    void deleteById(Integer eid);
}
