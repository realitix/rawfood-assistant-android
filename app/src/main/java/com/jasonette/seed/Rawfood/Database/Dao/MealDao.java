package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Relation;

import com.jasonette.seed.Rawfood.Database.Entity.Aliment;
import com.jasonette.seed.Rawfood.Database.Entity.Meal;
import com.jasonette.seed.Rawfood.Database.Entity.MealAliment;
import com.jasonette.seed.Rawfood.Database.Entity.MealReceipe;

import java.util.List;

@Dao
public interface MealDao {
    class MealFull extends Meal {
        @Relation(parentColumn = "id", entityColumn = "mealId", entity = MealAliment.class)
        List<MealAlimentDao.MealAlimentFull> aliments;
        @Relation(parentColumn = "id", entityColumn = "mealId", entity = MealReceipe.class)
        List<MealReceipeDao.MealReceipeFull> receipes;
    }

    @Query("SELECT * FROM Meal WHERE timestamp BETWEEN :begin AND :end")
    List<Meal> search(long begin, long end);

    @Insert
    long insert(Meal meal);

    @Query("SELECT * FROM Meal WHERE id=:id")
    Meal get(long id);

    @Query("SELECT * FROM Meal WHERE id=:id")
    MealFull getFull(long id);
}
