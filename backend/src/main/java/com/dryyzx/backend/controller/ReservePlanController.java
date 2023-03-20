package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.ReservePlanPageRequest;
import com.dryyzx.backend.entity.ReservePlan;
import com.dryyzx.backend.service.impl.ReservePlanService;
import com.dryyzx.backend.service.IReservePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reservePlan")
public class ReservePlanController {

    @Autowired
    IReservePlanService reservePlanService;

    @PostMapping("/save")
    public Result save(@RequestBody ReservePlan reservePlan){
        reservePlanService.save(reservePlan);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody ReservePlan reservePlan){
        reservePlanService.update(reservePlan);
        return Result.success();
    }

    @DeleteMapping("/delete/{planID}")
    public Result delete(@PathVariable Integer planID) {
        reservePlanService.deleteById(planID);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<ReservePlan> reservePlans = reservePlanService.list();
        return Result.success(reservePlans);
    }
    @GetMapping("/{planID}")
    public Result getById(@PathVariable Integer planID){
        ReservePlan reservePlan = reservePlanService.getById(planID);
        return Result.success(reservePlan);
    }

    @GetMapping("/page")
    public Result page(ReservePlanPageRequest reservePlanPageRequest) {
        return Result.success(reservePlanService.page(reservePlanPageRequest));
    }

}
