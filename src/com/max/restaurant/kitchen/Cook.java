package com.max.restaurant.kitchen;

import com.max.restaurant.ConsoleHelper;
import com.max.restaurant.statistic.StatisticManager;
import com.max.restaurant.statistic.event.CookedOrderEventDataRow;

import java.util.concurrent.LinkedBlockingQueue;

public class Cook implements Runnable  {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    private boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {

        while (true) {
            while (queue.isEmpty())
            {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if ( !isBusy())
                    if (!queue.isEmpty()) {
                            startCookingOrder(queue.poll());
                    }
        }
    }

    private void startCookingOrder(Order order){

        busy=true;

        ConsoleHelper.writeMessage("Start cooking - " + order);
        CookedOrderEventDataRow cookedOrderEventDataRow=
               new CookedOrderEventDataRow(
                        order.getTablet().toString(),//tablet
                       this.name,
                        order.getTotalCookingTime()*60,
                        order.getDishes());

       StatisticManager.getInstance().register(cookedOrderEventDataRow);

       try {
           Thread.sleep(order.getTotalCookingTime()*10);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       busy=false;
    }

}
