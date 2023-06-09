package com.example.iecs_1112_app_0313;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public interface ImageManagement {
  String image_base_path = Environment.getExternalStorageDirectory().getPath() + "/AnyDishes/user_images/";

  static Bitmap rescaleToWidth( Bitmap image, int new_width ) {
    int new_height = Math.round( ( float ) new_width / 4 * 3 );
    return Bitmap.createScaledBitmap( image, new_width, new_height, false );
  }

  static byte[] bitmap2Bytes( Bitmap image ) {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    image = rescaleToWidth( image, 300 );
    image.compress( Bitmap.CompressFormat.WEBP_LOSSY, 80, stream );
    return stream.toByteArray();
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
    return image_base_path + getMD5( data ) + ".webp";
  }


  static Bitmap loadImage( String image_path ) {
    return BitmapFactory.decodeFile( image_path );
  }

  static String saveImage( Bitmap image ) {
    if ( image == null ) return null;
    String image_path = getImagePath( bitmap2Bytes( image ) );
    
    // Prevent writing to duplicated file
    if ( Files.exists( Paths.get( image_path ) ) ) return image_path;

    try ( FileOutputStream file = new FileOutputStream( image_path ) ) {
      image = rescaleToWidth( image, 300 );
      image.compress( Bitmap.CompressFormat.WEBP_LOSSY, 80, file );
    } catch ( IOException e ) {
      throw new RuntimeException( e );
    }
    return image_path;
  }

  static void deleteImage( String image_path ) {
    if ( image_path == null || image_path.isEmpty() ) return;
    File file = new File( image_path );
    if ( file.exists() ) file.delete();
  }
}
