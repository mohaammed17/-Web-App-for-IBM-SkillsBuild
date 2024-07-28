package com.example.co2201group10.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String link;
    @ManyToMany
    private List<Course> prerequisites = new ArrayList<Course>();
    private int pointsGained = 25;
    private int points = 25;
    private int currentUsers;
    private int views = 0;
    @OneToMany
    private List<Review> courseReviews = new ArrayList<>();

    public List<Review> getCourseReviews() {return courseReviews;}

    public void setCourseReviews(List<Review> courseReviews) {this.courseReviews = courseReviews;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
        prerequisites.sort((a, b) -> {
            return a.getName().compareTo(b.getName());
        });
        points = pointsGained * (this.prerequisites.size() + 1);
    }
    public void addCourseReview(Review rev){
        courseReviews.add(rev);
    }

    public void addPrerequisite(Course course) {
        prerequisites.add(course);
        prerequisites.sort((a, b) -> {
            return a.getName().compareTo(b.getName());
        });
        points += pointsGained;
    }

    public boolean hasAllPrerequisites(User user) {
        for (Course course : prerequisites) {
            if (!user.getCoursesFinished().contains(course)) {
                return false;
            }
        }
        return true;
    }

    public int getPoints() {
        return points;
    }

    // Getter and setter for currentUsers
    public int getCurrentUsers() {
        return currentUsers;
    }

    public void setCurrentUsers(int currentUsers) {
        this.currentUsers = currentUsers;
    }

    // Method to add a user to the course and increment currentUsers
    public void addCurrentUser() {
        this.currentUsers += 1;

    }

    // Getter and setter for views
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void incrementViews() {
        views++;
    }

    // Method to calculate popularity based on views
    public static List<Course> CalculatePopularity(List<Course> courses) {
        // Sort courses based on the number of views in descending order
        courses.sort(Comparator.comparing(Course::getViews).reversed());

        return courses.subList(0, Math.min(3, courses.size() - 1));
    }

}