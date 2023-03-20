package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.EvaDisRequest;
import com.dryyzx.backend.entity.EvaDis;
import com.github.pagehelper.PageInfo;

public interface EvaDisService {
    PageInfo<EvaDis> getList(EvaDisRequest evaDisRequest);

    EvaDis getDetail(String id);

    void cut(String id);
}
