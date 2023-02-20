package com.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("dailyActivitySummary")
public class DailyActivitySummary {

    @Id
    private String id;

    private String category;
    private int calories;
    private int caloriesIdle;
    private String date;
    private int distance;
    private int duration;
    private int steps;

    public DailyActivitySummary() {
    }

    public DailyActivitySummary(String id, String category, int calories, int caloriesIdle, String date, int distance, int duration, int steps) {
        super();
        this.id = id;
        this.category = category;
        this.calories = calories;
        this.caloriesIdle = caloriesIdle;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.steps = steps;
    }

    public DailyActivitySummary(String category, int calories, int caloriesIdle, String date, int distance, int duration, int steps) {
        super();
        this.category = category;
        this.calories = calories;
        this.caloriesIdle = caloriesIdle;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.steps = steps;
    }

    public DailyActivitySummary(String category, int calories, int caloriesIdle, String date, int distance, int duration) {
        super();
        this.category = category;
        this.calories = calories;
        this.caloriesIdle = caloriesIdle;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
    }

    public DailyActivitySummary(String category, int caloriesIdle, String date, int distance, int duration) {
        super();
        this.category = category;
        this.caloriesIdle = caloriesIdle;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
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

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "DailyActivitySummary{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", calories=" + calories +
                ", caloriesIdle=" + caloriesIdle +
                ", date='" + date + '\'' +
                ", distance=" + distance +
                ", duration=" + duration +
                ", steps=" + steps + '\'' +
                '}';
    }
}
