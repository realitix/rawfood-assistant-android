package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Relation;

import com.jasonette.seed.Rawfood.Database.Entity.Aliment;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStep;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepAliment;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepReceipe;

import java.util.List;

@Dao
public interface ReceipeStepAlimentDao {
    class ReceipeStepAlimentFull extends ReceipeStepAliment {
        @Relation(parentColumn = "alimentId", entityColumn = "id")
        Aliment alimentDetail;
    }
    @Insert
    long insert(ReceipeStepAliment receipeStepAliment);
}