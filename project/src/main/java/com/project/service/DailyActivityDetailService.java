package com.project.service;

import com.project.model.DailyActivityDetail;
import com.project.model.DailyActivitySummary;
import com.project.repository.DetailRepository;
import com.project.repository.SummaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DailyActivityDetailService {

    private final DetailRepository detailRepository;

    public List<DailyActivityDetail> getAllDailyActivityDetailByDate(String date) {
        System.out.println("Service date is " + date);
        System.out.println("@@@" + detailRepository.findDetailByDate(date));
        return detailRepository.findDetailByDate(date);
    }

    public List<DailyActivityDetail> getAllDailyActivityDetailByCategory(String category) {
        System.out.println("Service date is " + category);
        System.out.println("@@@" + detailRepository.findDetailByCategory(category));
        return detailRepository.findDetailByCategory(category);
    }
}

