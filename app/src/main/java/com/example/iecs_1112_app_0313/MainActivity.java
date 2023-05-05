package com.example.iecs_1112_app_0313;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Restaurant[] restaurants = {
      new Restaurant(R.drawable.ic_launcher_background, "7-11"),
      new Restaurant(R.drawable.ic_launcher_background, "Family Mart"),
      new Restaurant(R.drawable.ic_launcher_background, "OK Mart")
    };

    GridView restaurants_grid = findViewById(R.id.restaurants_grid);
    restaurants_grid.setAdapter(new RestaurantGridViewAdapter(this, restaurants));

    SearchView restaurants_searchbar = findViewById(R.id.restaurants_searchbar);
    restaurants_searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String query) {
        return false;
      }
    });
  }
}
