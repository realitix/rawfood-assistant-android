package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "receipe")
public class Receipe {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public int nb_people;
    public int stars;

    public Receipe(String name, int nb_people, int stars) {
        this.name = name;
        this.nb_people = nb_people;
        this.stars = stars;
    }
}
