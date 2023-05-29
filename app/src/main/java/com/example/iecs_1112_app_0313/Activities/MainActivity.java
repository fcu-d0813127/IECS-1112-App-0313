package com.example.iecs_1112_app_0313.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Store;
import com.example.iecs_1112_app_0313.DatabaseRelations;
import com.example.iecs_1112_app_0313.R;
import com.example.iecs_1112_app_0313.Restaurant;
import com.example.iecs_1112_app_0313.Adapters.RestaurantGridViewAdapter;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import java.io.IOException;
import java.util.ArrayList;
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
    List<Restaurant> restaurants = new ArrayList<>();
    restaurants.add( new Restaurant( R.drawable.ic_launcher_background, "7-11" ) );
    restaurants.add( new Restaurant( R.drawable.ic_launcher_background, "Family Mart" ) );
    restaurants.add( new Restaurant( R.drawable.ic_launcher_background, "OK Mart" ) );
    for ( int i = 0; i < 10; ++i ) restaurants.add( new Restaurant( R.drawable.ic_launcher_background, String.format( "Test_%d",i ) ) );

    // Grid View setup
    restaurants_grid = findViewById( R.id.restaurants_grid );
    restaurants_grid.setAdapter( new RestaurantGridViewAdapter( this, restaurants ) );

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
}
