package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.AbInfoRequest;
import com.dryyzx.backend.entity.AbInfo;
import com.dryyzx.backend.mapper.AbInfoMapper;
import com.dryyzx.backend.service.AbInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AbInfoServiceImpl implements AbInfoService {
    static AtomicInteger atomicInteger = new AtomicInteger(10000001);

    @Autowired
    AbInfoMapper abInfoMapper;

    @Override
    public List<AbInfo> getList() {
        return abInfoMapper.list();
    }

    @Override
    public void insert(AbInfo abInfo) {
        abInfo.setAbId(String.valueOf(atomicInteger.addAndGet(1)));
        Date date = new Date();
        abInfo.setStartDate(date);
        abInfo.setAudiStatus(0);
        abInfoMapper.insert(abInfo);
    }

    @Override
    public void audit(String id) {
        abInfoMapper.audit(id);
    }

    @Override
    public AbInfo getDetails(String id) {
        return abInfoMapper.getDetails(id);
    }

    @Override
    public PageInfo<AbInfo> page(AbInfoRequest abInfoRequest) {
        PageHelper.startPage(abInfoRequest.getPageNum(), abInfoRequest.getPageSize());
        List<AbInfo> list = abInfoMapper.selectWithWhere(abInfoRequest);
        return new PageInfo<>(list);
    }
}
