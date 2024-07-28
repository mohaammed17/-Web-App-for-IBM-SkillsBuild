package com.example.co2201group10.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CourseTime {
    @Id @GeneratedValue
    private long id;
    private long courseId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String stringStartDate () {
        return
                String.format("%02d", startDate.getHour()) + ":" +
                        String.format("%02d", startDate.getMinute()) + " " +
                        String.format("%02d", startDate.getDayOfMonth()) + "/" +
                        String.format("%02d", startDate.getMonthValue()) + "/" +
                        String.format("%04d", startDate.getYear()
                    );
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String stringEndDate () {
        return
                String.format("%02d", endDate.getHour()) + ":" +
                        String.format("%02d", endDate.getMinute()) + " " +
                        String.format("%02d", endDate.getDayOfMonth()) + "/" +
                        String.format("%02d", endDate.getMonthValue()) + "/" +
                        String.format("%04d", endDate.getYear());
    }
}
