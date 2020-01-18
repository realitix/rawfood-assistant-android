package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "receipe_step", foreignKeys = @ForeignKey(
        entity = Receipe.class,
        parentColumns = "id",
        childColumns = "receipeId",
        onDelete = ForeignKey.CASCADE
))
public class ReceipeStep {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long categoryId;
    public int order;
    public String description;
    public int duration;

    public ReceipeStep(int categoryId, int order, String description, int duration) {
        this.categoryId = categoryId;
        this.order = order;
        this.description = description;
        this.duration = duration;
    }
}
