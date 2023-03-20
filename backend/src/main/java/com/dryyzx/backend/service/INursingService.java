package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.NursingPageRequest;
import com.dryyzx.backend.entity.Nursing;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface INursingService {
    List<Nursing> list();

    PageInfo<Nursing> page(NursingPageRequest nursingPageRequest);

    void save(Nursing nursing);

    Nursing getById(Integer nursingID);

    void update(Nursing nursing);

    void deleteById(Integer NursingID);
}
