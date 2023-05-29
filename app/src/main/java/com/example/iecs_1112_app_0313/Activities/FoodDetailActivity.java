package com.example.iecs_1112_app_0313.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iecs_1112_app_0313.R;

public class FoodDetailActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_food_detail );

    Intent intent = getIntent();
    String foodName = intent.getStringExtra( "foodName" );
    String foodDescription = intent.getStringExtra( "foodDescription" );
    String foodPrice = intent.getStringExtra( "foodPrice" );

    Button btnConfirm = findViewById( R.id.btn_food_detail_edit );
    Button btnCancel = findViewById( R.id.btn_food_detail_cancel );
    Button btnDelete = findViewById( R.id.btn_food_detail_delete );

    EditText tvFoodName = findViewById( R.id.et_food_detail_name );
    tvFoodName.setText( foodName );

    EditText tvFoodDescription = findViewById( R.id.et_food_detail_description );
    tvFoodDescription.setText( foodDescription );

    EditText tvFoodPrice = findViewById( R.id.et_food_detail_price );
    tvFoodPrice.setText( foodPrice );

    btnConfirm.setOnClickListener( v -> {
      finish();
    });

    btnCancel.setOnClickListener( v -> {
      finish();
    });

    btnDelete.setOnClickListener( v -> {
      finish();
    });
  }
}
