package com.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DailyActivityDetailTest {
    private DailyActivityDetail activityDetail = new DailyActivityDetail("10001", "walking", 136,
            1400, "20220101", 200, 100,
            "20220101T132707-0800", "20220101T132847-0800",
            70);

    @Test
    void getId() {
        assertEquals("10001", activityDetail.getId());
    }

    @Test
    void getCategory() {
        assertEquals("walking", activityDetail.getCategory());
    }

    @Test
    void getCalories() {
        assertEquals(136, activityDetail.getCalories());
    }

    @Test
    void getCaloriesIdle() {
        assertEquals(1400, activityDetail.getCaloriesIdle());
    }

    @Test
    void getDate() {
        assertEquals("20220101", activityDetail.getDate());
    }

    @Test
    void getDistance() {
        assertEquals(200, activityDetail.getDistance());
    }

    @Test
    void getDuration() {
        assertEquals(100, activityDetail.getDuration());
    }

    @Test
    void getEndTime() {
        assertEquals("20220101T132847-0800", activityDetail.getEndTime());
    }

    @Test
    void getStartTime() {
        assertEquals("20220101T132707-0800", activityDetail.getStartTime());
    }

    @Test
    void getSteps() {
        assertEquals(70, activityDetail.getSteps());
    }
}