package com.dryyzx.backend.controller;


import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.HDatasPageRequest;
import com.dryyzx.backend.controller.request.OutingPageRequest;
import com.dryyzx.backend.entity.HDatas;
import com.dryyzx.backend.entity.Outing;
import com.dryyzx.backend.service.impl.HDatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/hdatas")
public class HDatasController {

    @Autowired
    HDatasService hDatasService;

    @GetMapping("/list")
    public Result list(){
        List<HDatas> hDatass = hDatasService.list();
        return  Result.success(hDatass);
    }

    @GetMapping("/page")
    public Result page(HDatasPageRequest hDatasPageRequest) {
        return Result.success(hDatasService.page(hDatasPageRequest));
    }
 /*   @GetMapping("/{customerid}")
    public Result getById(@PathVariable Integer customerid){
        HDatas hDatas = hDatasService.getById(customerid);
        return Result.success(hDatas);
    }*/
    @PostMapping("/save")
    public Result save(@RequestBody HDatas hDatas){
        hDatasService.save(hDatas);
        return Result.success();
    }

}
