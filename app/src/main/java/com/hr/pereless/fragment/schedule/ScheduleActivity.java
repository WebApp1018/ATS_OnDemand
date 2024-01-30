package com.hr.pereless.fragment.schedule;

import android.graphics.Color;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.hr.pereless.view.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends ScheduleBasicActivity {

    // private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    boolean calledNetwork = false;
    static String token;
    static Calendar startTime = Calendar.getInstance();

    public static Fragment newInstance() {

        ScheduleBasicActivity fragment = new ScheduleBasicActivity();

        return fragment;
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(final int newYear, final int newMonth) {

        final List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        Log.e("TaskDate:", "NewYear:" + newYear + " newMonth:" + newMonth);

        if (ScheduleResponseList.size() > 0) {
//
//            Log.e("TaskDateSize:", ScheduleResponseList.size() + " nan");
//            //for (int i = 0; i < ScheduleResponseList.size() - 1; i++) {
//
//            Log.e("TaskDate:", ScheduleResponseList.get(0).getInterviewdatetime());
//
//            String tempDateAndTime = ScheduleResponseList.get(0).getInterviewdatetime();
//            int interviewDuration = ScheduleResponseList.get(0).getInterviewduration();
//
//            String[] splitTime = tempDateAndTime.split("T");
//            String dateMonthYear = splitTime[0];
//            String time = splitTime[1];
//
//            String hour[] = time.split(":", 3);
//
//            Log.e("HOUR", hour[0] + "min" + hour[1]);
//
//            String dateDetails[] = dateMonthYear.split("-");
//            int year = Integer.valueOf(dateDetails[0]);
//            int month = Integer.valueOf(dateDetails[1]);
//            int day = Integer.valueOf(dateDetails[2]);
//            int hourvalue = Integer.valueOf(hour[0]);
//            int mintsvalueof = Integer.valueOf(hour[1]);
//            Log.e("DATE-VALUE", "Year: " + year + " Month:" + month + " Date:" + day + " Hour:" + hourvalue + " Minute:" + mintsvalueof);
//
//            Calendar startTime = Calendar.getInstance();
//            startTime.set(Calendar.HOUR_OF_DAY, day);
//            startTime.set(Calendar.MINUTE, mintsvalueof);
//            startTime.set(Calendar.MONTH, newMonth - 1);
//            startTime.set(Calendar.YEAR, newYear);
//            Calendar endTime = (Calendar) startTime.clone();
//            endTime.add(Calendar.HOUR, 1);
//            endTime.set(Calendar.MONTH, newMonth - 1);
//            WeekViewEvent event = new WeekViewEvent(1,ScheduleResponseList.get(0).getName()+", "+ScheduleResponseList.get(0).getLocation()+", "+ScheduleResponseList.get(0).getMessage(), startTime, endTime);
//            //event.setColor(getResources().getColor(R.color.blue));
//            String changecolor = ScheduleResponseList.get(0).getPrimarycolor();
//            event.setColor(Color.parseColor(changecolor));
//            events.add(event);


                /*Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hourvalue);
                startTime.set(Calendar.MINUTE, mintsvalueof);

                startTime.set(Calendar.MONTH, month - 1);
                startTime.set(Calendar.YEAR, year);
                startTime.set(Calendar.DAY_OF_MONTH, day);

                Calendar endTime = Calendar.getInstance();
                endTime.set(Calendar.HOUR_OF_DAY, hourvalue);
                endTime.set(Calendar.MINUTE, mintsvalueof + interviewDuration);

                endTime.set(Calendar.MONTH, month - 1);
                endTime.set(Calendar.YEAR, year);
                endTime.set(Calendar.DAY_OF_MONTH, day);

                WeekViewEvent event = new WeekViewEvent(i, ScheduleResponseList.get(i).getName(), startTime, endTime);
                String changecolor = ScheduleResponseList.get(i).getPrimarycolor();
                event.setColor(Color.parseColor(changecolor));
                events.add(event);*/
            // }
        }

        return events;
    }

    /**
     * Checks if an event falls into a specific year and month.
     *
     * @param event The event to check for.
     * @param year  The year.
     * @param month The month.
     * @return True if the event matches the year and month.
     */
    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

}
  /*
   @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();





                Calendar startTime = Calendar.getInstance();
               */

  /* String tempdateandtime=S;
                String []splittime=tempdateandtime.split("T");
                String time = splittime[1];
                String hour=time.substring(0,1);*//*

                //Log.e("hour is ",hour);
                startTime.set(Calendar.HOUR_OF_DAY,21 );
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, 1);
                endTime.set(Calendar.MONTH, newMonth - 1);
                WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.redbutton));
                events.add(event);

                startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 3);
                startTime.set(Calendar.MINUTE, 30);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                endTime = (Calendar) startTime.clone();
                endTime.set(Calendar.HOUR_OF_DAY, 4);
                endTime.set(Calendar.MINUTE, 30);
                endTime.set(Calendar.MONTH, newMonth - 1);
                event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.graybutton));
                events.add(event);

                startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 4);
                startTime.set(Calendar.MINUTE, 20);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                endTime = (Calendar) startTime.clone();
                endTime.set(Calendar.HOUR_OF_DAY, 5);
                endTime.set(Calendar.MINUTE, 0);
                event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.graytext));
                events.add(event);

                startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 5);
                startTime.set(Calendar.MINUTE, 30);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR_OF_DAY, 2);
                endTime.set(Calendar.MONTH, newMonth - 1);
                event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.colorLightOrange));
                events.add(event);

                startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 5);
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                startTime.add(Calendar.DATE, 1);
                endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR_OF_DAY, 3);
                endTime.set(Calendar.MONTH, newMonth - 1);
                event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.colorLightOrange));
                events.add(event);

                startTime = Calendar.getInstance();
                startTime.set(Calendar.DAY_OF_MONTH, 15);
                startTime.set(Calendar.HOUR_OF_DAY, 3);
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR_OF_DAY, 3);
                event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.colorLightOrange));
                events.add(event);

                startTime = Calendar.getInstance();
                startTime.set(Calendar.DAY_OF_MONTH, 1);
                startTime.set(Calendar.HOUR_OF_DAY, 3);
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR_OF_DAY, 3);
                event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.colorLightOrange));
                events.add(event);

                startTime = Calendar.getInstance();
                startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
                startTime.set(Calendar.HOUR_OF_DAY, 15);
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR_OF_DAY, 3);
                event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.colorLightOrange));
                events.add(event);




        // All day event until 00:00 next day

        return events;
    }


    private static void  getsheduls() {

        Call<ScheduleResponse> call = apiService.getSchedule("application/json", token);
        call.enqueue(new retrofit2.Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                Log.e("Scheduleresp", new Gson().toJson(response.body()));

                if (response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                Log.e("Schedulerespfail", new Gson().toJson(t.getMessage()));

            }
        });

    }*/


