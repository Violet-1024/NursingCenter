package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.EmployeePageRequest;
import com.dryyzx.backend.controller.request.OutingPageRequest;
import com.dryyzx.backend.entity.Employee;
import com.dryyzx.backend.entity.Outing;
import com.dryyzx.backend.mapper.OutingMapper;
import com.dryyzx.backend.service.IOutingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutingService implements IOutingService {

    @Autowired
    OutingMapper outingMapper;

    @Override
    public List<Outing> list() {
        return outingMapper.list();
    }

    @Override
    public PageInfo<Outing> page(OutingPageRequest outingPageRequest) {
        PageHelper.startPage(outingPageRequest.getPageNum(), outingPageRequest.getPageSize());
        List<Outing> outings = outingMapper.listByCondition(outingPageRequest);
        return new PageInfo<>(outings);
    }

    @Override
    public Outing getById(Integer id) {
        return outingMapper.getById(id);
    }

    @Override
    public void update(Outing outing) {
        outingMapper.updateById(outing);
    }
}
