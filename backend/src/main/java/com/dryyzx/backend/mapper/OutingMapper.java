package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.OutingPageRequest;
import com.dryyzx.backend.entity.Outing;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutingMapper {

    List<Outing> list();

    List<Outing> listByCondition(OutingPageRequest outingPageRequest);

    Outing getById(Integer id);

    void updateById(Outing outing);
}
