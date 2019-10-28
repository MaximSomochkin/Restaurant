package com.max.restaurant.kitchen;

import com.max.restaurant.ConsoleHelper;
import com.max.restaurant.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Tablet tablet;
    List<Dish> dishes=new ArrayList<>();


    public Order(Tablet tablet) throws Exception {
        this.tablet = tablet;
       // dishes= ConsoleHelper.getAllDishesForOrder();
        initDishes();


    }

    List<Dish> getDishes() {
        return dishes;
    }

    Tablet getTablet() {
        return tablet;
    }

    protected void initDishes() throws Exception {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }
    public int getTotalCookingTime(){
        int time=0;
        for (Dish dish:dishes ) {
            time+=dish.getDuration();
        }
        return time;
    }



    @Override
    public String toString()  {
        return dishes.isEmpty()?null:String.format("Your order: %s %s, cooking time %dmin",dishes,tablet, getTotalCookingTime());
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }
}
