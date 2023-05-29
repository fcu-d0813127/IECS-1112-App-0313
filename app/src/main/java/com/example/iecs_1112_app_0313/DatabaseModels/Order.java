package com.example.iecs_1112_app_0313.DatabaseModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity( tableName = "orders" )
public class Order {
  @PrimaryKey( autoGenerate = true )
  public int id;

  @ColumnInfo( name = "food_id" )
  public int food_id;

  @ColumnInfo( name = "quantity" )
  public int quantity;

  @ColumnInfo( name = "pick_up_time" )
  public int pick_up_time;

  public Order( int food_id, int quantity, int pick_up_time ) {
    this.food_id = food_id;
    this.quantity = quantity;
    this.pick_up_time = pick_up_time;
  }
}
