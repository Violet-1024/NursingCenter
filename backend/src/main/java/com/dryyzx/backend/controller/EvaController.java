package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.EvaRequest;
import com.dryyzx.backend.entity.Evaluate;
import com.dryyzx.backend.service.EvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/Eva")
public class EvaController {
    @Autowired
    EvaService evaServiceImpl;

    @GetMapping("/page")
    public Result pageQuery(EvaRequest evaRequest) {
        return Result.success(evaServiceImpl.selectWithIf(evaRequest));
    }

    @RequestMapping("/{eid}")
    public Result seeInfo(@PathVariable Integer eid) {
        Evaluate eva = evaServiceImpl.getDetail(String.valueOf(eid));
        return Result.success(eva);
    }

    @RequestMapping("/cut/{eid}")
    public Result cutEva(@PathVariable Integer eid) {
        evaServiceImpl.cut(String.valueOf(eid));
        return Result.success();
    }
}
