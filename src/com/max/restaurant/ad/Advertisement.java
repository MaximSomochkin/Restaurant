package com.max.restaurant.ad;

public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;

        amountPerOneDisplaying = hits>0 ? initialAmount/hits : 0;
    }

    public String getName() {
        return name;
    }

    int getDuration() {
        return duration;
    }

    long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    void revalidate(){
        if (hits<=0)
            throw new NoVideoAvailableException();

        hits--;
    }

    public int getHits() {
        return hits;
    }
}
