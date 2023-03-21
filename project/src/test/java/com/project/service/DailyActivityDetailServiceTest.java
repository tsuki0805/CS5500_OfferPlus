package com.project.service;

import com.project.model.DailyActivityDetail;
import com.project.repository.DetailRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DailyActivityDetailServiceTest {

    @Mock
    private DetailRepository detailRepository;

    @InjectMocks
    private DailyActivityDetailService dailyActivityDetailService;

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
    public void testGetAllDailyActivityDetailByDate() {
        List<DailyActivityDetail> details1 = Arrays.asList(
            new DailyActivityDetail("10001", "walking", 136,
                1400, "20220101", 200, 100,
                "20220101T132707-0800", "20220101T132847-0800",
                70),
            new DailyActivityDetail("10002", "cycling", 200,
                1400, "20220101", 106, 200,
                "20220101T132707-0800", "20220101T132847-10800",
                80));
        when(detailRepository.findDetailByDate("20220101")).thenReturn(details1);
        List<DailyActivityDetail> response1 = dailyActivityDetailService.getAllDailyActivityDetailByDate(
            "20220101");
        assertEquals(details1, response1);

        List<DailyActivityDetail> details2 = Arrays.asList(
            new DailyActivityDetail("10003", "walking", 123,
                1400, "20220102", 120, 130,
                "20220102T132707-0800", "20220102T132847-1010",
                140),
            new DailyActivityDetail("10004", "running", 155,
                1400, "20220102", 60, 45,
                "20220102T132707-0800", "20220102T132847-1010",
                160));
        when(detailRepository.findDetailByDate("20220102")).thenReturn(details2);
        List<DailyActivityDetail> response2 = dailyActivityDetailService.getAllDailyActivityDetailByDate(
            "20220102");
        assertEquals(details2, response2);
    }

    @Test
    public void testGetAllDailyActivityDetailByDate_WhenDateDoesNotExist() {
        List<DailyActivityDetail> details = dailyActivityDetailService.getAllDailyActivityDetailByDate(
            "20220103");
        assertNotNull(details);
        assertTrue(details.isEmpty());
    }

    @Test
    public void testGetAllDailyActivityDetailByCategory() {
        List<DailyActivityDetail> details1 = Arrays.asList(
            new DailyActivityDetail("10001", "walking", 136,
                1400, "20220101", 200, 100,
                "20220101T132707-0800", "20220101T132847-0800",
                70),
            new DailyActivityDetail("10003", "walking", 123,
                1400, "20220102", 120, 130,
                "20220102T132707-0800", "20220102T132847-1010",
                140));
        when(detailRepository.findDetailByCategory("walking")).thenReturn(details1);
        List<DailyActivityDetail> response1 = dailyActivityDetailService.getAllDailyActivityDetailByCategory(
            "walking");
        assertEquals(details1, response1);

        List<DailyActivityDetail> details2 = List.of(
            new DailyActivityDetail("10004", "running", 155,
                1400, "20220102", 60, 45,
                "20220102T132707-0800", "20220102T132847-1010",
                160));
        when(detailRepository.findDetailByCategory("running")).thenReturn(details2);
        List<DailyActivityDetail> response2 = dailyActivityDetailService.getAllDailyActivityDetailByCategory(
            "running");
        assertEquals(details2, response2);
    }

    @Test
    public void testGetAllDailyActivityDetailByCategory_WhenCategoryDoesNotExist() {
        List<DailyActivityDetail> details = dailyActivityDetailService.getAllDailyActivityDetailByCategory(
            "swimming");
        assertNotNull(details);
        assertTrue(details.isEmpty());
    }

    @Test
    public void testDeleteDailyActivityDetailByDate()  {
        List<DailyActivityDetail> details1 = Arrays.asList(
            new DailyActivityDetail("10001", "walking", 136,
                1400, "20220101", 200, 100,
                "20220101T132707-0800", "20220101T132847-0800",
                70),
            new DailyActivityDetail("10002", "cycling", 200,
                1400, "20220101", 106, 200,
                "20220101T132707-0800", "20220101T132847-10800",
                80));
        when(detailRepository.deleteDetailByDate("20220102")).thenReturn(details1);
        List<DailyActivityDetail> response1 = dailyActivityDetailService.deleteDailyActivityDetailByDate(
            "20220102");
        assertEquals(details1, response1);

        List<DailyActivityDetail> details2 = Arrays.asList(
            new DailyActivityDetail("10003", "walking", 123,
                1400, "20220102", 120, 130,
                "20220102T132707-0800", "20220102T132847-1010",
                140),
            new DailyActivityDetail("10004", "running", 155,
                1400, "20220102", 60, 45,
                "20220102T132707-0800", "20220102T132847-1010",
                160));
        when(detailRepository.deleteDetailByDate("20220101")).thenReturn(details2);
        List<DailyActivityDetail> response2 = dailyActivityDetailService.deleteDailyActivityDetailByDate(
            "20220101");
        assertEquals(details2, response2);
    }

    @Test
    public void testDeleteDailyActivityDetailByDate_WhenDateDoesNotExist() {
        List<DailyActivityDetail> details = dailyActivityDetailService.deleteDailyActivityDetailByDate(
            "20220103");
        assertNotNull(details);
        assertTrue(details.isEmpty());
    }

    @Test
    public void testAddDailyActivityDetail(){

        DailyActivityDetail AddedDetails = new DailyActivityDetail("10005", "walking", 136,
            1400, "20220104", 200, 100,
            "20220101T132707-0800", "20220101T132847-0800",
            70);
        when(detailRepository.save(AddedDetails)).thenReturn(AddedDetails);
        DailyActivityDetail response2 = dailyActivityDetailService.addDailyActivityDetail(
            AddedDetails);
        assertEquals(AddedDetails, response2);
    }


}
