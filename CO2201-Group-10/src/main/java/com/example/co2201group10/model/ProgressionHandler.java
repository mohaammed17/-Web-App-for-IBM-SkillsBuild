package com.example.co2201group10.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ProgressionHandler {

    @Id @GeneratedValue
    private long id;

    private int currentExp = 0; // Initial points
    private int currentLevel = 1; // Initial level
    private int nextLevelRequirement = 100; // Exp needed to level up
    private int levelUpIncrease = 50; // Increase in Exp needed per level

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public int getCurrentPoints() {
        return currentExp;
    }

    private void setCurrentPoints(int currentPoints) {
        this.currentExp = currentPoints;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    private void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    /* need this getter for progress bar
    @author Stuart
     */
    public int getNextLevelRequirement() {
        return nextLevelRequirement;
    }
    public void setNextLevelRequirement(int nextLevelRequirement) {
        this.nextLevelRequirement = nextLevelRequirement;
    }

    /* Method to earn points, calls check for level up
     * @param expPoints int > 0 of exp to add
     * @author Alina
     */
    public void earnPoints (int expPoints) {

        if (expPoints < 0) {
            System.out.println("Error : Points earned must be greater than 0");
            return;
        }

        this.currentExp += expPoints; // sets currentExp as the points earned.
        checkLevelUp(); // checks the level of the user

    }

    /* Check if level up can be achieved
     * @Author Alina
     */
    private void checkLevelUp() {
        while (currentExp >= nextLevelRequirement) {
            currentLevel++; // increases level if points are > than points needed for level up
            currentExp -= nextLevelRequirement; // works out remaining level for next level up
            nextLevelRequirement += levelUpIncrease;// Increase points needed for the next level
        }
    }

}