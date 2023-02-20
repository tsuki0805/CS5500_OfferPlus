package com.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("dailyActivityDetails")
public class DailyActivityDetail {

    @Id
    private String id;

    private String category;
    private int calories;
    private int caloriesIdle;
    private String date;
    private int distance;
    private int duration;
    private String startTime;
    private String endTime;
    private int steps;

    public DailyActivityDetail() {
    }

    public DailyActivityDetail(String id, String category, int calories, int caloriesIdle, String date, int distance, int duration, String startTime, String endTime, int steps) {
        super();
        this.id = id;
        this.category = category;
        this.calories = calories;
        this.caloriesIdle = caloriesIdle;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.steps = steps;
    }

    public DailyActivityDetail(String category, int calories, int caloriesIdle, String date, int distance, int duration, String startTime, String endTime, int steps) {
        super();
        this.category = category;
        this.calories = calories;
        this.caloriesIdle = caloriesIdle;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.steps = steps;
    }

    public DailyActivityDetail(String category, int caloriesIdle, String date, int distance, int duration, String startTime, String endTime) {
        super();
        this.category = category;
        this.caloriesIdle = caloriesIdle;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DailyActivityDetail(String category, int calories, int caloriesIdle, String date, int distance, int duration, String startTime, String endTime) {
        super();
        this.category = category;
        this.calories = calories;
        this.caloriesIdle = caloriesIdle;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCaloriesIdle() {
        return caloriesIdle;
    }

    public void setCaloriesIdle(int caloriesIdle) {
        this.caloriesIdle = caloriesIdle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "DailyActivityDetail{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", calories=" + calories +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
