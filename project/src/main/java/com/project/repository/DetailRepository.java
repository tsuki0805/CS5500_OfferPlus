package com.project.repository;

import com.project.model.DailyActivityDetail;
import com.project.model.DailyActivitySummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DetailRepository extends MongoRepository<DailyActivityDetail, String> {

    @Query("{date: '?0'}")
    List<DailyActivityDetail> findDetailByDate(String date);

    @Query("{category:'?0'}")
    List<DailyActivityDetail> findDetailByCategory(String category);

    @Query("{date: '?0', category:'?1'}")
    List<DailyActivityDetail> findDetailByDateAndCategory(String date, String category);

    @Query(value="{date: '?0'}", delete = true)
    List<DailyActivityDetail> deleteDetailByDate(String date);

    @Query(value="{date: '?0', category: '?1'}", delete = true)
    List<DailyActivityDetail> deleteDetailByDateAndCategory(String date, String Category);

    long count();
}
