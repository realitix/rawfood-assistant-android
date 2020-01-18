package com.jasonette.seed.Rawfood.Database.Entity;

public class AlimentNutrition {
    public int protein;
    public int glucid;
    public int lipid;

    public AlimentNutrition(int protein, int glucid, int lipid) {
        this.protein = protein;
        this.glucid = glucid;
        this.lipid = lipid;
    }
}