package com.project.repository;

import com.project.model.DailyActivityDetail;
import com.project.model.DailyActivitySummary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class DetailRepositoryTest {

    @Autowired
    DetailRepository detailRepository;

    @BeforeEach
    void setUp() {
        List<DailyActivityDetail> list = List.of(
                new DailyActivityDetail(
                        "walking",
                        136,
                        1400,
                        "20220101",
                        200,
                        100,
                        "20220101T132707-0800",
                        "20220101T132847-0800",
                        70),
                new DailyActivityDetail(
                        "running",
                        136,
                        1400,
                        "20220101",
                        200,
                        100,
                        "20220101T132707-0800",
                        "20220101T132847-0800",
                        70),
                new DailyActivityDetail(
                        "transport",
                        1400,
                        "20220102",
                        200,
                        100,
                        "20220101T132707-0800",
                        "20220101T132847-0800")
        );
        detailRepository.saveAll(list);
    }

    @AfterEach
    void tearDown() {
        detailRepository.deleteAll();
    }

    @Test
    void findDetailByDate() {
        List<DailyActivityDetail> detailList = detailRepository.findDetailByDate("20220101");
        assertEquals(2, detailList.size());
        assertEquals("20220101", detailList.get(0).getDate());
        assertEquals(0, detailRepository.findDetailByDate("20221231").size());
    }

    @Test
    void findDetailByCategory() {
        List<DailyActivityDetail> detailList = detailRepository.findDetailByCategory("walking");
        assertEquals(1, detailList.size());
        assertEquals("walking", detailList.get(0).getCategory());
        assertEquals(0, detailRepository.findDetailByCategory("cycling").size());
    }

    @Test
    void findDetailByDateAndCategory() {
        List<DailyActivityDetail> detailList = detailRepository.findDetailByDateAndCategory("20220101", "walking");
        assertEquals(1, detailList.size());
        assertEquals("walking", detailList.get(0).getCategory());
        assertEquals("20220101", detailList.get(0).getDate());
        assertEquals(0, detailRepository.findDetailByDateAndCategory("20220101", "cycling").size());
    }

    @Test
    void deleteDetailByDate() {
        // BEFORE delete by date
        List<DailyActivityDetail> detailList = detailRepository.findDetailByDate("20220101");
        assertEquals(2, detailList.size());
        assertEquals("20220101", detailList.get(0).getDate());
        // AFTER delete by date
        detailList = detailRepository.deleteDetailByDate("20220101");
        assertEquals(2, detailList.size());
        assertEquals("20220101", detailList.get(0).getDate());
        assertEquals(0, detailRepository.findDetailByDate("20220101").size());
    }

    @Test
    void deleteDetailByDateAndCategory() {
        // BEFORE delete by date
        List<DailyActivityDetail> detailList = detailRepository.findDetailByDateAndCategory("20220101", "walking");
        assertEquals(1, detailList.size());
        assertEquals("20220101", detailList.get(0).getDate());
        assertEquals("walking", detailList.get(0).getCategory());
        // AFTER delete by date
        detailList = detailRepository.deleteDetailByDateAndCategory("20220101", "walking");
        assertEquals(1, detailList.size());
        assertEquals("20220101", detailList.get(0).getDate());
        assertEquals("walking", detailList.get(0).getCategory());
        assertEquals(0, detailRepository.deleteDetailByDateAndCategory("20220101", "walking").size());
    }

    @Test
    void count() {
        assertEquals(3, detailRepository.count());
    }
}