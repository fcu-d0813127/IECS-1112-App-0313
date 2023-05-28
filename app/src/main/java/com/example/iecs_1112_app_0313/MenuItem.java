package com.example.iecs_1112_app_0313;

public class MenuItem {
  private int imageId;
  private String foodName;
  private int foodPrice;
  private int number;

  public MenuItem( int imageId, String foodName, int foodPrice ) {
    this.imageId = imageId;
    this.foodName = foodName;
    this.foodPrice = foodPrice;
  }

  public MenuItem( int imageId, String foodName, int foodPrice, int number ) {
    this.imageId = imageId;
    this.foodName = foodName;
    this.foodPrice = foodPrice;
    this.number = number;
  }

  public int getImageId() {
    return imageId;
  }

  public void setImageId( int imageId ) {
    this.imageId = imageId;
  }

  public String getFoodName() {
    return foodName;
  }

  public void setFoodName( String foodName ) {
    this.foodName = foodName;
  }

  public int getFoodPrice() {
    return foodPrice;
  }

  public void setFoodPrice( int foodPrice ) {
    this.foodPrice = foodPrice;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }
}
