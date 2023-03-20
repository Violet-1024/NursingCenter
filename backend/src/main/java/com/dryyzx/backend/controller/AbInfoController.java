package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.AbInfoRequest;
import com.dryyzx.backend.entity.AbInfo;
import com.dryyzx.backend.service.AbInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/MessageManage")
public class AbInfoController {
    @Autowired
    AbInfoService abInfoServiceImpl;

    @RequestMapping("/list")
    public Result getDataWithList() {
        List<AbInfo> list = abInfoServiceImpl.getList();
        return Result.success(list);
    }

    @RequestMapping("/Add")
    public Result insertAbInfo(@RequestBody AbInfo abInfo) {
        abInfoServiceImpl.insert(abInfo);
        return Result.success();
    }

    @GetMapping("/{aid}")
    public Result getDetail(@PathVariable Integer aid) {
        AbInfo abInfo = abInfoServiceImpl.getDetails(String.valueOf(aid));
        return Result.success(abInfo);
    }

    @RequestMapping("/page")
    public Result queryByPage(AbInfoRequest abInfoRequest) {
        return Result.success(abInfoServiceImpl.page(abInfoRequest));
    }

    @GetMapping("/audit/{aid}")
    public Result auditInfo(@PathVariable Integer aid) {
        abInfoServiceImpl.audit(String.valueOf(aid));
        return Result.success();
    }
}
