package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.OutingPageRequest;
import com.dryyzx.backend.entity.Outing;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IOutingService {
    List<Outing> list();

    PageInfo<Outing> page(OutingPageRequest outingPageRequest);

    Outing getById(Integer id);

    void update(Outing outing);
}
