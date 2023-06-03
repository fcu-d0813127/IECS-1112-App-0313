package com.example.iecs_1112_app_0313;

import com.example.iecs_1112_app_0313.DatabaseModels.Product;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
  public static List<MenuItem> ShoppingCart = new ArrayList<>();
  private Product product;
  private int number;

  public MenuItem( Product product, int number ) {
    this.product = product;
    this.number = number;
  }

  public static void addShoppingCart( MenuItem menuItem ) {
    ShoppingCart.add( menuItem );
  }

  public static MenuItem isInShoppingCart( Product product ) {
    for ( MenuItem menuItem : ShoppingCart ) {
      if ( menuItem.product.id == product.id ) {
        return menuItem;
      }
    }
    return null;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct( Product product ) {
    this.product = product;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber( int number ) {
    this.number = number;
  }
}
