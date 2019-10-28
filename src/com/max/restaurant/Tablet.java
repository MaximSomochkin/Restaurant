package com.max.restaurant;

import com.max.restaurant.ad.AdvertisementManager;
import com.max.restaurant.ad.NoVideoAvailableException;
import com.max.restaurant.kitchen.Order;
import com.max.restaurant.kitchen.TestOrder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet{
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    private  LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }


    Tablet(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        try {
            return "Tablet{number="+
                    this.number+"}";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public Order createOrder() throws Exception {
        return getOrder(new Order(this));
//
    }

    void createTestOrder()throws Exception{
        Order order = new TestOrder(this);
         getOrder(order);
         getOrder(order);



    }

    private Order getOrder(Order order) throws Exception {
        try {
            ConsoleHelper.writeMessage(order.toString());
//        } catch (IOException e) {
//            logger.log(Level.SEVERE, "Console is unavailable.");
//            // return null;
//
       }
        finally {

            if (!order.isEmpty()) {
                queue.put(order);
                AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);

                try {
                    advertisementManager.processVideos();

                } catch (NoVideoAvailableException e) {
                    logger.log(Level.INFO, "No video is available for the order " + order);
                }
            }
        }
        return order;
    }
}
