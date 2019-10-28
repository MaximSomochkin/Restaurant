package com.max.restaurant;

import com.max.restaurant.kitchen.Cook;
import com.max.restaurant.kitchen.Order;
import com.max.restaurant.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();



    public static void main(String[] args) throws Exception {
        //Tablet tablet = new Tablet(1);
        Cook cook = new Cook("Vasya");
        cook.setQueue(orderQueue);
        Cook cook2 = new Cook("Petya");
        cook2.setQueue(orderQueue);

        StatisticManager manager = StatisticManager.getInstance();

        new Thread(cook).start();
        new Thread(cook2).start();

        List<Tablet> tablets = new ArrayList<>();

        for (int i = 0; i <5 ; i++)  tablets.add(new Tablet(i+1));
        tablets.forEach(tablet -> tablet.setQueue(orderQueue));


        Thread taskThread = new Thread(new RandomOrderGeneratorTask
                (tablets,ORDER_CREATING_INTERVAL));
        taskThread.start();
        Thread.sleep(1000);

//        Tablet tablet1 = new Tablet(2);
//       Cook cook1 = new Cook("Petya");
//       tablet1.addObserver(cook1);
//       tablet1.createOrder();
//       cook1.addObserver(new Waiter());
//        tablet1.createOrder();
//        cook1.addObserver(new Waiter());



//        DirectorTablet dTablet = new DirectorTablet();
//        dTablet.printActiveVideoSet();
//        dTablet.printArchivedVideoSet();
     //   dTablet.printAdvertisementProfit();
      //  dTablet.printCookWorkloading();



//        tablet.createOrder();
//        tablet.createOrder();
//        tablet.createOrder();




    }
}
