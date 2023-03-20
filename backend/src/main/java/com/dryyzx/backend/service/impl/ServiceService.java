package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.ServicePageRequest;
import com.dryyzx.backend.entity.Outing;
import com.dryyzx.backend.mapper.ServiceMapper;
import com.dryyzx.backend.service.IServiceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService implements IServiceService {

    @Autowired
    ServiceMapper serviceMapper;

    @Override
    public List<com.dryyzx.backend.entity.Service> list() {
        return serviceMapper.list();
    }

    @Override
    public PageInfo<com.dryyzx.backend.entity.Service> page(ServicePageRequest servicePageRequest) {
        PageHelper.startPage(servicePageRequest.getPageNum(), servicePageRequest.getPageSize());
        List<com.dryyzx.backend.entity.Service> services = serviceMapper.listByCondition(servicePageRequest);
        return new PageInfo<>(services);
    }

    @Override
    public void save(com.dryyzx.backend.entity.Service service) {
        if(service.getServiceid()==null){
            serviceMapper.insert(service);
        }else{
            serviceMapper.update(service);
        }
    }

    @Override
    public void deleteById(Integer serviceid) {
        serviceMapper.deleteById(serviceid);
    }

}
