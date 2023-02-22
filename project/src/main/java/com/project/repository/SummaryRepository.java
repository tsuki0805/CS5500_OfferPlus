package com.project.repository;

import com.project.model.DailyActivityDetail;
import com.project.model.DailyActivitySummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SummaryRepository extends MongoRepository<DailyActivitySummary, String> {
    @Query("{date: '?0'}")
    List<DailyActivitySummary> findSummaryByDate(String date);

    @Query("{category:'?0'}")
    List<DailyActivitySummary> findSummaryByCategory(String category);

    @Query(value="{date: '?0'}", delete = true)
    List<DailyActivitySummary> deleteSummaryByDate(String date);

    long count();
}
