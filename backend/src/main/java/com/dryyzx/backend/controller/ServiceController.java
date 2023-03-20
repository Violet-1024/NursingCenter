package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.ServicePageRequest;
import com.dryyzx.backend.entity.Service;
import com.dryyzx.backend.service.impl.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    ServiceService serviceService;

    @GetMapping("/list")
    public Result list(){
        List<Service> services = serviceService.list();
        return Result.success(services);
    }
    @GetMapping("/page")
    public Result page(ServicePageRequest servicePageRequest) {
        return Result.success(serviceService.page(servicePageRequest));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Service service){
        serviceService.save(service);
        return Result.success();
    }
    @DeleteMapping("/delete/{serviceid}")
    public Result delete(@PathVariable Integer serviceid){
        serviceService.deleteById(serviceid);
        return Result.success();
    }

}
