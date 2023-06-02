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

public class StoreEditActivity extends AppCompatActivity {
  private ActivityResultLauncher<String> imagePickerLauncher;
  private String image_path;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_store_edit );

    EditText etStoreName = findViewById( R.id.et_edit_store_name );
    ImageView ivStoreImage = findViewById( R.id.iv_edit_store_image );
    Button btnStoreUpdate = findViewById( R.id.btn_edit_store_update );
    Button btnStoreCancel = findViewById( R.id.btn_edit_store_cancel );
    Button btnImageUpload = findViewById( R.id.btn_edit_store_upload );
    Button btnStoreDelete = findViewById( R.id.btn_edit_store_delete );

    // Get store data
    int store_id = getIntent().getIntExtra( "store_id", -1 );
    Store store = DatabaseController.db.storeDao().getById( store_id );

    etStoreName.setText( store.name );
    image_path = store.image_path;
    Bitmap currentBitmap = ImageManagement.loadImage( image_path );
    ivStoreImage.setImageBitmap( currentBitmap );

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
          ivStoreImage.setImageURI( result );
        }
      }
    );

    btnStoreUpdate.setOnClickListener( v -> {
      store.name = etStoreName.getText().toString();
      store.image_path = image_path;
      DatabaseController.updateStore( store );
      finish();
    });

    btnStoreCancel.setOnClickListener( v -> finish() );

    btnImageUpload.setOnClickListener( v -> imagePickerLauncher.launch( "image/*" ) );

    btnStoreDelete.setOnClickListener( v -> {
      DatabaseController.deleteStore( store );
      finish();
    });
  }
}
