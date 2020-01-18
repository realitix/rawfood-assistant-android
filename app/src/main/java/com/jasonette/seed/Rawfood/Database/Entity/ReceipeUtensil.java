package com.jasonette.seed.Rawfood.Database.Entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"receipeId", "utensilId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Receipe.class,
                        parentColumns = "id",
                        childColumns = "receipeId",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Utensil.class,
                        parentColumns = "id",
                        childColumns = "utensilId",
                        onDelete = ForeignKey.CASCADE
                )
        })
public class ReceipeUtensil {
    public long receipeId;
    public long utensilId;
}
