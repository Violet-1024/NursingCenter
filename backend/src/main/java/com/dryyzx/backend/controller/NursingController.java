package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.CustomerPageRequest;
import com.dryyzx.backend.controller.request.NursingPageRequest;
import com.dryyzx.backend.entity.Customer;
import com.dryyzx.backend.entity.Nursing;
import com.dryyzx.backend.service.ICustomerService;
import com.dryyzx.backend.service.INursingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/nursing")
public class NursingController {

    @Autowired
    INursingService nursingService;

    @PostMapping("/save")
    public Result save(@RequestBody Nursing nursing){
        nursingService.save(nursing);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Nursing nursing){
        nursingService.update(nursing);
        return Result.success();
    }

    @DeleteMapping("/delete/{nursingID}")
    public Result delete(@PathVariable Integer nursingID) {
        nursingService.deleteById(nursingID);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<Nursing> nursings = nursingService.list();
        return Result.success(nursings);
    }
    @GetMapping("/{nursingID}")
    public Result getById(@PathVariable Integer nursingID){
        Nursing nursing = nursingService.getById(nursingID);
        return Result.success(nursing);
    }

    @GetMapping("/page")
    public Result page(NursingPageRequest nursingPageRequest) {
        return Result.success(nursingService.page(nursingPageRequest));
    }


}
