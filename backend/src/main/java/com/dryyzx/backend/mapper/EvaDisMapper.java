package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.EvaDisRequest;
import com.dryyzx.backend.entity.EvaDis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvaDisMapper {
    List<EvaDis> page(EvaDisRequest evaDisRequest);

    EvaDis detail(String id);

    void cut(String id);
}
