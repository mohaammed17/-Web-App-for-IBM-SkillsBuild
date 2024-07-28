package com.example.co2201group10.model;

import com.example.co2201group10.repository.BadgeRepo;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RewardHandler {

    @Id @GeneratedValue
    private int id;
    @ManyToMany
    List<Badge> badgesUnlocked = new ArrayList<>();
    @ManyToMany
    List<Badge> badgesLocked = new ArrayList<>();

    public RewardHandler () {}

    public RewardHandler (BadgeRepo badgeRepo) {
        for (Badge badge : badgeRepo.findAll()) {
            badgesLocked.add(badge);
        }
    }

    public void addBadges (BadgeRepo badgeRepo) {
        for (Badge badge : badgeRepo.findAll()) {
            badgesLocked.add(badge);
        }
    }

    public List<Badge> getBadgesUnlocked() {
        return badgesUnlocked;
    }

    public void setBadgesUnlocked(List<Badge> badgesUnlocked) {
        this.badgesUnlocked = badgesUnlocked;
    }

    public void addBadgeUnlocked (Badge badge) {
        badgesUnlocked.add(badge);
    }

    /* Loops through the users locked badges and checks against its corresponding condition whether it can be unlocked
     * @param Takes in a user so their individual badges can be accessed
     * @return Sets the badges unlocked state to true if unlock() returns True and adds it to an array of unlocked badges
     * @Author Ben Seager
     */
    public void unlockBadges (User user) {
        for (Badge badge : badgesLocked) {
            if (badge.unlock(user) && !badgesUnlocked.contains(badge)) {
                badge.setUnlocked(true);
                badgesUnlocked.add(badge);
            }
        }
        for (Badge badge: badgesUnlocked) {
            badgesLocked.remove(badge);
        }
    }

    public List<Badge> getBadgesLocked() {
        return badgesLocked;
    }

    public void setBadgesLocked(List<Badge> badgesLocked) {
        this.badgesLocked = badgesLocked;
    }

    public void addBadgeLocked (Badge badge) { badgesLocked.add(badge); }

    public void clearBadges () {
        badgesLocked = new ArrayList<Badge>();
        badgesUnlocked = new ArrayList<Badge>();
    }
}
