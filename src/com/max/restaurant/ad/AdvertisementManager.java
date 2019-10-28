package com.max.restaurant.ad;

import com.max.restaurant.ConsoleHelper;
import com.max.restaurant.statistic.StatisticManager;
import com.max.restaurant.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private  List<Advertisement> videos=new ArrayList<>();
    private List<Advertisement> bestCombination =new ArrayList<>();

   private List<List<Advertisement>> allCombinations = new ArrayList<>();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

  private Long calcPrice(List<Advertisement> list){

       return list.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
    }


    private int calcDuration(List<Advertisement> list){
       return list.stream().mapToInt(Advertisement::getDuration).sum();
   }

    private void checkList(List<Advertisement> list){

         long bestPrice = 0L;
        int calcDurationInt= list.stream().mapToInt(Advertisement::getDuration).sum();
        long calcPriceLong = list.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
        long totalPrice=0;

       // long totalPrice = list.stream().mapToLong(o->o.getAmountPerOneDisplaying()).sum();
        if ( calcDurationInt<=timeSeconds) {

            if(bestCombination.isEmpty()){
            allCombinations.add(list);

            }else if(calcPriceLong>bestPrice){
                allCombinations.add(list);

            }

        }

    }

    private void makeAllLists(List<Advertisement> list){

        if(!list.isEmpty())
           checkList(list);

        for (int i = 0; i <list.size() ; i++) {
           List<Advertisement> newList = new ArrayList<>(list);
//
            newList.remove(i);
            makeAllLists(newList);

        }

    }

    public  void processVideos() {

        long filterMaxPrice;
        int filterMaxDuration;


        if(storage.list().isEmpty())
            throw new NoVideoAvailableException();


        videos=storage.list();

       videos= videos.stream()
                .filter(advertisement->advertisement.getDuration()<=timeSeconds)
                .filter(advertisement->advertisement.getHits()>0)
                .filter(advertisement ->advertisement.getAmountPerOneDisplaying()>0)
               .collect(Collectors.toList());

       makeAllLists(videos);
//
//
      Comparator<List<Advertisement>> compareByPrice = Comparator.comparingLong(this::calcPrice);
      Comparator<List<Advertisement>> compareByDuration = Comparator.comparingLong(this::calcDuration);
      Comparator<List<Advertisement>> compareBySize = Comparator.comparingLong(List::size);

     if (allCombinations.size()>1) {
         allCombinations.sort(compareByPrice.reversed());
         filterMaxPrice = calcPrice(allCombinations.get(0));

         allCombinations = allCombinations.stream()
                 .filter(advertisements -> calcPrice(advertisements)==filterMaxPrice)
                 .collect(Collectors.toList());
     }

     if (allCombinations.size()>1) {
         allCombinations.sort(compareByDuration.reversed());
         filterMaxDuration=calcDuration(allCombinations.get(0));
         allCombinations = allCombinations.stream()
                 .filter(advertisements -> calcDuration(advertisements) == filterMaxDuration)
                 .collect(Collectors.toList());
     }

        if (allCombinations.size()>1)
            allCombinations.sort(compareBySize);

        bestCombination = allCombinations.get(0);

        bestCombination = bestCombination.stream().sorted(
             Comparator.comparingLong(Advertisement::getAmountPerOneDisplaying).reversed()
             .thenComparingLong(a->a.getAmountPerOneDisplaying()/a.getDuration()))
             .collect(Collectors.toList());


        VideoSelectedEventDataRow videoSelected =
                new VideoSelectedEventDataRow(bestCombination, calcPrice(bestCombination),calcDuration(bestCombination));

        StatisticManager.getInstance().register(videoSelected);

        for (Advertisement a:bestCombination) {

                String message = String.format("%s is displaying... %d, %d",
                        a.getName(),
                        a.getAmountPerOneDisplaying(),
                        a.getAmountPerOneDisplaying()*1000/a.getDuration());

                ConsoleHelper.writeMessage(message);
                a.revalidate();
        }



    }
}
