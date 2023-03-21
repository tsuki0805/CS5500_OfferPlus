package com.project.service;

import com.project.model.DailyActivityDetail;
//import com.project.model.DailyActivitySummary;
import com.project.model.DailyActivitySummary;
import com.project.repository.DetailRepository;
//import com.project.repository.SummaryRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DailyActivityDetailService {

    private final DetailRepository detailRepository;

    public List<DailyActivityDetail> getAllDailyActivityDetailByDate(String date) {
        return detailRepository.findDetailByDate(date);
    }

    public List<DailyActivityDetail> getAllDailyActivityDetailByCategory(String category) {
        return detailRepository.findDetailByCategory(category);
    }

    public List<DailyActivityDetail> deleteDailyActivityDetailByDate(String date) {
        return detailRepository.deleteDetailByDate(date);
    }

    public DailyActivityDetail addDailyActivityDetail(DailyActivityDetail update) {
        return detailRepository.save(update);
    }

    public DailyActivityDetail updateDailyActivityDetailById(ObjectId id,DailyActivityDetail dailyActivitydetail) {
        detailRepository.findDetailById(id);
        return detailRepository.save(dailyActivitydetail);
    }

}

