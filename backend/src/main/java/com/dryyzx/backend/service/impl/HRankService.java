package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.HRankPageRequest;
import com.dryyzx.backend.entity.HRank;
import com.dryyzx.backend.mapper.HRankMapper;
import com.dryyzx.backend.service.IHRankService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class HRankService implements IHRankService {

    @Autowired
    HRankMapper hrankMapper;

    @Override
    public List<HRank> list() {
        return hrankMapper.list();
    }

    @Override
    public PageInfo<HRank> page(HRankPageRequest hrankPageRequest) {
        PageHelper.startPage(hrankPageRequest.getPageNum(), hrankPageRequest.getPageSize());
        List<HRank> hranks = hrankMapper.listByCondition(hrankPageRequest);
        return new PageInfo<>(hranks);
    }

    @Override
    public void save(HRank hrank) {

        if (hrank.getRankid()==null){
            hrankMapper.insert(hrank);
        }else{
            hrankMapper.update(hrank);
        }

    }

    @Override
    public void deleteById(Integer rankid) {
        hrankMapper.deleteById(rankid);
    }
/*
    @Override
    public HRank getById(Integer rankid) {
        return hrankMapper.getById(rankid);
    }

    @Override
    public void update(HRank hrank) {
        hrankMapper.updateById(hrank);
    }

 */


}
