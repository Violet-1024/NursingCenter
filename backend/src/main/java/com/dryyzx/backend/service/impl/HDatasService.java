package com.dryyzx.backend.service.impl;


import com.dryyzx.backend.controller.request.HDatasPageRequest;
import com.dryyzx.backend.entity.HDatas;
import com.dryyzx.backend.entity.HRank;
import com.dryyzx.backend.mapper.HDatasMapper;
import com.dryyzx.backend.service.IHDatasService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HDatasService  implements IHDatasService {

    @Autowired
    HDatasMapper hDatasMapper;

    @Override
    public List<HDatas> list() {return hDatasMapper.list();}

    @Override
    public PageInfo<HDatas> page(HDatasPageRequest hDatasPageRequest){
        PageHelper.startPage(hDatasPageRequest.getPageNum(), hDatasPageRequest.getPageSize());
        List<HDatas> hDatass = hDatasMapper.listByCondition(hDatasPageRequest);
        return new PageInfo<>(hDatass);
    }
    @Override
    public void save(HDatas hDatas){
        if(hDatas.getCustomerid()==null){
            hDatasMapper.insert(hDatas);
        }else{
            hDatasMapper.update(hDatas);
        }
    }
/*
    @Override
    public HDatas getById(Integer hdatasid){
        return hDatasMapper.getById(hdatasid);
    }
    */

}
