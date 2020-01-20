package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Relation;

import com.jasonette.seed.Rawfood.Database.Entity.Aliment;
import com.jasonette.seed.Rawfood.Database.Entity.MealAliment;
import com.jasonette.seed.Rawfood.Database.Entity.MealReceipe;
import com.jasonette.seed.Rawfood.Database.Entity.Receipe;

@Dao
public interface MealReceipeDao {
    class MealReceipeFull extends MealReceipe {
        @Relation(parentColumn = "receipeId", entityColumn = "id")
        Receipe receipeDetail;
    }
    @Insert
    long insert(MealReceipe mealReceipe);
}