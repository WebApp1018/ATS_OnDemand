package com.hr.pereless.model.home;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActivityChatModel {
    int Moved,JobsToday,CandidatesToday,MovedToay,Events,Candidates,EventsToday,Jobs;
    String ChartType;

    public int getMoved() {
        return Moved;
    }

    public void setMoved(int moved) {
        Moved = moved;
    }

    public int getJobsToday() {
        return JobsToday;
    }

    public void setJobsToday(int jobsToday) {
        JobsToday = jobsToday;
    }

    public int getCandidatesToday() {
        return CandidatesToday;
    }

    public void setCandidatesToday(int candidatesToday) {
        CandidatesToday = candidatesToday;
    }

    public int getMovedToay() {
        return MovedToay;
    }

    public void setMovedToay(int movedToay) {
        MovedToay = movedToay;
    }

    public int getEvents() {
        return Events;
    }

    public void setEvents(int events) {
        Events = events;
    }

    public int getCandidates() {
        return Candidates;
    }

    public void setCandidates(int candidates) {
        Candidates = candidates;
    }

    public int getEventsToday() {
        return EventsToday;
    }

    public void setEventsToday(int eventsToday) {
        EventsToday = eventsToday;
    }

    public int getJobs() {
        return Jobs;
    }

    public void setJobs(int jobs) {
        Jobs = jobs;
    }

    public String getChartType() {
        return ChartType;
    }

    public void setChartType(String chartType) {
        ChartType = chartType;
    }

    public void initModel(String json){
        try{
            //JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            setMoved(jsonObject.getInt("Moved"));;
            setChartType(jsonObject.getString("ChartType"));;
            setJobsToday(jsonObject.getInt("JobsToday"));;
            setCandidatesToday(jsonObject.getInt("CandidatesToday"));;
            setMovedToay(jsonObject.getInt("MovedToay"));
            setEvents(jsonObject.getInt("Events"));
            setCandidates(jsonObject.getInt("Candidates"));
            setEventsToday(jsonObject.getInt("EventsToday"));
            setJobs(jsonObject.getInt("Jobs"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
