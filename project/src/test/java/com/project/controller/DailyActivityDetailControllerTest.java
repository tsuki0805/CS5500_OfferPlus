package com.project.controller;

import com.project.model.DailyActivityDetail;
import com.project.repository.DetailRepository;
import com.project.service.DailyActivityDetailService;
import org.bson.types.ObjectId;
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
public class DailyActivityDetailControllerTest {

    @Mock
    private DetailRepository detailRepository;

    @Mock
    private DailyActivityDetailService dailyActivityDetailService;

    @InjectMocks
    private DailyActivityDetailController dailyActivityDetailController;

    @BeforeEach
    void setUp() {
        List<DailyActivityDetail> details = Arrays.asList(
            new DailyActivityDetail("10001", "walking", 136,
                1400, "20220101", 200, 100,
                "20220101T132707-0800", "20220101T132847-0800",
                70),
            new DailyActivityDetail("10002", "cycling", 200,
                1400, "20220101", 106, 200,
                "20220101T132707-0800", "20220101T132847-10800",
                80),
            new DailyActivityDetail("10003", "walking", 123,
                1400, "20220102", 120, 130,
                "20220102T132707-0800", "20220102T132847-1010",
                140),
            new DailyActivityDetail("10004", "running", 155,
                1400, "20220102", 60, 45,
                "20220102T132707-0800", "20220102T132847-1010",
                160));
        detailRepository.saveAll(details);
    }

    @AfterEach
    void tearDown() {
        detailRepository.deleteAll();
    }

    @Test
    public void testFetchAllDailyActivityDetailByDate() {
        List<DailyActivityDetail> details1 = Arrays.asList(
            new DailyActivityDetail("10001", "walking", 136,
                1400, "20220101", 200, 100,
                "20220101T132707-0800", "20220101T132847-0800",
                70),
            new DailyActivityDetail("10002", "cycling", 200,
                1400, "20220101", 106, 200,
                "20220101T132707-0800", "20220101T132847-10800",
                80));
        when(dailyActivityDetailService.getAllDailyActivityDetailByDate("20220101")).thenReturn(
            details1);
        ResponseEntity<List<DailyActivityDetail>> response1 = dailyActivityDetailController.fetchAllDailyActivityDetailByDate(
            "20220101");
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(details1, response1.getBody());

        List<DailyActivityDetail> details2 = Arrays.asList(
            new DailyActivityDetail("10003", "walking", 123,
                1400, "20220102", 120, 130,
                "20220102T132707-0800", "20220102T132847-1010",
                140),
            new DailyActivityDetail("10004", "running", 155,
                1400, "20220102", 60, 45,
                "20220102T132707-0800", "20220102T132847-1010",
                160));
        when(dailyActivityDetailService.getAllDailyActivityDetailByDate("20220102")).thenReturn(
            details2);
        ResponseEntity<List<DailyActivityDetail>> response2 = dailyActivityDetailController.fetchAllDailyActivityDetailByDate(
            "20220102");
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(details2, response2.getBody());
    }

    @Test
    public void testGetAllDailyActivityDetailByDate_WhenDateDoesNotExist() {
        ResponseEntity<List<DailyActivityDetail>> response = dailyActivityDetailController.fetchAllDailyActivityDetailByDate(
            "20220103");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testFetchAllDailyActivityDetailByCategory() {
        List<DailyActivityDetail> details1 = Arrays.asList(
            new DailyActivityDetail("10001", "walking", 136,
                1400, "20220101", 200, 100,
                "20220101T132707-0800", "20220101T132847-0800",
                70),
            new DailyActivityDetail("10003", "walking", 123,
                1400, "20220102", 120, 130,
                "20220102T132707-0800", "20220102T132847-1010",
                140));
        when(dailyActivityDetailService.getAllDailyActivityDetailByCategory("walking")).thenReturn(
            details1);
        ResponseEntity<List<DailyActivityDetail>> response1 = dailyActivityDetailController.fetchAllDailyActivityDetailByCategory(
            "walking");
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(details1, response1.getBody());

        List<DailyActivityDetail> details2 = List.of(
            new DailyActivityDetail("10004", "running", 155,
                1400, "20220102", 60, 45,
                "20220102T132707-0800", "20220102T132847-1010",
                160));
        when(dailyActivityDetailService.getAllDailyActivityDetailByCategory("running")).thenReturn(
            details2);
        ResponseEntity<List<DailyActivityDetail>> response2 = dailyActivityDetailController.fetchAllDailyActivityDetailByCategory(
            "running");
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(details2, response2.getBody());
    }

    @Test
    public void testGetAllDailyActivityDetailByCategory_WhenCategoryDoesNotExist() {
        ResponseEntity<List<DailyActivityDetail>> response = dailyActivityDetailController.fetchAllDailyActivityDetailByCategory(
            "swimming");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteDailyActivityDetailByDate() {
        List<DailyActivityDetail> details1 = Arrays.asList(
            new DailyActivityDetail("10001", "walking", 136,
                1400, "20220101", 200, 100,
                "20220101T132707-0800", "20220101T132847-0800",
                70),
            new DailyActivityDetail("10002", "cycling", 200,
                1400, "20220101", 106, 200,
                "20220101T132707-0800", "20220101T132847-10800",
                80));
        when(dailyActivityDetailService.deleteDailyActivityDetailByDate("20220102")).thenReturn(
            details1);
        ResponseEntity<List<DailyActivityDetail>> response1 = dailyActivityDetailController.deleteAllDailyActivityDetailByDate(
            "20220102");
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(details1, response1.getBody());
    }

    @Test
    public void testDeleteDailyActivityDetailByDate_WhenDateDoesNotExist() {
        ResponseEntity<List<DailyActivityDetail>> response = dailyActivityDetailController.deleteAllDailyActivityDetailByDate(
            "20220103");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testAddDailyActivityDetail(){

        DailyActivityDetail AddedDetails = new DailyActivityDetail("10005", "walking", 136,
                1400, "20220104", 200, 100,
                "20220101T132707-0800", "20220101T132847-0800",
                70);

        when(dailyActivityDetailService.addDailyActivityDetail(AddedDetails)).thenReturn(
            AddedDetails);
        ResponseEntity<DailyActivityDetail> response1 = dailyActivityDetailController.addDailyActivityDetail(AddedDetails);
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(AddedDetails, response1.getBody());
    }



}
