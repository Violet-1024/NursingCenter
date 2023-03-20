package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.AbInfoRequest;
import com.dryyzx.backend.entity.AbInfo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AbInfoService {
    List<AbInfo> getList();

    void insert(AbInfo abInfo);

    void audit(String id);

    AbInfo getDetails(String id);

    PageInfo<AbInfo> page(AbInfoRequest abInfoRequest);
}
