package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Meal {
    @PrimaryKey(autoGenerate = true)
    public long id;
    // Timestamp
    public int datetime;
    public int nb_people;
    public String description;

    public Meal(int datetime, int nb_people, String description) {
        this.datetime = datetime;
        this.nb_people = nb_people;
        this.description = description;
    }
}
