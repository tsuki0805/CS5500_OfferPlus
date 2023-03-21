package com.project.controller;

import com.project.model.DailyActivityDetail;
import com.project.model.DailyActivitySummary;
import com.project.repository.SummaryRepository;
import com.project.service.DailyActivitySummaryService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DailyActivitySummaryControllerTest {

  @Mock
  private DailyActivitySummaryService summaryService;

  @Mock
  private SummaryRepository summaryRepository;

  @InjectMocks
  private DailyActivitySummaryController summaryController;

  @BeforeEach
  void setUp() {
    List<DailyActivitySummary> list = Arrays.asList(
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
    summaryRepository.saveAll(list);
    summaryService = new DailyActivitySummaryService(summaryRepository);
  }

  @AfterEach
  void tearDown() {
    summaryRepository.deleteAll();
  }

  @Test
  public void testFetchListOfSummaryByDate() {
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

    when(summaryService.deleteDailyActivitySummaryByDate(date)).thenReturn(summaryList);
    ResponseEntity<List<DailyActivitySummary>> response1 = summaryController.fetchListOfSummaryByDate("20220102");
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals(summaryList, response1.getBody());
  }

  @Test
  public void testFetchListOfSummaryByDate_WhenDateDoesNotExist() {
    ResponseEntity<List<DailyActivitySummary>> response = summaryController.fetchListOfSummaryByDate("20111111");
    assertEquals(response.getBody().size(),0);
  }

  @Test
  public void testFetchDailyCaloriesSumByDateAndCat(){
    String activityDate = "20220101";
    String category = "walking";

    List<DailyActivitySummary> expected = List.of(
        new DailyActivitySummary(
            "walking",
            136,
            1400,
            "20220101",
            200,
            100,
            599));

    when(summaryService.getCalByDateAndCat(activityDate,category)).thenReturn(expected);
    ResponseEntity<List<DailyActivitySummary>> response1 = summaryController.fetchDailyCaloriesSumByDateAndCat(activityDate,category);
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals(expected, response1.getBody());
  }

  @Test
  public void testFetchDailyCaloriesSumByDateAndCat_WhenCategoryDoesNotExist() {
    ResponseEntity<List<DailyActivitySummary>> response = summaryController.fetchDailyCaloriesSumByDateAndCat("20201111","swimming");
    assertEquals(response.getBody().size(),0);
  }

  @Test
  public void testDeleteAllDailyActivitySummaryByDate() {
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

    when(summaryService.deleteDailyActivitySummaryByDate("20220102")).thenReturn(summaryList);
    ResponseEntity<List<DailyActivitySummary>> response1 = summaryController.deleteAllDailyActivitySummaryByDate("20220102");
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals(summaryList, response1.getBody());
  }

  @Test
  public void testDeleteAllDailyActivitySummaryByDate_WhenDateDoesNotExist() {
    ResponseEntity<List<DailyActivitySummary>> response = summaryController.deleteAllDailyActivitySummaryByDate("20111111");
    assertNull(response.getBody());
  }

  @Test
  public void testAddDailyActivitySummary(){
    DailyActivitySummary addedSummary =
        new DailyActivitySummary(
            "walking",
            136,
            1400,
            "20220103",
            200,
            100,
            599);
    when(summaryService.addDailyActivitySummary(addedSummary)).thenReturn(
        addedSummary);
    ResponseEntity<DailyActivitySummary> response1 = summaryController.addDailyActivitySummary(addedSummary);
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals(addedSummary, response1.getBody());
  }


  @Test
  public void testUpdateDailyActivitySummary(){
    DailyActivitySummary updatedSummary =
        new DailyActivitySummary(
            "walking",
            136,
            1400,
            "20220101",
            200,
            100,
            7500);
    when(summaryService.updateDailyActivitySummaryByDate("20220101", updatedSummary)).thenReturn(
        updatedSummary);
    ResponseEntity<DailyActivitySummary> response1 = summaryController.updateDailyActivitySummaryByDate("20220101", updatedSummary);
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals(updatedSummary, response1.getBody());
  }









}

