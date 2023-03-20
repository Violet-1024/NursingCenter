package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.BedPageRequest;
import com.dryyzx.backend.entity.Bed;
import com.dryyzx.backend.mapper.BedMapper;
import com.dryyzx.backend.service.IBedService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class BedService implements IBedService {
    @Autowired
    BedMapper bedMapper;

    @Override
    public List<Bed> list() {
        return bedMapper.list();
    }

    @Override
    public PageInfo<Bed> page(BedPageRequest bedPageRequest) {
        PageHelper.startPage(bedPageRequest.getPageNum(), bedPageRequest.getPageSize());
        List<Bed> beds = bedMapper.listByCondition(bedPageRequest);
        return new PageInfo<>(beds);
    }

    @Override
    public void save(Bed bed) {
        Date date = new Date();
        bed.setCreateDate(date);
        bedMapper.save(bed);
    }

    @Override
    public Bed getById(Integer bedId) {
        return bedMapper.getById(bedId);
    }

    @Override
    public void update(Bed bed) {
        bedMapper.updateById(bed);
    }

    @Override
    public void deleteById(Integer bedId) {
        bedMapper.deleteById(bedId);
    }


}
