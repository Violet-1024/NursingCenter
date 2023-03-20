package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.OutingPageRequest;
import com.dryyzx.backend.entity.Outing;
import com.dryyzx.backend.service.impl.OutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/outing")
public class OutingController {

    @Autowired
    OutingService outingService;

    @GetMapping("/list")
    public Result list() {
        List<Outing> outings = outingService.list();
        return Result.success(outings);
    }
    @GetMapping("/page")
    public Result page(OutingPageRequest outingPageRequest) {
        return Result.success(outingService.page(outingPageRequest));
    }
    @GetMapping("/{outingid}")
    public Result getById(@PathVariable Integer outingid){
        Outing outing = outingService.getById(outingid);
        return Result.success(outing);
    }
    @PutMapping("/update/")
    public Result update(@RequestBody Outing outing){
        outingService.update(outing);
        return Result.success();
    }

}
