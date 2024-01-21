package com.example.mylist.data;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.SortedList;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mylist.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getAllLiveData();

    @Query("SELECT * FROM Product WHERE uid IN (:userIds)")
    List<Product> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Product WHERE uid = :uid LIMIT 1")
    Product findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);
}
