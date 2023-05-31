package com.example.iecs_1112_app_0313.DatabaseModels;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.iecs_1112_app_0313.ImageManagement;

import java.nio.ByteBuffer;

@Entity( tableName = "product" )
public class Product {
  @PrimaryKey( autoGenerate = true )
  public int id;

  @ColumnInfo( name = "store_id" )
  public int store_id;

  @ColumnInfo( name = "name" )
  public String name;

  @ColumnInfo( name = "price" )
  public int price;

  @ColumnInfo( name = "description" )
  public String description;

  @ColumnInfo( name = "image_path" )
  public String image_path;

  @Ignore
  public Product( int store_id, String name, int price ) {
    this.store_id = store_id;
    this.name = name;
    this.price = price;
  }

  @Ignore
  public Product( int store_id, String name, int price, String description ) {
    this.store_id = store_id;
    this.name = name;
    this.price = price;
    this.description = description;
  }

  public Product( int store_id, String name, int price, String description, String image_path ) {
    this.store_id = store_id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.image_path = image_path;
  }
}
