package com.example.iecs_1112_app_0313.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iecs_1112_app_0313.Activities.MenuActivity;
import com.example.iecs_1112_app_0313.R;
import com.example.iecs_1112_app_0313.Restaurant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import me.xdrop.fuzzywuzzy.FuzzySearch;

class RestraurantSearchResult {
  Restaurant restaurant;
  int similarity;

  public RestraurantSearchResult( Restaurant restaurant, int similarity ) {
    this.restaurant = restaurant;
    this.similarity = similarity;
  }
}

public class RestaurantGridViewAdapter extends BaseAdapter {
  Context context;
  List<Restaurant> restaurants;
  List<Restaurant> restaurants_view;
  LayoutInflater inflater;

  public RestaurantGridViewAdapter( Context context, List<Restaurant> restaurants ) {
    this.context = context;
    this.restaurants = restaurants;
    this.restaurants_view = new ArrayList<>( restaurants );
    this.inflater = LayoutInflater.from( this.context );
  }

  @Override
  public int getCount() {
    return restaurants_view.size();
  }

  @Override
  public Object getItem( int i ) {
    return restaurants_view.get(i);
  }

  @Override
  public long getItemId( int i ) {
    return i;
  }

  @Override
  public View getView( int i, View convert_view, ViewGroup parent ) {
    View view = convert_view;
    if ( view == null ) {
      view = LayoutInflater.from( context ).inflate( R.layout.restaurant_layout, parent, false );
    }

    Restaurant restaurant = restaurants_view.get( i );
    TextView restaurant_name = view.findViewById( R.id.restaurant_name );
    ImageView restaurant_logo = view.findViewById( R.id.restaurant_logo );

    restaurant_logo.setImageResource( restaurant.logo_id );
    restaurant_logo.setScaleType( ImageView.ScaleType.CENTER_CROP );

    restaurant_name.setText( restaurant.name );

    // 為每個物件添加點擊事件並包裝被點擊的餐廳名稱
    view.setOnClickListener( v -> {
      Intent intent = new Intent( context, MenuActivity.class );
      intent.putExtra( "restaurant_name", restaurant.name );
      context.startActivity( intent );
    });

    return view;
  }

  // Perform Fuzzy Search on restaurants against the specified keyword
  public void filter( String keyword ) {
    if ( keyword == null || keyword.isEmpty() ) {
      restaurants_view = new ArrayList<>( restaurants );
    } else {
      ArrayList<RestraurantSearchResult> search_results = new ArrayList<>();
      String keyword_lowercase = keyword.toLowerCase();

      for ( Restaurant restaurant : restaurants ) {
        int similarity = FuzzySearch.weightedRatio( restaurant.name.toLowerCase(), keyword_lowercase );
        if ( similarity == 0 ) continue;
        search_results.add( new RestraurantSearchResult( restaurant, similarity ) );
      }
      search_results.sort( Comparator.comparingInt( restaurant -> ( ( RestraurantSearchResult ) restaurant ).similarity ).reversed() );

      restaurants_view.clear();
      for ( RestraurantSearchResult search_result : search_results ) {
        restaurants_view.add( search_result.restaurant );
      }
    }
    this.notifyDataSetChanged();
  }
}
