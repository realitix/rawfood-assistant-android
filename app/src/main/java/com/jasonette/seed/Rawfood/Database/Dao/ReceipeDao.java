package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Embedded;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Relation;
import androidx.room.Update;

import com.jasonette.seed.Rawfood.Database.Entity.Receipe;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStep;

import java.util.List;

@Dao
public interface ReceipeDao {
    class ReceipeWithSteps extends Receipe {
        @Relation(parentColumn = "id", entityColumn = "receipeId")
        List<ReceipeStep> steps;
    }

    @Query("SELECT * FROM Receipe")
    List<Receipe> getList();

    @Query("SELECT * FROM Receipe WHERE id=:id")
    Receipe get(long id);

    @Query("SELECT * FROM Receipe WHERE Receipe.id=:id")
    ReceipeWithSteps getWithSteps(long id);

    @Insert
    long insert(Receipe receipe);

    @Update
    void update(Receipe receipe);

    @Delete
    void delete(Receipe receipe);
}