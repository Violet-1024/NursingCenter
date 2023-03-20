package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.EvaDisRequest;
import com.dryyzx.backend.entity.EvaDis;
import com.dryyzx.backend.mapper.EvaDisMapper;
import com.dryyzx.backend.service.EvaDisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaDisServiceImpl implements EvaDisService {

    @Autowired
    EvaDisMapper evaDisMapper;

    @Override
    public PageInfo<EvaDis> getList(EvaDisRequest evaDisRequest) {
        PageHelper.startPage(evaDisRequest.getPageNum(), evaDisRequest.getPageSize());
        List<EvaDis> list = evaDisMapper.page(evaDisRequest);
        return new PageInfo<>(list);
    }

    @Override
    public EvaDis getDetail(String id) {
        return evaDisMapper.detail(id);
    }

    @Override
    public void cut(String id) {
        evaDisMapper.cut(id);
    }
}
