package com.example.iecs_1112_app_0313.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Store;
import com.example.iecs_1112_app_0313.ImageManagement;
import com.example.iecs_1112_app_0313.R;

import java.io.FileNotFoundException;

public class StoreAddActivity extends AppCompatActivity {
  private ActivityResultLauncher<String> imagePickerLauncher;
  String image_path;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_store_add );

    EditText etStoreName = findViewById( R.id.et_store_name );

    ImageView imageView = findViewById( R.id.iv_store_preview );

    Button btnUpload = findViewById( R.id.btn_store_upload_image );
    Button btnAddStore = findViewById( R.id.btn_add_store );

    imagePickerLauncher = registerForActivityResult(
      new ActivityResultContracts.GetContent(),
      result -> {
        if ( result != null ) {
          // Change uri to bitmap
          Bitmap bitmap;
          try {
            bitmap = BitmapFactory.decodeStream(
              getContentResolver().openInputStream( result )
            );
          } catch ( FileNotFoundException e ) {
            throw new RuntimeException( e );
          }

          image_path = ImageManagement.saveImage( bitmap );
          imageView.setImageURI( result );
        }
      }
    );

    btnUpload.setOnClickListener( v -> imagePickerLauncher.launch( "image/*" ));

    btnAddStore.setOnClickListener( v -> {
      DatabaseController.db.storeDao().insert(
        new Store(
          etStoreName.getText().toString(),
          image_path
        )
      );

      finish();
    });
  }
}
