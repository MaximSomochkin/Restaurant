package com.max.restaurant;

import com.max.restaurant.kitchen.Dish;
import com.max.restaurant.kitchen.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }

    private static String readString() throws Exception{
       return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws Exception {
        writeMessage(Dish.allDishesToString());
        writeMessage("Введите блюдо...");
        List<Dish> dishes = new ArrayList<>();
        String dish = readString();
        //Arrays.stream(Dish.values()).findAny().toString().equals(dish);


        while (!dish.equals("exit")){
try {

        dishes.add(Dish.valueOf(dish));
}catch (IllegalArgumentException e) {
    writeMessage("такого блюда нет");
}
dish=readString();

        }
        return dishes;
    }
}
