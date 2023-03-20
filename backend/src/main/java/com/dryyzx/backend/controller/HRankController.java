package com.dryyzx.backend.controller;

import com.dryyzx.backend.common.Result;
import com.dryyzx.backend.controller.request.HRankPageRequest;
import com.dryyzx.backend.entity.HRank;
import com.dryyzx.backend.service.IHRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/hrank")
public class HRankController {

    @Autowired
    IHRankService hrankService;

    @PostMapping("/save")
    public Result save(@RequestBody HRank hrank){
        hrankService.save(hrank);
        return Result.success();
    }

  /*  @PutMapping("/update")
    public Result update(@RequestBody hRank hrank){
        hrankService.update(hrank);
        return Result.success();
    }*/

    @DeleteMapping("/delete/{rankid}")
    public Result delete(@PathVariable Integer rankid) {
        hrankService.deleteById(rankid);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        List<HRank> hranks = hrankService.list();
        return Result.success(hranks);
    }
    /*    @GetMapping("/{rankid}")
        public Result getById(@PathVariable Integer rankid){
            HRank hrank = hrankService.getById(rankid);
            return Result.success(hrank);

       }
    */
    @GetMapping("/page")
    public Result page(HRankPageRequest hrankPageRequest) {
        return Result.success(hrankService.page(hrankPageRequest));
    }


}
