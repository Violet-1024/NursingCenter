package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.BedPageRequest;
import com.dryyzx.backend.entity.Bed;
import com.dryyzx.backend.service.IBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bed")
public class BedController {
    @Autowired
    IBedService bedService;

    @PostMapping("/save")
    public Result save(@RequestBody Bed bed){
        bedService.save(bed);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Bed bed){
        bedService.update(bed);
        return Result.success();
    }

    @DeleteMapping("/delete/{bedID}")
    public Result delete(@PathVariable Integer bedID) {
        bedService.deleteById(bedID);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<Bed> beds = bedService.list();
        return Result.success(beds);
    }
    @GetMapping("/{bedID}")
    public Result getById(@PathVariable Integer bedID){
        Bed bed = bedService.getById(bedID);
        return Result.success(bed);
    }

    @GetMapping("/page")
    public Result page(BedPageRequest bedPageRequest) {
        return Result.success(bedService.page(bedPageRequest));
    }

}
