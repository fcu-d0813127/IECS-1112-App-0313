package com.example.iecs_1112_app_0313.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.iecs_1112_app_0313.Adapters.MenuListViewAdapter;
import com.example.iecs_1112_app_0313.MenuItem;
import com.example.iecs_1112_app_0313.R;

import java.util.ArrayList;
import java.util.List;

public class FoodEditActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_food_edit );

    List<MenuItem> menuItems = new ArrayList<>();
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Home", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Home", 200 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Home", 300 ) );

    ListView menuListView = findViewById( R.id.lv_food_edit_list );
    menuListView.setAdapter( new MenuListViewAdapter( this, menuItems ) );
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Toast.makeText( this, "onRestart", Toast.LENGTH_SHORT ).show();
  }
}
