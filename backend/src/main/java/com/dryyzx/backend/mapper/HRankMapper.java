package com.dryyzx.backend.mapper;


import com.dryyzx.backend.controller.request.HRankPageRequest;
import com.dryyzx.backend.entity.HRank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HRankMapper {
    List<HRank> list();

    List<HRank> listByCondition(HRankPageRequest hrankPageRequest);

    void update(HRank hRank);
    void insert(HRank hRank);

    void deleteById(Integer rankid);
/*
    void save(HRank hrank);
    HRank getById(Integer rankid);
    void updateById(HRank hrank);
*/

}
