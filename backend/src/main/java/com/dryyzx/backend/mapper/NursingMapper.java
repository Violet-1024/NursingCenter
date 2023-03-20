package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.NursingPageRequest;
import com.dryyzx.backend.entity.Nursing;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NursingMapper {
    List<Nursing> list();

    List<Nursing> listByCondition(NursingPageRequest nursingPageRequest);

    void save(Nursing nursing);

    Nursing getById(Integer nursingID);

    void updateById(Nursing nursing);

    void deleteById(Integer nursingID);
}
