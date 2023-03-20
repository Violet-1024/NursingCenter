package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.BaseRequest;
import com.dryyzx.backend.controller.request.ReservePlanPageRequest;
import com.dryyzx.backend.entity.Employee;
import com.dryyzx.backend.entity.ReservePlan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ReservePlanMapper {
    List<ReservePlan> list();

    List<ReservePlan> listByCondition(ReservePlanPageRequest reservePlanPageRequest);

    void save(ReservePlan reservePlan);

    ReservePlan getById(Integer planID);

    void updateById(ReservePlan reservePlan);

    void deleteById(Integer planID);
}
