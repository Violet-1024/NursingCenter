package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.EvaRequest;
import com.dryyzx.backend.entity.Evaluate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EvaMapper {

    List<Evaluate> query(EvaRequest evaRequest);

    Evaluate detail(String id);

    void cut(String id);
}
