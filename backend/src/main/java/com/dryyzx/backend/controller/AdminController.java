package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.dto.LoginDTO;
import com.dryyzx.backend.controller.request.AdminPageRequest;
import com.dryyzx.backend.controller.request.LoginRequest;
import com.dryyzx.backend.entity.Admin;
import com.dryyzx.backend.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired

    IAdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        LoginDTO login = adminService.login(request);
        return Result.success(login);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        adminService.save(admin);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        adminService.update(admin);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<Admin> admins = adminService.list();
        return Result.success(admins);
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest) {
        return Result.success(adminService.page(adminPageRequest));
    }
}
