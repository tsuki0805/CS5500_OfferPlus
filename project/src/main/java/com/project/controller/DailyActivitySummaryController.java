package com.project.controller;

import com.project.model.DailyActivityDetail;
import com.project.model.DailyActivitySummary;
import com.project.service.DailyActivityDetailService;
import com.project.service.DailyActivitySummaryService;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fetch")
@AllArgsConstructor
public class DailyActivitySummaryController {
  private final DailyActivitySummaryService dailyActivitySummaryService;

  //Get list of DailyActivitySummary by a specific date
  @GetMapping("/getListOfSummaryByDate")
  public ResponseEntity<List<DailyActivitySummary>> fetchListOfSummaryByDate(@RequestParam("date") String date){
    try {
      List<DailyActivitySummary> listSummary = dailyActivitySummaryService.getListOfSumByDate(date);
      if (listSummary==null) {
        return ResponseEntity.notFound().build();
      } else {
        return ResponseEntity.ok(listSummary);
      }
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(Collections.emptyList());
    }
  }

  //Get list of DailyActivitySummary by a specific category
  @GetMapping("/getListOfSummaryByCategory")
  public ResponseEntity<List<DailyActivitySummary>> fetchListOfSummaryByCat(@RequestParam("category") String category){
    try {
      List<DailyActivitySummary> listSummary = dailyActivitySummaryService.getCalByCat(category);
      if (listSummary==null) {
        return ResponseEntity.notFound().build();
      } else {
        return ResponseEntity.ok(listSummary);
      }
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(Collections.emptyList());
    }
  }

  //Get total daily calories by a specific date and category
  @GetMapping("/getDailyCaloriesSumByDateAndCat")
  public ResponseEntity<List<DailyActivitySummary>> fetchDailyCaloriesSumByDateAndCat(@RequestParam("date") String date, @RequestParam("category") String category){
    try {
      List<DailyActivitySummary> listSummary = dailyActivitySummaryService.getCalByDateAndCat(date,category);
      if (listSummary==null) {
        return ResponseEntity.notFound().build();
      } else {
        return ResponseEntity.ok(listSummary);
      }
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(Collections.emptyList());
    }
  }


  @DeleteMapping("/delete/dailyActivitySummaryByDate")
  public ResponseEntity<List<DailyActivitySummary>> deleteAllDailyActivitySummaryByDate(@RequestParam("date") String date){
    try {
      List<DailyActivitySummary> summary = dailyActivitySummaryService.deleteDailyActivitySummaryByDate(date);
      if (summary.isEmpty()) {
        return ResponseEntity.notFound().build();
      } else {
        return ResponseEntity.ok(summary);
      }
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(Collections.emptyList());
    }
  }

  @PostMapping("/add/dailyActivitySummary")
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<DailyActivitySummary> addDailyActivitySummary(@RequestBody DailyActivitySummary dailyActivitySummary){
    return ResponseEntity.ok(dailyActivitySummaryService.addDailyActivitySummary(dailyActivitySummary));
  }

  @PutMapping("/update/dailyActivitySummary")
  public ResponseEntity<DailyActivitySummary> updateDailyActivitySummaryByDate(@RequestParam("date") String date, @RequestBody DailyActivitySummary dailyActivitySummary){
    return ResponseEntity.ok(dailyActivitySummaryService.updateDailyActivitySummaryByDate(date,dailyActivitySummary));
  }

}
