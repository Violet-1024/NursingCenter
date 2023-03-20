package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.EvaDisRequest;
import com.dryyzx.backend.entity.EvaDis;
import com.dryyzx.backend.service.EvaDisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/EvaDis")
public class EvaDisController {

    @Autowired
    EvaDisService evaDisServiceImpl;

    @GetMapping("/page")
    public Result getPage(EvaDisRequest evaDisRequest) {
        return Result.success(evaDisServiceImpl.getList(evaDisRequest));
    }

    @GetMapping("/{did}")
    public Result getDetail(@PathVariable Integer did) {
        EvaDis evaDis = evaDisServiceImpl.getDetail(String.valueOf(did));
        return Result.success(evaDis);
    }

    @GetMapping("/cut/{did}")
    public Result cutOne(@PathVariable Integer did) {
        evaDisServiceImpl.cut(String.valueOf(did));
        return Result.success();
    }
}
