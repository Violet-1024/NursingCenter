package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.EvaRequest;
import com.dryyzx.backend.entity.Evaluate;
import com.github.pagehelper.PageInfo;

public interface EvaService {

    PageInfo<Evaluate> selectWithIf(EvaRequest evaRequest);

    Evaluate getDetail(String id);

    void cut(String id);

}
