package com.example.iecs_1112_app_0313;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ImageManagement {
  String image_base_path = Environment.getExternalStorageDirectory().getPath() + "/AnyDishes/user_images/";

  static byte[] bitmap2Bytes( Bitmap image ) {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    image.compress( Bitmap.CompressFormat.PNG, 100, stream );
    byte[] image_bytes = stream.toByteArray();
    image.recycle();
    return image_bytes;
  }

  static String getMD5( byte[] data ) {
    try {
      java.security.MessageDigest message_digest = java.security.MessageDigest.getInstance( "MD5" );
      byte[] digest_bytes = message_digest.digest( data );
      StringBuilder string_builder = new StringBuilder();
      for ( byte _byte : digest_bytes ) {
        string_builder.append( Integer.toHexString( ( _byte & 0xFF ) | 0x100 ).substring( 1, 3 ) );
      }
      return string_builder.toString();
    } catch ( NoSuchAlgorithmException e ) {
      throw new RuntimeException( e );
    }
  }

  static String getImagePath( byte[] data ) {
    return image_base_path + getMD5( data ) + ".png";
  }

  static Bitmap loadImage( String image_path ) {
    return BitmapFactory.decodeFile( image_path );
  }

  static String saveImage( Bitmap image ) {
    String image_path = getImagePath( bitmap2Bytes( image ) );
    try ( FileOutputStream file = new FileOutputStream( image_path ) ) {
      image.compress( Bitmap.CompressFormat.PNG, 100, file );
    } catch ( IOException e ) {
      throw new RuntimeException( e );
    }
    return image_path;
  }

  static void deleteImage( String image_path ) {
    File file = new File( image_path );
    if ( file.exists() ) file.delete();
  }
}
