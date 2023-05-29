package com.example.iecs_1112_app_0313.DatabaseModels;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.nio.ByteBuffer;

@Entity( tableName = "store" )
public class Store {
  @PrimaryKey( autoGenerate = true )
  public int id;

  @ColumnInfo( name = "name" )
  public String name;

  @ColumnInfo( name = "image", typeAffinity = ColumnInfo.BLOB )
  public byte[] image;

  @Ignore
  public Store( String name ) {
    this.name = name;
  }

  @Ignore
  public Store( String name , Bitmap bitmap ) {
    int size = bitmap.getRowBytes() * bitmap.getHeight();
    ByteBuffer buffer = ByteBuffer.allocate( size );
    bitmap.copyPixelsToBuffer( buffer );

    this.name = name;
    this.image = buffer.array();
  }

  public Store( String name , byte[] image ) {
    this.name = name;
    this.image = image;
  }
}
