package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.BedRecordPageRequest;
import com.dryyzx.backend.entity.BedRecord;
import com.dryyzx.backend.service.IBedRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bedrecord")
public class BedRecordController {
    @Autowired
    IBedRecordService bedRecordService;

    @PostMapping("/save")
    public Result save(@RequestBody BedRecord bedRecord){
        bedRecordService.save(bedRecord);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody BedRecord bedRecord){
        bedRecordService.update(bedRecord);
        return Result.success();
    }

    @DeleteMapping("/delete/{recordID}")
    public Result delete(@PathVariable Integer recordID) {
        bedRecordService.deleteById(recordID);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<BedRecord> bedRecords = bedRecordService.list();
        return Result.success(bedRecords);
    }
    @GetMapping("/{recordID}")
    public Result getById(@PathVariable Integer recordID){
        BedRecord bedRecord = bedRecordService.getById(recordID);
        return Result.success(bedRecord);
    }

    @GetMapping("/page")
    public Result page(BedRecordPageRequest bedRecordPageRequest) {
        return Result.success(bedRecordService.page(bedRecordPageRequest));
    }

}
