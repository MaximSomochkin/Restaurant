package com.max.restaurant.statistic.event;

import com.max.restaurant.ad.Advertisement;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class VideoSelectedEventDataRow implements EventDataRow {
    private List<Advertisement> optimalVideoSet;
    private long amount;
    private int totalDuration;
   private Date currentDate;
  // private  static int i=0;

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;

        currentDate=new Date();

       // private  Date datePius1Day (Date date){
//       Calendar calendar = new GregorianCalendar();
//        calendar.setTime(currentDate);
//        if(i<2) {
//            calendar.set(Calendar.DAY_OF_MONTH, (int) (Math.random() * 30));
//            i++;
//            currentDate = calendar.getTime();
        }



    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }

    public long getAmount() {
        return amount;
    }


}
