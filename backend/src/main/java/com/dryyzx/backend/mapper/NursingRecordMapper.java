package com.dryyzx.backend.mapper;


import com.dryyzx.backend.controller.request.BaseRequest;
import com.dryyzx.backend.controller.request.NursingRecordPageRequest;
import com.dryyzx.backend.entity.NursingRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NursingRecordMapper {
    List<NursingRecord> list();

    List<NursingRecord> listByCondition(NursingRecordPageRequest nursingRecordPageRequest);

    void save(NursingRecord nursingRecord);

    NursingRecord getById(Integer recordID);

    void updateById(NursingRecord nursingRecord);

    void deleteById(Integer recordID);
}
