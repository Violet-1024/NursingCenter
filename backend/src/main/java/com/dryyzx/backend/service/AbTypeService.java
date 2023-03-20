package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.AbTypeRequest;
import com.dryyzx.backend.entity.AbType;
import com.github.pagehelper.PageInfo;


public interface AbTypeService {
    PageInfo<AbType> getPage(AbTypeRequest abTypeRequest);

    void insert(AbType abType);

    AbType getById(String id);

    void deleteById(String id);

    void updateType(AbType abType);

    PageInfo<AbType> getValid(AbTypeRequest abTypeRequest);
}
