package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.AbTypeRequest;
import com.dryyzx.backend.entity.AbType;
import com.dryyzx.backend.mapper.AbTypeMapper;
import com.dryyzx.backend.service.AbTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AbTypeServiceImpl implements AbTypeService {
    static AtomicInteger typeId = new AtomicInteger(10000001);
    static AtomicInteger stateId = new AtomicInteger(5);

    @Autowired
    AbTypeMapper abTypeMapper;

    @Override
    public PageInfo<AbType> getPage(AbTypeRequest abTypeRequest) {
        PageHelper.startPage(abTypeRequest.getPageNum(), abTypeRequest.getPageSize());
        List<AbType> list = abTypeMapper.list(abTypeRequest);
        return new PageInfo<>(list);
    }

    @Override
    public void insert(AbType abType) {
        abType.setAbTypeId(String.valueOf(typeId.addAndGet(1)));
        abType.setPublishTime(new Date());
        abType.setState(stateId.addAndGet(1));
        abTypeMapper.insert(abType);
    }

    @Override
    public AbType getById(String id) {
        return abTypeMapper.getById(id);
    }

    @Override
    public void deleteById(String id) {
        abTypeMapper.deleteById(id);
    }

    @Override
    public void updateType(AbType abType) {
        abType.setState(0);
        abType.setPublishTime(new Date());
        abTypeMapper.update(abType);
    }

    @Override
    public PageInfo<AbType> getValid(AbTypeRequest abTypeRequest) {
        PageHelper.startPage(abTypeRequest.getPageNum(), abTypeRequest.getPageSize());
        List<AbType> list = abTypeMapper.valid(abTypeRequest);
        return new PageInfo<>(list);
    }
}
