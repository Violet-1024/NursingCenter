package com.dryyzx.backend.service;


import com.dryyzx.backend.controller.request.NursingRecordPageRequest;
import com.dryyzx.backend.entity.NursingRecord;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface INursingRecordService {

    List<NursingRecord> list();

    PageInfo<NursingRecord> page(NursingRecordPageRequest nursingRecordPageRequest);

    void save(NursingRecord nursingRecord);

    NursingRecord getById(Integer recordID);

    void update(NursingRecord nursingRecord);

    void deleteById(Integer recordID);
}
