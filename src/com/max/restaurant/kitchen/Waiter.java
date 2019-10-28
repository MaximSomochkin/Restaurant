package com.max.restaurant.kitchen;

import com.max.restaurant.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Waiter  implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage(arg+ " was cooked by " + o);

    }
}
