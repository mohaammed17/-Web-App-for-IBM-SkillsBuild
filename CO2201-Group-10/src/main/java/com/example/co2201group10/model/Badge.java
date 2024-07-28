package com.example.co2201group10.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.*;

@Entity
public class Badge {
    @Id @GeneratedValue
    private int id;
    private String name;
    @Column(length = 100000)
    private String description;
    @Column(length = 100000)
    private String link;
    private Boolean isUnlocked = false;

    /*
     * Condition enum corresponds a badge to a certain unlock type in the Unlock function
     * @Author Ben Seager
     */
    public enum Condition {
        Level, Courses, Daily
    }

    private Condition unlockCondition;
    private int evalValue;


    public Badge () {}

    public Badge(String name, String description, String link, Condition unlockCondition, int evalValue) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.unlockCondition = unlockCondition;
        this.evalValue = evalValue;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(Boolean unlocked) {
        isUnlocked = unlocked;
    }

    /*
    *@Param Takes in a user to access badges contained within their individual reward handler
    *@Return True or False depending on whether the badge's unlock condition has been met
    * @Author Ben Seager
     */

    public boolean unlock (User user) {
        switch (unlockCondition) {
            case Level:
                if (user.getProgressionHandler().getCurrentLevel() == evalValue) {
                    return Boolean.TRUE;
                }
                break;
            case Courses:
                if (user.getCoursesFinished().size() >= evalValue) {
                    return Boolean.TRUE;
                }
                break;
            case Daily:
                Map<String, Integer> daysCompleted = new HashMap<String, Integer>();

                for (CourseTime courseTime : user.getCourseTimes()) {
                    if (courseTime.getEndDate() == null) break;
                    String key =
                        String.valueOf(courseTime.getEndDate().getDayOfMonth()) +
                        String.valueOf(courseTime.getEndDate().getMonth()) +
                        String.valueOf(courseTime.getEndDate().getYear());

                    daysCompleted.put(key, daysCompleted.getOrDefault(key, 0) + 1);
                }

                int max = 0;
                for (Map.Entry<String, Integer> entry : daysCompleted.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    max = (max > value) ? max : value;
                }
                if (max >= evalValue) return Boolean.TRUE;
                break;
        }
        return Boolean.FALSE;
    }
}
