package com.max.restaurant.kitchen;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);


    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public static String  allDishesToString(){
      StringBuilder dihses=new StringBuilder();
      for (Dish dish:Dish.values()) {
          dihses.append(dish);
          dihses.append(", ");
      }
      dihses.deleteCharAt(dihses.length()-1);
      dihses.deleteCharAt(dihses.length()- 1);
      return dihses.toString();
  }


}
