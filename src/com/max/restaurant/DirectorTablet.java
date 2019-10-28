package com.max.restaurant;

import com.max.restaurant.ad.Advertisement;
import com.max.restaurant.ad.StatisticAdvertisementManager;
import com.max.restaurant.statistic.StatisticManager;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {

    public void printAdvertisementProfit(){
        StatisticManager statisticManager = StatisticManager.getInstance();
        double totalDayCash=0.0;

        SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);


        Map<Date,Long> dayProfitMap;
        dayProfitMap=(statisticManager.advertisementProfit());

        long cash;

        for (Map.Entry<Date,Long> entry:dayProfitMap.entrySet()) {
            cash= entry.getValue();


            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH,"%s - %.2f",simpleDate.format(entry.getKey()),cash/100.0));

           // totalDayCash+=(double)entry.getValue()/100;
            totalDayCash+=cash;
        }
        //ConsoleHelper.writeMessage(String.format("Total - %.2f" ,totalDayCash));
        ConsoleHelper.writeMessage(String.format( Locale.ENGLISH,"Total - %.2f",totalDayCash/100.0));//new BigDecimal(totalDayCash).movePointLeft(2));

    }
    public void printCookWorkloading(){
        StatisticManager statisticManager = StatisticManager.getInstance();
       // SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy");

        Map<String,Map<String,Integer>> coosksDateTime;
        coosksDateTime=statisticManager.durationCooksTime();

        Map<String,Integer> cooksTime;



        for (Map.Entry<String,Map<String,Integer>> entryDate:
                coosksDateTime.entrySet()) {
            cooksTime = entryDate.getValue();

            if (!cooksTime.isEmpty()){
                cooksTime.values().remove(null);
                ConsoleHelper.writeMessage(entryDate.getKey());
                cooksTime.forEach((name,time)->
                        ConsoleHelper.writeMessage(name +" - "+ time/60 + " min"));
            }
            ConsoleHelper.writeMessage("");

        }


    }

    public void printActiveVideoSet(){
        StatisticAdvertisementManager manager = StatisticAdvertisementManager.getInstance();
        ArrayList<Advertisement> videos = new ArrayList<>(manager.getActiveVideoSet());
        videos.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        videos.forEach(video->
                ConsoleHelper.writeMessage(video.getName()+" - "+video.getHits()));

    }

    public void printArchivedVideoSet(){
        StatisticAdvertisementManager manager = StatisticAdvertisementManager.getInstance();
        ArrayList<Advertisement> videos = new ArrayList<>(manager.getArchivedVideoSet());
        videos.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        videos.forEach(video->
                ConsoleHelper.writeMessage(video.getName()));

    }
}
