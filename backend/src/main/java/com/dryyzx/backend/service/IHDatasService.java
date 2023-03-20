package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.HDatasPageRequest;
import com.dryyzx.backend.entity.HDatas;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IHDatasService {
    List<HDatas> list();

    PageInfo<HDatas> page(HDatasPageRequest hDatasPageRequest);

    void save(HDatas hDatas);

  //  HDatas getById(Integer hdatasid);

}
