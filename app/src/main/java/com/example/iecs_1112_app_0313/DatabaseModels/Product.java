package com.example.iecs_1112_app_0313.DatabaseModels;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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

  @ColumnInfo( name = "image", typeAffinity = ColumnInfo.BLOB )
  public byte[] image;

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

  @Ignore
  public Product( int store_id, String name, int price, String description, Bitmap bitmap ) {
    int size = bitmap.getRowBytes() * bitmap.getHeight();
    ByteBuffer buffer = ByteBuffer.allocate( size );
    bitmap.copyPixelsToBuffer( buffer );

    this.store_id = store_id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.image = buffer.array();
  }

  public Product( int store_id, String name, int price, String description, byte[] image ) {
    this.store_id = store_id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.image = image;
  }
}
