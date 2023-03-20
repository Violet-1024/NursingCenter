package com.dryyzx.backend.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.dryyzx.backend.controller.request.NursingRecordPageRequest;
import com.dryyzx.backend.entity.NursingRecord;
import com.dryyzx.backend.mapper.NursingRecordMapper;
import com.dryyzx.backend.service.INursingRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NursingRecordService implements INursingRecordService {
    @Autowired
    NursingRecordMapper nursingRecordMapper;

    @Override
    public List<NursingRecord> list() {
        return nursingRecordMapper.list();
    }

    @Override
    public PageInfo<NursingRecord> page(NursingRecordPageRequest nursingRecordPageRequest) {
        PageHelper.startPage(nursingRecordPageRequest.getPageNum(), nursingRecordPageRequest.getPageSize());
        List<NursingRecord> nursingRecords = nursingRecordMapper.listByCondition(nursingRecordPageRequest);
        return new PageInfo<>(nursingRecords);
    }

    @Override
    public void save(NursingRecord nursingRecord) {
        nursingRecordMapper.save(nursingRecord);
    }

    @Override
    public NursingRecord getById(Integer recordID) {
        return nursingRecordMapper.getById(recordID);
    }

    @Override
    public void update(NursingRecord nursingRecord) {
        nursingRecordMapper.updateById(nursingRecord);
    }

    @Override
    public void deleteById(Integer recordID) {
        nursingRecordMapper.deleteById(recordID);
    }
}
