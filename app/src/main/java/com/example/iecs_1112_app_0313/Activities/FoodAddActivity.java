package com.example.iecs_1112_app_0313.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Product;
import com.example.iecs_1112_app_0313.R;

public class FoodAddActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_food_add);

    EditText foodName = findViewById( R.id.et_food_add_name );
    EditText foodDescription = findViewById( R.id.et_food_add_description );
    EditText foodPrice = findViewById( R.id.et_food_add_price );

    Button btnAdd = findViewById( R.id.btn_food_add );

    btnAdd.setOnClickListener( v -> {
//      DatabaseController.db.productDao().insert(
//        new Product(
//          foodName.getText().toString(),
//          foodDescription.getText().toString(),
//          Double.parseDouble( foodPrice.getText().toString() )
//        )
//      )
    });
  }
}
