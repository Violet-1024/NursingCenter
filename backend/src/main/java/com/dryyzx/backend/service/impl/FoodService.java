package com.dryyzx.backend.service.impl;

import com.dryyzx.backend.controller.request.FoodPageRequest;
import com.dryyzx.backend.entity.Food;
import com.dryyzx.backend.mapper.FoodMapper;
import com.dryyzx.backend.service.IFoodService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class FoodService implements IFoodService {

    @Autowired
    FoodMapper foodMapper;

    @Override
    public List<Food> list() {
        return foodMapper.list();
    }

    @Override
    public PageInfo<Food> page(FoodPageRequest foodPageRequest) {
        PageHelper.startPage(foodPageRequest.getPageNum(), foodPageRequest.getPageSize());
        List<Food> foods = foodMapper.listByCondition(foodPageRequest);
        return new PageInfo<>(foods);
    }

    @Override
    public void save(Food food) {

        if (food.getFoodid()==null){
            foodMapper.insert(food);
        }else{
            foodMapper.update(food);
        }

    }

    @Override
    public void deleteById(Integer foodid) {
        foodMapper.deleteById(foodid);
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
