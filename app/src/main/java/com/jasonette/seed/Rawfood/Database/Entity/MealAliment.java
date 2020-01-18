package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_aliment",
        primaryKeys = {"alimentId", "mealId"},
        foreignKeys = {
        @ForeignKey(
                entity = Aliment.class,
                parentColumns = "id",
                childColumns = "alimentId",
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = Meal.class,
                parentColumns = "id",
                childColumns = "mealId",
                onDelete = ForeignKey.CASCADE
        )
})
public class MealAliment {
    public long alimentId;
    public long mealId;
    public int quantity;

    public MealAliment(long alimentId, long mealId, int quantity) {
        this.alimentId = alimentId;
        this.mealId = mealId;
        this.quantity = quantity;
    }
}
