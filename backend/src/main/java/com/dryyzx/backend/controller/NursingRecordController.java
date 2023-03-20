package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.NursingRecordPageRequest;
import com.dryyzx.backend.entity.NursingRecord;
import com.dryyzx.backend.service.INursingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/nursingrecord")
public class NursingRecordController {

    @Autowired
    INursingRecordService nursingRecordService;

    @PostMapping("/save")
    public Result save(@RequestBody NursingRecord nursingRecord){
        nursingRecordService.save(nursingRecord);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody NursingRecord nursingRecord){
        nursingRecordService.update(nursingRecord);
        return Result.success();
    }

    @DeleteMapping("/delete/{recordID}")
    public Result delete(@PathVariable Integer recordID) {
        nursingRecordService.deleteById(recordID);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<NursingRecord> nursingRecords = nursingRecordService.list();
        return Result.success(nursingRecords);
    }
    @GetMapping("/{recordID}")
    public Result getById(@PathVariable Integer recordID){
        NursingRecord nursingRecord = nursingRecordService.getById(recordID);
        return Result.success(nursingRecord);
    }

    @GetMapping("/page")
    public Result page(NursingRecordPageRequest nursingRecordPageRequest) {
        return Result.success(nursingRecordService.page(nursingRecordPageRequest));
    }


}
