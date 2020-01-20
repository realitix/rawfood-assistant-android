package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Relation;

import com.jasonette.seed.Rawfood.Database.Entity.Aliment;
import com.jasonette.seed.Rawfood.Database.Entity.Receipe;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepAliment;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepReceipe;

@Dao
public interface ReceipeStepReceipeDao {
    class ReceipeStepReceipeFull extends ReceipeStepReceipe {
        @Relation(parentColumn = "receipeId", entityColumn = "id")
        Receipe receipeDetail;
    }
    @Insert
    long insert(ReceipeStepReceipe receipeStepReceipe);
}