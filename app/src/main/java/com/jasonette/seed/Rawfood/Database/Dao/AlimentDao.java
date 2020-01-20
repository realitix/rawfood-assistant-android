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
    @Query("SELECT * FROM Aliment")
    List<Aliment> getList();

    @Query("SELECT * FROM Aliment WHERE nameSearch LIKE '%' ||  :search || '%'")
    List<Aliment> search(String search);

    @Query("SELECT * FROM Aliment WHERE name LIKE :name")
    Aliment getByName(String name);

    @Insert
    long insert(Aliment aliment);
}
