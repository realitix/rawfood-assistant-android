package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Embedded;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jasonette.seed.Rawfood.Database.Entity.Receipe;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStep;

@Dao
public interface ReceipeDao {
    static class ReceipeWithSteps {
        @Embedded
        Receipe receipe;

        @Embedded
        ReceipeStep[] steps;
    }

    @Query("SELECT * FROM Receipe")
    Receipe[] getList();

    @Query("SELECT * FROM Receipe WHERE id=:id")
    Receipe get(long id);

    @Query("SELECT * FROM Receipe INNER JOIN ReceipeStep ON Receipe.id = ReceipeStep.receipeId WHERE receipe.id=:id")
    ReceipeWithSteps getWithSteps(long id);

    @Insert
    long insert(Receipe receipe);

    @Update
    void update(Receipe receipe);

    @Delete
    void delete(Receipe receipe);
}