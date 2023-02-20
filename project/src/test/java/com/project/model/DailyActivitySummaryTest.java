package com.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DailyActivitySummaryTest {
    private DailyActivitySummary activitySummary= new DailyActivitySummary("10001", "walking", 100, 1400, "20220101",
            100, 100, 200);

    @Test
    void getId() {
        assertEquals("10001", activitySummary.getId());
    }

    @Test
    void getCategory() {
        assertEquals("walking", activitySummary.getCategory());
    }

    @Test
    void getCalories() {
        assertEquals(100, activitySummary.getCalories());
    }

    @Test
    void getCaloriesIdle() {
        assertEquals(1400, activitySummary.getCaloriesIdle());
    }

    @Test
    void getDate() {
        assertEquals("20220101", activitySummary.getDate());
    }

    @Test
    void getDistance() {
        assertEquals(100, activitySummary.getDistance());
    }

    @Test
    void getDuration() {
        assertEquals(100, activitySummary.getDuration());
    }

    @Test
    void getSteps() {
        assertEquals(200, activitySummary.getSteps());
    }
}