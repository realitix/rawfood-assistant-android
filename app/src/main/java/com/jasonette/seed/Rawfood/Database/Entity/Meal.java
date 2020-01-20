package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Meal {
    @PrimaryKey(autoGenerate = true)
    public long id;
    // Timestamp
    public long timestamp;
    public int nb_people;
    public String description;

    public Meal(long timestamp, int nb_people, String description) {
        this.timestamp = timestamp;
        this.nb_people = nb_people;
        this.description = description;
    }
}
