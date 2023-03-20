package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.AbTypeRequest;
import com.dryyzx.backend.entity.AbType;
import com.dryyzx.backend.service.AbTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/MessageType")
public class AbTypeController {

    @Autowired
    AbTypeService abTypeServiceImpl;

    @GetMapping("/page")
    public Result getPage(AbTypeRequest abTypeRequest) {
        return Result.success(abTypeServiceImpl.getPage(abTypeRequest));
    }

    @RequestMapping("/Add")
    public Result addType(@RequestBody AbType abType) {
        abTypeServiceImpl.insert(abType);
        return Result.success();
    }

    @GetMapping("/{tid}")
    public Result getDetail(@PathVariable Integer tid) {
        AbType abType = abTypeServiceImpl.getById(String.valueOf(tid));
        return Result.success(abType);
    }

    @RequestMapping("/cut/{tid}")
    public Result deleteType(@PathVariable Integer tid) {
        abTypeServiceImpl.deleteById(String.valueOf(tid));
        return Result.success();
    }

    @PutMapping("/edit")
    public Result updateType(@RequestBody AbType abType) {
        abTypeServiceImpl.updateType(abType);
        return Result.success();
    }

    @GetMapping("/getType")
    public Result getValid(AbTypeRequest abTypeRequest) {
        return Result.success(abTypeServiceImpl.getValid(abTypeRequest));
    }
}
