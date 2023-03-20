package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.AbTypeRequest;
import com.dryyzx.backend.entity.AbType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AbTypeMapper {
    List<AbType> list(AbTypeRequest abTypeRequest);

    List<AbType> valid(AbTypeRequest abTypeRequest);

    void insert(AbType abType);

    AbType getById(String id);

    void deleteById(String id);

    void update(AbType abType);
}
