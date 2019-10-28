package com.max.restaurant.statistic;


import com.max.restaurant.kitchen.Cook;
import com.max.restaurant.statistic.event.CookedOrderEventDataRow;
import com.max.restaurant.statistic.event.EventDataRow;
import com.max.restaurant.statistic.event.EventType;
import com.max.restaurant.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

import java.util.stream.Collectors;

public class StatisticManager {
    private static StatisticManager ourInstance ;
    private StatisticStorage statisticStorage;

    public static synchronized StatisticManager getInstance() {

        if (ourInstance==null)
             ourInstance=new StatisticManager();

        return ourInstance;
    }

    private StatisticManager() {
        statisticStorage = new StatisticStorage();
    }



    private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage=new HashMap<>();

        Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        private void put(EventDataRow data){
           storage.get(data.getType()).add(data);
       }

        private StatisticStorage() {

            for (EventType eventType:EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }
    }

    private  Date dateRemoveTime(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public Map<Date, Long> advertisementProfit(){
       List<VideoSelectedEventDataRow> advertisements = new ArrayList<>();
      Map<Date,Long> dayProfit;
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);

        this.statisticStorage.getStorage()
                .get(EventType.SELECTED_VIDEOS)
                .forEach(c->advertisements.add((VideoSelectedEventDataRow)c));

       dayProfit= advertisements.stream()
               .collect(Collectors.groupingBy(ad->dateRemoveTime(ad.getDate()),
                       Collectors.summingLong(VideoSelectedEventDataRow::getAmount)));

       return  dayProfit;

    }



    public Map<String, Map<String, Integer>> durationCooksTime(){
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
       List<CookedOrderEventDataRow> cooks = new ArrayList<>();

        this.statisticStorage.getStorage().get(EventType.COOKED_ORDER).forEach(c->cooks.add((CookedOrderEventDataRow)c));

        return cooks.stream()
                .collect(Collectors.groupingBy(d->simpleDate.format(d.getDate()),
                        Collectors.groupingBy(CookedOrderEventDataRow::getCookName,
                                Collectors.summingInt(CookedOrderEventDataRow::getTime))));

    }


    public void register(EventDataRow data){
       statisticStorage.put(data);
    }




}
