package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Relation;

import com.jasonette.seed.Rawfood.Database.Entity.Receipe;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStep;

import java.util.List;

@Dao
public interface ReceipeDao {
    class ReceipeFull extends Receipe {
        @Relation(parentColumn = "id", entityColumn = "receipeId")
        List<ReceipeStep> steps;
    }

    @Query("SELECT * FROM Receipe")
    List<Receipe> getList();

    @Query("SELECT * FROM Receipe WHERE id=:id")
    Receipe get(long id);

    @Query("SELECT * FROM Receipe WHERE Receipe.id=:id")
    ReceipeFull getFull(long id);

    @Insert
    long insert(Receipe receipe);
}