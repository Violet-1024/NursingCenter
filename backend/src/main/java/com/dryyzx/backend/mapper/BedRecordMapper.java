package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.BedPageRequest;
import com.dryyzx.backend.controller.request.BedRecordPageRequest;
import com.dryyzx.backend.entity.Bed;
import com.dryyzx.backend.entity.BedRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BedRecordMapper {
    List<BedRecord> list();

    List<BedRecord> listByCondition(BedRecordPageRequest bedRecordPageRequest);

    void save(BedRecord bedRecord);

    BedRecord getById(Integer recordId);

    void updateById(BedRecord bedRecord);

    void deleteById(Integer recordId);
}
