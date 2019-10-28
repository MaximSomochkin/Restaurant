package com.max.restaurant.ad;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    private AdvertisementStorage storage= AdvertisementStorage.getInstance();

    public List<Advertisement> getActiveVideoSet(){
       return  storage.list().stream()
                .filter(advertisement -> advertisement.getHits()>0)
                .collect(Collectors.toList());
    }
    public List<Advertisement> getArchivedVideoSet(){
       return  storage.list().stream()
                .filter(advertisement -> advertisement.getHits()<=0)
                .collect(Collectors.toList());
    }
}
