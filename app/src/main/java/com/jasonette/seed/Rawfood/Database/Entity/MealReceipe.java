package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "meal_receipe",
        primaryKeys = {"receipeId", "mealId"},
        foreignKeys = {
        @ForeignKey(
                entity = Receipe.class,
                parentColumns = "id",
                childColumns = "receipeId",
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = Meal.class,
                parentColumns = "id",
                childColumns = "mealId",
                onDelete = ForeignKey.CASCADE
        )
})
public class MealReceipe {
    public long receipeId;
    public long mealId;

    public MealReceipe(long receipeId, long mealId) {
        this.receipeId = receipeId;
        this.mealId = mealId;
    }
}
