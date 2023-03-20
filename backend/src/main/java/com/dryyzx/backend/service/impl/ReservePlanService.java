package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.ReservePlanPageRequest;
import com.dryyzx.backend.entity.ReservePlan;
import com.dryyzx.backend.mapper.ReservePlanMapper;
import com.dryyzx.backend.service.IReservePlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservePlanService implements IReservePlanService{

    @Autowired
    ReservePlanMapper reservePlanMapper;


    @Override
    public List<ReservePlan> list() {return reservePlanMapper.list();}


    @Override
    public PageInfo<ReservePlan> page(ReservePlanPageRequest reservePlanPageRequest) {
        PageHelper.startPage(reservePlanPageRequest.getPageNum(), reservePlanPageRequest.getPageSize());
        List<ReservePlan> reservePlans = reservePlanMapper.listByCondition(reservePlanPageRequest);
        return new PageInfo<>(reservePlans);
    }

    @Override
    public void save(ReservePlan reservePlan) {
        reservePlanMapper.save(reservePlan);
    }

    @Override
    public ReservePlan getById(Integer planID) {
        return reservePlanMapper.getById(planID);
    }

    @Override
    public void update(ReservePlan reservePlan) {
        reservePlanMapper.updateById(reservePlan);
    }

    @Override
    public void deleteById(Integer planID) { reservePlanMapper.deleteById(planID);}
}
