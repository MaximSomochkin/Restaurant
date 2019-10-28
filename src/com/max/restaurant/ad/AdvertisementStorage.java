package com.max.restaurant.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage instance;
   private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "Первое видео", 5000, 1, 3 * 60)); // 3 min);
       add(new Advertisement(someContent, "Второе видео", 1000, 2, 15 * 60)); //15 min); // 3 min);
        add(new Advertisement(someContent, "Third Video", 10000, 3, 10 * 60) );//10 min)); //15 min); // 3 min);
       // add(new Advertisement(someContent, "Fourth Video", 500, 4, 4 * 60) );//10 min)); //15 min); // 3 min);
        add(new Advertisement(someContent, "Четвертое видео", 1500, 2, 30 * 60));
        add(new Advertisement(someContent, "Fifth Video", 4000, 10, 2 * 60));
        add(new Advertisement(someContent, "Seventh Video", 2500, 1, 20 * 60));
        add(new Advertisement(someContent, "Eight Video", 3000, 3, 36 * 60));
        add(new Advertisement(someContent, "Ninth Video", 500, 2, 3 * 60));
        add(new Advertisement(someContent, "Ten Video", 4500, 4, 18 * 60));
    }

    static synchronized AdvertisementStorage getInstance() {
        if (instance==null)
            instance = new AdvertisementStorage();


        return instance;
    }

    public List<Advertisement> list(){
        return videos;
    }

   private void add(Advertisement advertisement){
        videos.add(advertisement);

   }

}
