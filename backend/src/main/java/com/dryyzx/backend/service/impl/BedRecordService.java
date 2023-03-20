package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.BedPageRequest;
import com.dryyzx.backend.controller.request.BedRecordPageRequest;
import com.dryyzx.backend.entity.Bed;
import com.dryyzx.backend.entity.BedRecord;
import com.dryyzx.backend.mapper.BedMapper;
import com.dryyzx.backend.mapper.BedRecordMapper;
import com.dryyzx.backend.service.IBedRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BedRecordService implements IBedRecordService {

    @Autowired
    BedRecordMapper bedRecordMapper;

    @Override
    public List<BedRecord> list() {
        return bedRecordMapper.list();
    }

    @Override
    public PageInfo<BedRecord> page(BedRecordPageRequest bedRecordPageRequest) {
        PageHelper.startPage(bedRecordPageRequest.getPageNum(), bedRecordPageRequest.getPageSize());
        List<BedRecord> bedRecords = bedRecordMapper.listByCondition(bedRecordPageRequest);
        return new PageInfo<>(bedRecords);
    }

    @Override
    public void save(BedRecord bedRecord) {
        Date date = new Date();
        bedRecord.setEntryDate(date);
        bedRecordMapper.save(bedRecord);
    }

    @Override
    public BedRecord getById(Integer recordId) {
        return bedRecordMapper.getById(recordId);
    }

    @Override
    public void update(BedRecord bedRecord) {
        bedRecordMapper.updateById(bedRecord);
    }

    @Override
    public void deleteById(Integer recordId) {
        bedRecordMapper.deleteById(recordId);
    }


}
