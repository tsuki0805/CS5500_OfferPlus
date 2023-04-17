package com.project.service;


import com.project.model.DailyActivityDetail;
import com.project.model.DailyActivitySummary;
import com.project.repository.DetailRepository;
import com.project.repository.SummaryRepository;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DailyActivitySummaryService {
  private final SummaryRepository summaryRepository;

  public List<DailyActivitySummary> deleteDailyActivitySummaryByDate(String date) {
    return summaryRepository.deleteSummaryByDate(date);
  }

  public DailyActivitySummary addDailyActivitySummary(DailyActivitySummary summary) {
    return summaryRepository.save(summary);
  }

  public DailyActivitySummary updateDailyActivitySummaryByDate(String date,DailyActivitySummary summary) {
    summaryRepository.findSummaryByDate(date);
    return summaryRepository.save(summary);
  }

  /**
   * @param activityDate date input
   * @return a list of DailyActivitySummary objects according to the input
   */
  public List<DailyActivitySummary> getListOfSumByDate (String activityDate){
    List<DailyActivitySummary> listOfDailyActSum;
    listOfDailyActSum = summaryRepository.findSummaryByDate(activityDate);

    return listOfDailyActSum;
  }

  /**
   * @param activityDate date input
   * @param category category input
   * @return the total consumption of calories in a specific date and specific category
   */
  public List<DailyActivitySummary>  getCalByDateAndCat(String activityDate,String category){
    List<DailyActivitySummary> listOfDailyActSum;
    listOfDailyActSum = summaryRepository.findSummaryByDateAndCategory(activityDate, category);

    return listOfDailyActSum;
  }




}
