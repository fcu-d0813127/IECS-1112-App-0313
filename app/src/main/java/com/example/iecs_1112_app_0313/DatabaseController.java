package com.example.iecs_1112_app_0313;


import android.content.Context;
import android.os.Environment;

import androidx.room.Room;

import java.io.File;
import java.io.IOException;

public class DatabaseController {
  public static DatabaseRelations db;

  public static void init( Context appContext ) throws IOException {
    String path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS )
      .getPath() + "/AnyDish.db";

    // Create an empty file if it doesn't exist
    File file = new File( path );
    file.createNewFile();

    // Open and load SQLite database
    db = Room.databaseBuilder(
      appContext,
      DatabaseRelations.class,
      path
    ).allowMainThreadQueries().build();
  }
}
