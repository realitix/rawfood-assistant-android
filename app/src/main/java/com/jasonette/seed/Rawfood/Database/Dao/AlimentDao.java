package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jasonette.seed.Rawfood.Database.Entity.Aliment;

import java.util.List;

@Dao
public interface AlimentDao {
    @Query("Select * from aliment")
    Aliment[] getList();

    @Query("SELECT * FROM aliment WHERE name=:name")
    Aliment getByName(String name);

    @Insert
    long insert(Aliment aliment);

    @Update
    void update(Aliment aliment);

    @Delete
    void delete(Aliment aliment);
}
