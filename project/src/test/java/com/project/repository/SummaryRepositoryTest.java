package com.project.repository;

import com.project.model.DailyActivitySummary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class SummaryRepositoryTest {

    @Autowired
    private SummaryRepository summaryRepository;

    @BeforeEach
    void setUp() {
        List<DailyActivitySummary> list = List.of(
                new DailyActivitySummary(
                        "walking",
                        136,
                        1400,
                        "20220101",
                        200,
                        100,
                        599),
                new DailyActivitySummary(
                        "transport",
                        1400,
                        "20220102",
                        200,
                        100)
        );
        summaryRepository.saveAll(list);
    }

    @AfterEach
    void tearDown() {
        summaryRepository.deleteAll();
    }

    @Test
    void findSummaryByDate() {
        List<DailyActivitySummary> summaryList = summaryRepository.findSummaryByDate("20220101");
        assertEquals(1, summaryList.size());
        assertEquals("20220101", summaryList.get(0).getDate());
        assertEquals(0, summaryRepository.findSummaryByDate("20221231").size());
    }

    @Test
    void findSummaryByCategory() {
        List<DailyActivitySummary> summaryList = summaryRepository.findSummaryByCategory("walking");
        assertEquals(1, summaryList.size());
        assertEquals("walking", summaryList.get(0).getCategory());
        assertEquals(0, summaryRepository.findSummaryByCategory("cycling").size());
    }

    @Test
    void deleteSummaryByDate() {
        // BEFORE delete by date
        List<DailyActivitySummary> summaryList = summaryRepository.findSummaryByDate("20220101");
        assertEquals(1, summaryList.size());
        // AFTER delete by date
        summaryList = summaryRepository.deleteSummaryByDate("20220101");
        assertEquals(1, summaryList.size());
        assertEquals("20220101", summaryList.get(0).getDate());
        summaryList = summaryRepository.findSummaryByDate("20220101");
        assertEquals(0, summaryList.size());
    }

    @Test
    void count() {
        assertEquals(2, summaryRepository.count());
    }
}