package com.example.iecs_1112_app_0313.DatabaseModels;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.iecs_1112_app_0313.ImageManagement;

import java.nio.ByteBuffer;

@Entity( tableName = "store" )
public class Store {
  @PrimaryKey( autoGenerate = true )
  public int id;

  @ColumnInfo( name = "name" )
  public String name;

  @ColumnInfo( name = "image_path" )
  public String image_path;

  @Ignore
  public Store( String name ) {
    this.name = name;
  }

  public Store( String name , String image_path ) {
    this.name = name;
    this.image_path = image_path;
  }
}
