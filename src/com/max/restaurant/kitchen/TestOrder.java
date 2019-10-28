package com.max.restaurant.kitchen;

import com.max.restaurant.ConsoleHelper;
import com.max.restaurant.Tablet;

import java.util.ArrayList;
import java.util.Random;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws Exception {
        super(tablet);
        initDishes();
    }
    protected void initDishes() throws Exception{
      //  dishes= ConsoleHelper.getAllDishesForOrder();
        this.dishes=new ArrayList<>();
        int random = (int)(Math.random()* Dish.values().length);
       // int random = new Random().nextInt(  1)+1;

//        for (int i = 0; i < random; i++) {
//          // int randomType = (int)(Math.random()* Dish.values().length);
//        for (int j = 0; j < Dish.values().length; j++) {
//            Dish dish=Dish.values()[j];
//            dishes.add(dish);
//
//            }
//
//        }
        for (Dish dish:Dish.values()){
            if (((int) (Math.random() * 2))==1)
                dishes.add(dish);
        }




    }
}
