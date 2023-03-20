package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.ServicePageRequest;
import com.dryyzx.backend.entity.Outing;
import com.dryyzx.backend.entity.Service;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IServiceService {
    List<Service> list();

    PageInfo<Service> page(ServicePageRequest servicePageRequest);

    void save(Service service);

    void deleteById(Integer serviceid);
}
