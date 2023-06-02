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
import com.example.iecs_1112_app_0313.DatabaseModels.Product;
import com.example.iecs_1112_app_0313.ImageManagement;
import com.example.iecs_1112_app_0313.R;

import java.io.FileNotFoundException;

public class FoodDetailActivity extends AppCompatActivity {
  private ActivityResultLauncher<String> imagePickerLauncher;
  private String image_path;
  private Product product;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_food_detail );

    int foodId = getIntent().getIntExtra( "product_id", -1 );
    product = DatabaseController.db.productDao().getById( foodId );

    Button btnConfirm = findViewById( R.id.btn_food_detail_edit );
    Button btnCancel = findViewById( R.id.btn_food_detail_cancel );
    Button btnDelete = findViewById( R.id.btn_food_detail_delete );
    Button btnImageUpload = findViewById( R.id.btn_food_detail_upload );
    ImageView ivFoodImage = findViewById( R.id.iv_food_detail_image );

    Bitmap currentBitmap = ImageManagement.loadImage( product.image_path );
    ivFoodImage.setImageBitmap( currentBitmap );

    EditText tvFoodName = findViewById( R.id.et_food_detail_name );
    tvFoodName.setText( product.name );

    EditText tvFoodDescription = findViewById( R.id.et_food_detail_description );
    tvFoodDescription.setText( product.description );

    EditText tvFoodPrice = findViewById( R.id.et_food_detail_price );
    tvFoodPrice.setText( String.valueOf( product.price ) );

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
          ivFoodImage.setImageURI( result );
        }
      }
    );

    btnConfirm.setOnClickListener( v -> {
      product.name = tvFoodName.getText().toString();
      product.description = tvFoodDescription.getText().toString();
      product.price = Integer.parseInt( tvFoodPrice.getText().toString() );
      product.image_path = image_path;
      DatabaseController.updateProduct( product );
      finish();
    });

    btnCancel.setOnClickListener( v -> finish());

    btnDelete.setOnClickListener( v -> {
      DatabaseController.deleteProduct( product );
      finish();
    });

    btnImageUpload.setOnClickListener( v -> imagePickerLauncher.launch( "image/*" ) );
  }
}
