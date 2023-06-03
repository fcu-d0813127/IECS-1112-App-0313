package com.example.iecs_1112_app_0313.DatabaseModels;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDao {
  @Query( "SELECT * FROM orders WhERE order_id = :order_id" )
  List<Order> getAll( int order_id );

  @Query( "SELECT MAX(order_id) FROM orders" )
  int getMaxOrderId();

  @Query( "SELECT * FROM orders WHERE order_id = :order_id LIMIT 1" )
  List<Order> getById( int order_id );

  @Insert( onConflict = OnConflictStrategy.REPLACE )
  void insert( Order order );

  @Delete
  void delete( Order order );

  @Update( entity = Order.class )
  void update( Order order );
}
