package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.NursingPageRequest;
import com.dryyzx.backend.entity.Nursing;
import com.dryyzx.backend.mapper.NursingMapper;
import com.dryyzx.backend.service.INursingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NursingService implements INursingService {
    @Autowired
    NursingMapper nursingMapper;

    @Override
    public List<Nursing> list() {
        return nursingMapper.list();
    }

    @Override
    public PageInfo<Nursing> page(NursingPageRequest nursingPageRequest) {
        PageHelper.startPage(nursingPageRequest.getPageNum(), nursingPageRequest.getPageSize());
        List<Nursing> nursings = nursingMapper.listByCondition(nursingPageRequest);
        return new PageInfo<>(nursings);
    }

    @Override
    public void save(Nursing nursing) {
//        nursing.setStatus("未启用");
        nursingMapper.save(nursing);
    }

    @Override
    public Nursing getById(Integer nursingID) {
        return nursingMapper.getById(nursingID);
    }

    @Override
    public void update(Nursing nursing) {
        nursingMapper.updateById(nursing);
    }

    @Override
    public void deleteById(Integer nursingID) {
        nursingMapper.deleteById(nursingID);
    }


}
