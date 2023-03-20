package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.BedPageRequest;
import com.dryyzx.backend.entity.Bed;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBedService {
    List<Bed> list();

    PageInfo<Bed> page(BedPageRequest bedPageRequest);

    void save(Bed bed);

    Bed getById(Integer bedId);

    void update(Bed bed);

    void deleteById(Integer bedId);
}
