package com.project.controller;

import com.project.model.DailyActivityDetail;
import com.project.model.DailyActivitySummary;
import com.project.service.DailyActivityDetailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fetch")
@AllArgsConstructor
public class DailyActivityDetailController {
    private final DailyActivityDetailService dailyActivityDetailService;


    @GetMapping("/dailyActivityDetailByDate")
    public List<DailyActivityDetail> fetchAllDailyActivityDetailByDate(@RequestParam("id") String date){
        return dailyActivityDetailService.getAllDailyActivityDetailByDate(date);
    }

    @GetMapping("/dailyActivityDetailByCategory")
    public List<DailyActivityDetail> fetchAllDailyActivityDetailByCategory(@RequestParam("category") String category) {
        return dailyActivityDetailService.getAllDailyActivityDetailByCategory(category);
    }
}


