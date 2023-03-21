package com.project.controller;

import com.project.model.DailyActivityDetail;
import com.project.repository.DetailRepository;
import com.project.service.DailyActivityDetailService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
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


    @DeleteMapping("/delete/dailyActivityDetailByDate")
    public ResponseEntity<List<DailyActivityDetail>> deleteAllDailyActivityDetailByDate(@RequestParam("date") String date){
        try {
            List<DailyActivityDetail> details = dailyActivityDetailService.deleteDailyActivityDetailByDate(date);
            if (details.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(details);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

//    @PutMapping("/dailyActivityDetailByDate")
//    public ResponseEntity<DailyActivityDetail> updateDailyActivityDetailById(@RequestParam("id") String id) {
//        DailyActivityDetail DailyActivityDetailData = DetailRepository.findDetailById(id);
//        if(DailyActivityDetailData == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }else{
//            DailyActivityDetail updatedData = DailyActivityDetailData.getDate()
//        }
//
//        return dailyActivityDetailService.updateDailyActivityDetailById();
//    }

    @PostMapping("/add/dailyActivityDetail")
    public ResponseEntity<DailyActivityDetail> addDailyActivityDetail(@RequestBody DailyActivityDetail detail){
        return ResponseEntity.ok(dailyActivityDetailService.addDailyActivityDetail(detail));
    }

    @PutMapping("/update/dailyActivityDetailById")
    public ResponseEntity<DailyActivityDetail> updateDailyActivityDetailById(@RequestParam("_id") ObjectId id, @RequestBody DailyActivityDetail detail){
//        try {
//            DailyActivityDetail detail = dailyActivityDetailService.findById(id);
//            if (detail==null) {
//                return ResponseEntity.notFound().build();
//            } else {
//                return ResponseEntity.ok(dailyActivityDetailService.updateDailyActivityDetailById(id,detail));
//            }
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Collections.emptyList());
//        }
        return ResponseEntity.ok(dailyActivityDetailService.updateDailyActivityDetailById(id,detail));
    }






}


