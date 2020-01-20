package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Fts4;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
            entity = AlimentCategory.class,
            parentColumns = "id",
            childColumns = "categoryId",
            onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index(value = {"name"}, unique = true)}
)
public class Aliment {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String nameSearch;
    public long categoryId;
    @Embedded
    public AlimentNutrition nutrition;

    public Aliment(String name, String nameSearch, long categoryId, AlimentNutrition nutrition) {
        this.name = name;
        this.nameSearch = nameSearch;
        this.categoryId = categoryId;
        this.nutrition = nutrition;
    }
}
