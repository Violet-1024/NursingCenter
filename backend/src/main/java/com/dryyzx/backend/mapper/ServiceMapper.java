package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.ServicePageRequest;
import com.dryyzx.backend.entity.Service;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceMapper {
    List<Service> list();

    List<Service> listByCondition(ServicePageRequest servicePageRequest);

    void update(Service service);
    void insert(Service service);

    void deleteById(Integer serviceid);
}
