package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.BaseRequest;
import com.dryyzx.backend.controller.request.LoginRequest;
import com.dryyzx.backend.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<Admin> list();

    List<Admin> listByCondition(BaseRequest baseRequest);

    void save(Admin admin);

    Admin getById(Integer id);

    void updateById(Admin admin);

    void deleteById(Integer id);

    Admin getByUsernameAndPassword(LoginRequest request);
}
