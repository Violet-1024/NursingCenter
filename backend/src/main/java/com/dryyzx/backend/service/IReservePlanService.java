package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.ReservePlanController;
import com.dryyzx.backend.controller.request.ReservePlanPageRequest;
import com.dryyzx.backend.entity.Employee;
import com.dryyzx.backend.entity.ReservePlan;
import com.github.pagehelper.PageInfo;
import org.springframework.context.annotation.Primary;

import java.util.List;


public interface IReservePlanService {

    List<ReservePlan> list();

    PageInfo<ReservePlan> page(ReservePlanPageRequest reservePlanPageRequest);

    void save(ReservePlan reservePlan);

    void update(ReservePlan reservePlan);

    ReservePlan getById(Integer planID);

    void deleteById(Integer planID);
}
