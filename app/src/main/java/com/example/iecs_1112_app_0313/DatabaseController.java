package com.example.iecs_1112_app_0313;


import android.content.Context;
import android.os.Environment;

import androidx.room.Room;

import com.example.iecs_1112_app_0313.DatabaseModels.Product;
import com.example.iecs_1112_app_0313.DatabaseModels.Store;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseController {
  public static DatabaseRelations db;

  public static void init( Context appContext ) throws IOException {
    String path = Environment.getExternalStorageDirectory().getPath() + "/AnyDishes/AnyDishes.db";

    // Create an empty file if it doesn't exist
    File file = new File( path );
    Files.createDirectories( Paths.get( file.getParent() ) );
    file.createNewFile();

    // Open and load SQLite database
    db = Room.databaseBuilder(
      appContext,
      DatabaseRelations.class,
      path
    ).allowMainThreadQueries().build();
  }

  public static void deleteStore( Store store ) {
    Store old_store = db.storeDao().getById( store.id );
    int image_reference_count = db.storeDao().findByImagePath( old_store.image_path ).size();
    if ( image_reference_count <= 1 ) {
      // Delete the image file on disk if nothing is referencing it
      ImageManagement.deleteImage( old_store.image_path );
    }
    db.storeDao().delete( old_store );
  }

  public static void deleteProduct( Product product ) {
    Product old_product = db.productDao().getById( product.id );
    int image_reference_count = db.productDao().findByImagePath( old_product.image_path ).size();
    if ( image_reference_count <= 1 ) {
      // Delete the image file on disk if nothing is referencing it
      ImageManagement.deleteImage( old_product.image_path );
    }
    db.productDao().delete( old_product );
  }

  public static void updateStore( Store store ) {
    Store old_store = db.storeDao().getById( store.id );
    int image_reference_count = db.storeDao().findByImagePath( old_store.image_path ).size();
    if ( !store.image_path.equals( old_store.image_path ) && image_reference_count <= 1 ) {
      // Delete the image file on disk if nothing is referencing it
      ImageManagement.deleteImage( old_store.image_path );
    }
    db.storeDao().update( store );
  }

  public static void updateProduct( Product product ) {
    Product old_product = db.productDao().getById( product.id );
    int image_reference_count = db.productDao().findByImagePath( old_product.image_path ).size();
    if ( !product.image_path.equals( old_product.image_path ) && image_reference_count <= 1 ) {
      // Delete the image file on disk if nothing is referencing it
      ImageManagement.deleteImage( old_product.image_path );
    }
    db.productDao().update( product );
  }
}
