package com.project.repository;

import com.project.model.DailyActivityDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DetailRepository extends MongoRepository<DailyActivityDetail, String> {

    @Query("{date: '?0'}")
    List<DailyActivityDetail> findDetailByDate(String date);

    @Query("{category:'?0'}")
    List<DailyActivityDetail> findDetailByCategory(String category);

    public long count();
}
