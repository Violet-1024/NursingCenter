package com.dryyzx.backend.mapper;

import com.dryyzx.backend.controller.request.FoodPageRequest;
import com.dryyzx.backend.entity.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodMapper {
    List<Food> list();

    List<Food> listByCondition(FoodPageRequest foodPageRequest);

    void update(Food food);
    void insert(Food food);

    void deleteById(Integer foodid);
}
