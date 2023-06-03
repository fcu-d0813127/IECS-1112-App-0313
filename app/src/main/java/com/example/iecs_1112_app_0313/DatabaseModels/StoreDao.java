package com.example.iecs_1112_app_0313.DatabaseModels;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StoreDao {
  @Query( "SELECT * FROM store" )
  List<Store> getAll();

  @Query( "SELECT * FROM store WHERE id = :store_id LIMIT 1" )
  Store getById( int store_id );

  @Query( "SELECT * FROM store WHERE image_path = :image_path" )
  List<Store> findByImagePath( String image_path );

  @Query( "SELECT * FROM store WHERE name = :store_name" )
  List<Store> findByName( String store_name );

  @Insert( onConflict = OnConflictStrategy.REPLACE )
  void insert( Store store );

  @Delete
  void delete( Store store );

  @Update( entity = Store.class )
  void update( Store store );
}
