package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.FoodPageRequest;
import com.dryyzx.backend.entity.Food;
import com.dryyzx.backend.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    IFoodService foodService;

    @PostMapping("/save")
    public Result save(@RequestBody Food food){
        foodService.save(food);
        return Result.success();
    }

 /*   @PutMapping("/update")
    public Result update(@RequestBody Food food){
        foodService.update(food);
        return Result.success();
    }
*/
    @DeleteMapping("/delete/{foodid}")
    public Result delete(@PathVariable Integer foodid) {
        foodService.deleteById(foodid);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<Food> foods = foodService.list();
        return Result.success(foods);
    }
  /*  @GetMapping("/{foodid}")
    public Result getById(@PathVariable Integer foodid){
        Food food = foodService.getById(foodid);
        return Result.success(food);
    }
*/
    @GetMapping("/page")
    public Result page(FoodPageRequest foodPageRequest) {
        return Result.success(foodService.page(foodPageRequest));
    }

}
