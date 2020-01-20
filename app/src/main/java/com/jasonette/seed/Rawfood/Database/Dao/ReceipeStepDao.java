package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Relation;
import androidx.room.Update;

import com.jasonette.seed.Rawfood.Database.Entity.Receipe;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStep;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepAliment;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepReceipe;

import java.util.List;

@Dao
public interface ReceipeStepDao {
    class ReceipeStepFull extends ReceipeStep {
        @Relation(parentColumn = "id", entityColumn = "stepId", entity = ReceipeStepAliment.class)
        List<ReceipeStepAlimentDao.ReceipeStepAlimentFull> aliments;
        @Relation(parentColumn = "id", entityColumn = "stepId", entity = ReceipeStepReceipe.class)
        List<ReceipeStepReceipeDao.ReceipeStepReceipeFull> receipes;
    }

    @Query("SELECT * FROM ReceipeStep WHERE id=:id")
    ReceipeStep get(long id);

    @Query("SELECT * FROM ReceipeStep WHERE id=:id")
    ReceipeStepFull getFull(long id);

    @Insert
    long insert(ReceipeStep receipeStep);
}