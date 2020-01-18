package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "aliment_category",
        indices = {@Index(value = {"name"}, unique = true)}
        )
public class AlimentCategory {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public Boolean fresh;

    public AlimentCategory(String name, Boolean fresh) {
        this.name = name;
        this.fresh = fresh;
    }
}
