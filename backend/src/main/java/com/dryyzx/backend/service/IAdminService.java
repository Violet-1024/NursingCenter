package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.dto.LoginDTO;
import com.dryyzx.backend.controller.request.BaseRequest;
import com.dryyzx.backend.controller.request.LoginRequest;
import com.dryyzx.backend.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IAdminService {
    List<Admin> list();

    PageInfo<Admin> page(BaseRequest baseRequest);

    void save(Admin admin);

    Admin getById(Integer eid);

    void update(Admin admin);

    void deleteById(Integer eid);

    LoginDTO login(LoginRequest request);
}
