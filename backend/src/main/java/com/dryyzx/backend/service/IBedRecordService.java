package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.BedPageRequest;
import com.dryyzx.backend.controller.request.BedRecordPageRequest;
import com.dryyzx.backend.entity.Bed;
import com.dryyzx.backend.entity.BedRecord;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBedRecordService {
    List<BedRecord> list();

    PageInfo<BedRecord> page(BedRecordPageRequest bedRecordPageRequest);

    void save(BedRecord bedRecord);

    BedRecord getById(Integer recordId);

    void update(BedRecord bedRecord);

    void deleteById(Integer recordId);
}
