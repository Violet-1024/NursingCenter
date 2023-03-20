package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.BedPageRequest;
import com.dryyzx.backend.entity.Bed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BedMapper {
    List<Bed> list();

    List<Bed> listByCondition(BedPageRequest bedPageRequest);

    void save(Bed bed);

    Bed getById(Integer bedId);

    void updateById(Bed bed);

    void deleteById(Integer bedId);

}
