package com.example.iecs_1112_app_0313.DatabaseModels;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
  @Query( "SELECT * FROM product" )
  List<Product> getAll();

  @Query( "SELECT * FROM product WHERE id = :product_id LIMIT 1" )
  List<Product> getById( int product_id );

  @Query( "SELECT * FROM product WHERE name = :product_name LIMIT 1" )
  Product findByName( String product_name );

  @Insert( onConflict = OnConflictStrategy.REPLACE )
  void insert( Product product );

  @Delete
  void delete( Product product );

  @Update( entity = Product.class )
  void edit( Product product );
}
