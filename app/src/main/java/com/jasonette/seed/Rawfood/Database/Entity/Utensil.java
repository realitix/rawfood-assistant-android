package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.PrimaryKey;

public class Utensil {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;

    public Utensil(String name) {
        this.name = name;
    }
}
