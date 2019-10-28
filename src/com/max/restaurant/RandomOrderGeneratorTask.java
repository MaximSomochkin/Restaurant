package com.max.restaurant;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private List<Tablet> tablets;
    private int interval;
    RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        Tablet tablet = tablets.get((int) (Math.random() * tablets.size() - 1));
        // Tablet tablet1 = tablets.get((int) (Math.random()*tablets.size()-1));

        try {
            tablet.createTestOrder();
            // tablet1.createTestOrder();

        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
