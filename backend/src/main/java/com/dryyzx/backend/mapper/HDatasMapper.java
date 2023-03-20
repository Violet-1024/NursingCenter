package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.HDatasPageRequest;
import com.dryyzx.backend.entity.HDatas;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HDatasMapper {

    List<HDatas> list();

    List<HDatas> listByCondition(HDatasPageRequest hDatasPageRequest);

    void update(HDatas hDatas);
    void insert(HDatas hDatas);
/*
    HDatas getById(Integer hdatasid);

    void updateById(HDatas hDatas);*/
}
