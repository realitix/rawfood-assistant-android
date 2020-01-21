package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Relation;

import com.jasonette.seed.Rawfood.Database.Entity.Aliment;
import com.jasonette.seed.Rawfood.Database.Entity.MealAliment;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepAliment;

@Dao
public interface MealAlimentDao {
    class MealAlimentFull extends MealAliment {
        @Relation(parentColumn = "alimentId", entityColumn = "id")
        Aliment alimentDetail;
    }

    @Insert
    long insert(MealAliment mealAliment);
}