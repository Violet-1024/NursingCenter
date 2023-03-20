package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.HRankPageRequest;
import com.dryyzx.backend.entity.HRank;
import com.github.pagehelper.PageInfo;


import java.util.List;


public interface IHRankService {

    List<HRank> list();

    PageInfo<HRank> page(HRankPageRequest hrankPageRequest);

    void save(HRank hrank);

    void deleteById(Integer rankid);

    /*
    void update(HRank hrank);

    HRank getById(Integer rankid);
     */

}
