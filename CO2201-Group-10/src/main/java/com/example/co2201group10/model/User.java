package com.example.co2201group10.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    private Avatar avatar = new Avatar();
    private String name;
    @ManyToMany
    private List<Course> coursesInProgress = new ArrayList<Course>();
    @ManyToMany
    private List<Course> coursesFinished = new ArrayList<Course>();
    @OneToOne(cascade = CascadeType.ALL)
    private ProgressionHandler progressionHandler = new ProgressionHandler();
    @OneToOne(cascade = CascadeType.ALL)
    private RewardHandler rewardHandler = new RewardHandler();
    @OneToMany(cascade = CascadeType.ALL)
    private List<CourseTime> courseTimes = new ArrayList<CourseTime>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> friends = new ArrayList<User>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> friendRequestsIncoming = new ArrayList<User>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> friendRequestsOutgoing = new ArrayList<User>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCoursesInProgress() {
        return coursesInProgress;
    }

    public void addCourseInProgress(Course course) {
        coursesInProgress.add(course);
        coursesInProgress.sort(Comparator.comparing(Course::getName));
    }

    public void removeCourseInProgress(Course course) {
        coursesInProgress.remove(course);
    }

    public List<Course> getCoursesFinished() {
        return coursesFinished;
    }

    public void addCourseFinished(Course course) {
        removeCourseInProgress(course);
        coursesFinished.add(course);
        coursesFinished.sort(Comparator.comparing(Course::getName));
    }

    public void removeCourseFinished(Course course) {
        coursesFinished.remove(course);
    }

    public void finishCourse(Course course) {
        coursesInProgress.remove(course);
        coursesFinished.add(course);
    }

    public void unfinishCourse(Course course) {
        coursesFinished.remove(course);
        coursesInProgress.add(course);
        coursesInProgress.sort(Comparator.comparing(Course::getName));
    }

    public void clearCourses() {
        coursesInProgress.clear();
        coursesFinished.clear();
    }

    public boolean hasCourseInProgress(Course course) {
        return coursesInProgress.contains(course);
    }

    public boolean hasCourseFinished(Course course) {
        return coursesFinished.contains(course);
    }

    public boolean hasCourse(Course course) {
        return hasCourseInProgress(course) || hasCourseFinished(course);
    }

    /* This method returns a list of courses that are recommended for the user to take next.
    * @param courseList - a list of all courses from the database
    * @return - a list of courses that are recommended for the user to take next
    * @Author - Jakob (jkep1)
    */
    public List<Course> createCoursesRecommended(List<Course> courseList) {
        List<Course> recommended = new ArrayList<Course>();
        for (Course course : courseList) { // For every course
            List<Course> prerequisites = course.getPrerequisites(); // Get the prerequisites
            for (Course prereq : prerequisites) { // For every prerequisite
                if (!hasCourse(prereq) && prereq.hasAllPrerequisites(this)) { // If the user hasn't taken the prerequisite and has all the prerequisites for the prerequisite
                    if (!recommended.contains(prereq)) recommended.add(prereq); // Add the prereq to the recommended list
                } else if (course.hasAllPrerequisites(this) && !hasCourse(course)) { // If the user has all the prerequisites for the course and hasn't taken the course
                    if (!recommended.contains(course)) recommended.add(course); // Add the course to the recommended list
                }
            }
        }
        recommended.sort(Comparator.comparing(Course::getName));
        return recommended;
    }

    public ProgressionHandler getProgressionHandler() {
        return progressionHandler;
    }

    public void setProgressionHandler(ProgressionHandler progressionHandler) {
        this.progressionHandler = progressionHandler;
    }

    public RewardHandler getRewardHandler() {
        return rewardHandler;
    }

    public void setRewardHandler(RewardHandler rewardHandler) {
        this.rewardHandler = rewardHandler;
    }

    public List<CourseTime> getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(List<CourseTime> courseTimes) {
        this.courseTimes = courseTimes;
    }

    public void addCourseTime (CourseTime courseTime) {
        this.courseTimes.add(courseTime);
    }

    public CourseTime getCourseTimeById (long id) {
        for (CourseTime courseTime : courseTimes) {
            if (courseTime.getCourseId() == id) {
                return courseTime;
            }
        }
        return null;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public void removeFriend(User friend) {
        friends.remove(friend);
        friendRequestsIncoming.remove(friend);
        friendRequestsOutgoing.remove(friend);
    }

    public List<User> getFriendRequestsIncoming() {
        return friendRequestsIncoming;
    }

    public void setFriendRequestsIncoming(List<User> friendRequests) {
        this.friendRequestsIncoming = friendRequests;
    }

    public void addFriendRequestIncoming(User friendRequest) {
        friendRequestsIncoming.add(friendRequest);
    }

    public void removeFriendRequestIncoming(User friendRequest) {
        friendRequestsIncoming.remove(friendRequest);
    }

    public List<User> getFriendRequestsOutgoing() {
        return friendRequestsOutgoing;
    }

    public void setFriendRequestsOutgoing(List<User> friendRequestsOutgoing) {
        this.friendRequestsOutgoing = friendRequestsOutgoing;
    }

    public void addFriendRequestOutgoing(User friendRequest) {
        friendRequestsOutgoing.add(friendRequest);
    }

    public void removeFriendRequestOutgoing(User friendRequest) {
        friendRequestsOutgoing.remove(friendRequest);
    }

    public boolean hasFriend(User friend) {
        return friends.contains(friend);
    }

    public boolean hasFriendRequest(User friendRequest) {
        return friendRequestsIncoming.contains(friendRequest) || friendRequestsOutgoing.contains(friendRequest);
    }

    public void acceptFriendRequest(User friendRequest) {
        if (
            !friendRequestsIncoming.contains(friendRequest) &&
            !friendRequestsOutgoing.contains(friendRequest)
        ) return;
        if (friends.contains(friendRequest)) return;

        friendRequestsIncoming.remove(friendRequest);
        friendRequestsOutgoing.remove(friendRequest);
        friends.add(friendRequest);
    }

    public boolean equals (User other) {
        return Objects.equals(this.id, other.id);
    }

    public Avatar getAvatar() {
        return avatar;
    }



}