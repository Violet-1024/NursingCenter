package com.dryyzx.backend.service;

import com.dryyzx.backend.controller.request.FoodPageRequest;
import com.dryyzx.backend.entity.Food;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IFoodService {

    List<Food> list();

    PageInfo<Food> page(FoodPageRequest foodPageRequest);

    void save(Food food);

    void deleteById(Integer foodid);
}
