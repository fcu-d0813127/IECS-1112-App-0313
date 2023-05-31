package com.example.iecs_1112_app_0313.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Store;
import com.example.iecs_1112_app_0313.R;

public class StoreAddActivity extends AppCompatActivity {
  private ActivityResultLauncher<String> imagePickerLauncher;

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
          imageView.setImageURI( result );
        }
      }
    );

    btnUpload.setOnClickListener( v -> {
      imagePickerLauncher.launch( "image/*" );
    });

    btnAddStore.setOnClickListener( v -> {
      Bitmap bitmap = ( ( BitmapDrawable ) imageView.getDrawable() ).getBitmap();

      DatabaseController.db.storeDao().insert(
        new Store(
          etStoreName.getText().toString(),
          bitmap
        )
      );

      finish();
    });
  }
}
