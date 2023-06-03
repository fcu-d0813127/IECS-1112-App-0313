package com.example.iecs_1112_app_0313.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Product;
import com.example.iecs_1112_app_0313.ImageManagement;
import com.example.iecs_1112_app_0313.R;

import java.io.FileNotFoundException;

public class FoodAddActivity extends AppCompatActivity {
  private ActivityResultLauncher<String> imagePickerLauncher;
  private Bitmap new_bitmap;
  private int store_id;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_food_add);

    store_id = getIntent().getIntExtra( "store_id", -1 );

    EditText foodName = findViewById( R.id.et_food_add_name );
    EditText foodDescription = findViewById( R.id.et_food_add_description );
    EditText foodPrice = findViewById( R.id.et_food_add_price );
    ImageView foodImage = findViewById( R.id.iv_food_add_image );
    Button btnAdd = findViewById( R.id.btn_food_add );
    Button btnAddUpload = findViewById( R.id.btn_food_add_upload );

    imagePickerLauncher = registerForActivityResult(
      new ActivityResultContracts.GetContent(),
      result -> {
        if ( result != null ) {
          // Change uri to bitmap
          try {
            new_bitmap = BitmapFactory.decodeStream(
              getContentResolver().openInputStream( result )
            );
          } catch ( FileNotFoundException e ) {
            throw new RuntimeException( e );
          }

          foodImage.setImageURI( result );
        }
      }
    );

    btnAdd.setOnClickListener( v -> {
      DatabaseController.db.productDao().insert(
        new Product(
          store_id,
          foodName.getText().toString(),
          Integer.parseInt( foodPrice.getText().toString() ),
          foodDescription.getText().toString(),
          ImageManagement.saveImage( new_bitmap )
        )
      );

      finish();
    });

    btnAddUpload.setOnClickListener( v -> imagePickerLauncher.launch( "image/*" ));
  }
}
