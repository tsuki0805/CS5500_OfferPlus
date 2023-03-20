package com.project.controller;

import com.project.model.DailyActivityDetail;
//import com.project.model.DailyActivitySummary;
import com.project.service.DailyActivityDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/fetch")
@AllArgsConstructor
public class DailyActivityDetailController {
    private final DailyActivityDetailService dailyActivityDetailService;


    @GetMapping("/dailyActivityDetailByDate")
    public ResponseEntity<List<DailyActivityDetail>> fetchAllDailyActivityDetailByDate(@RequestParam("date") String date){
        try {
            List<DailyActivityDetail> details = dailyActivityDetailService.getAllDailyActivityDetailByDate(date);
            if (details.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(details);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

    @GetMapping("/dailyActivityDetailByCategory")
    public ResponseEntity<List<DailyActivityDetail>> fetchAllDailyActivityDetailByCategory(@RequestParam("category") String category) {
        try {
            List<DailyActivityDetail> details = dailyActivityDetailService.getAllDailyActivityDetailByCategory(category);
            if (details.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(details);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }
}


