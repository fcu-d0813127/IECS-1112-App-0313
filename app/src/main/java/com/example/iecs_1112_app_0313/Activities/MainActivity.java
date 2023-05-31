package com.example.iecs_1112_app_0313.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Store;
import com.example.iecs_1112_app_0313.R;
import com.example.iecs_1112_app_0313.Adapters.RestaurantGridViewAdapter;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  GridView restaurants_grid;
  SearchView restaurants_searchbar;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    try {
      DatabaseController.init( this );
    } catch ( IOException e ) {
      throw new RuntimeException( e );
    }

    // Data Source setup
    List<Store> stores = DatabaseController.db.storeDao().getAll();
//    List<Restaurant> restaurants = new ArrayList<>();
//    restaurants.add( new Restaurant( R.drawable.ic_launcher_background, "7-11" ) );
//    restaurants.add( new Restaurant( R.drawable.ic_launcher_background, "Family Mart" ) );
//    restaurants.add( new Restaurant( R.drawable.ic_launcher_background, "OK Mart" ) );
//    for ( int i = 0; i < 10; ++i ) restaurants.add( new Restaurant( R.drawable.ic_launcher_background, String.format( "Test_%d",i ) ) );

    // Grid View setup
    restaurants_grid = findViewById( R.id.restaurants_grid );
    restaurants_grid.setAdapter( new RestaurantGridViewAdapter( this, stores ) );

    // Search View setup
    restaurants_searchbar = findViewById( R.id.restaurants_searchbar );
    restaurants_searchbar.setOnClickListener( v -> restaurants_searchbar.setIconified( false ) );
    restaurants_searchbar.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit( String query ) {
        restaurants_searchbar.clearFocus();
        return false;
      }

      @Override
      public boolean onQueryTextChange( String query ) {
        ( ( RestaurantGridViewAdapter ) restaurants_grid.getAdapter() ).filter( query );
        return true;
      }
    });

    // Search View: Clear the query and reset the search view, instead of asking user for new input
    int search_view_close_button_id = restaurants_searchbar
      .getContext()
      .getResources()
      .getIdentifier( "android:id/search_close_btn", null, null );
    View search_view_close_button = restaurants_searchbar.findViewById( search_view_close_button_id );
    search_view_close_button.setOnClickListener( v -> {
      restaurants_searchbar.setQuery( "", true );
      restaurants_searchbar.clearFocus();
      restaurants_searchbar.setIconified( true );
    });

    // Search View: Clear the focus of the search view when back button is pressed
    KeyboardVisibilityEvent.setEventListener(
      this,
      is_keyboard_open -> {
        if ( is_keyboard_open ) return;
        restaurants_searchbar.clearFocus();
      });
  }
  @Override
  public boolean onCreateOptionsMenu( Menu menu ) {
    getMenuInflater().inflate( R.menu.store_main, menu );
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {
    int id = item.getItemId();

    if ( id == R.id.store_add) {
      Intent intent = new Intent( MainActivity.this, StoreAddActivity.class );
      startActivity( intent );
    } else if ( id == R.id.store_edit ) {
      Intent intent = new Intent( MainActivity.this, FoodEditActivity.class );
      startActivity( intent );
    }

    return true;
  }

  @Override
  protected void onRestart() {
    super.onRestart();

    // Refresh the list
    List<Store> stores = DatabaseController.db.storeDao().getAll();
    restaurants_grid.setAdapter( new RestaurantGridViewAdapter( this, stores ) );
  }
}
