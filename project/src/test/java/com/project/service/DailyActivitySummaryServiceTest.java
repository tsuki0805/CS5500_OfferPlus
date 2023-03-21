package com.project.service;

import com.project.model.DailyActivitySummary;
import com.project.repository.SummaryRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DailyActivitySummaryServiceTest {

  @Mock
  private SummaryRepository summaryRepository;

  @InjectMocks
  private DailyActivitySummaryService summaryService;

  @BeforeEach
  void setUp() {
    List<DailyActivitySummary> summaries = List.of(
        new DailyActivitySummary(
            "walking",
            136,
            1400,
            "20220101",
            200,
            100,
            599),
        new DailyActivitySummary(
            "running",
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
    summaryRepository.saveAll(summaries);
  }

  @AfterEach
  void tearDown() {
    summaryRepository.deleteAll();
  }

  @Test
  public void testGetListOfSummaryByDate(){
    String date = "20220101";
    List<DailyActivitySummary> summaryList = List.of(
        new DailyActivitySummary(
            "walking",
            136,
            1400,
            "20220101",
            200,
            100,
            599),
        new DailyActivitySummary(
            "running",
            136,
            1400,
            "20220101",
            200,
            100,
            599));

    when(summaryRepository.findSummaryByDate(date)).thenReturn(summaryList);
    List<DailyActivitySummary> response1 = summaryService.getListOfSumByDate(date);
    assertEquals(summaryList, response1);
  }

  @Test
  public void testGetListOfSummaryByDate_WhenDateDoesNotExist() {
    List<DailyActivitySummary> summary = summaryService.getListOfSumByDate("20220103");
    assertNotNull(summary);
    assertTrue(summary.isEmpty());
  }

  @Test
  public void testGetDailyCaloriesSumByDateAndCat(){
    String date = "20220101";
    String category = "running";

    List<DailyActivitySummary> summaryList = List.of(
        new DailyActivitySummary(
            "running",
            136,
            1400,
            "20220101",
            200,
            100,
            599));

    when(summaryRepository.findSummaryByDateAndCategory(date,category)).thenReturn(summaryList);
    List<DailyActivitySummary> response1 = summaryService.getCalByDateAndCat(date,category);
    assertEquals(summaryList, response1);
  }

  @Test
  public void testGetDailyCaloriesSumByDateAndCat_WhenDateCatDoesNotExist() {
    List<DailyActivitySummary> summary = summaryService.getCalByDateAndCat("20220103","running");
    assertNotNull(summary);
    assertTrue(summary.isEmpty());
  }

  @Test
  public void  testDeleteAllDailyActivitySummaryByDate() {
    String date = "20220102";
    List<DailyActivitySummary> summaryList = List.of(
        new DailyActivitySummary(
            "walking",
            136,
            1400,
            "20220101",
            200,
            100,
            599),
        new DailyActivitySummary(
            "running",
            136,
            1400,
            "20220101",
            200,
            100,
            599));

    when(summaryRepository.deleteSummaryByDate(date)).thenReturn(summaryList);
    List<DailyActivitySummary> response1 = summaryService.deleteDailyActivitySummaryByDate(date);
    assertEquals(summaryList, response1);
  }

  @Test
  public void testDeleteAllDailyActivitySummaryByDate_WhenDateDoesNotExist() {
    List<DailyActivitySummary> summary = summaryService.deleteDailyActivitySummaryByDate("20220103");
    assertNotNull(summary);
    assertTrue(summary.isEmpty());
  }

  @Test
  public void testAddDailyActivityDetail(){
    DailyActivitySummary addedSummary =
        new DailyActivitySummary(
            "walking",
            136,
            1400,
            "20220105",
            200,
            100,
            599);
    when(summaryRepository.save(addedSummary)).thenReturn(addedSummary);
    DailyActivitySummary response1 = summaryService.addDailyActivitySummary(addedSummary);
    assertEquals(addedSummary, response1);
  }

  @Test
  public void testUpdatedDailyActivityDetail(){
    DailyActivitySummary updatedSummary =
        new DailyActivitySummary(
            "walking",
            136,
            1400,
            "20220101",
            200,
            100,
            7500);
    when(summaryRepository.save(updatedSummary)).thenReturn(updatedSummary);
    DailyActivitySummary response1 = summaryService.updateDailyActivitySummaryByDate("20220101",updatedSummary);
    assertEquals(updatedSummary, response1);
  }




}

