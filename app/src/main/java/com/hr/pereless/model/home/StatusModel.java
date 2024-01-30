package com.hr.pereless.model.home;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class StatusModel {
    int moveCountToday,candidateCountToday,jboCountToday,candidateCount,jobcCount,eventCountToday,moveCount,eventCount,savedJobCount;

    public int getMoveCountToday() {
        return moveCountToday;
    }

    public void setMoveCountToday(int moveCountToday) {
        this.moveCountToday = moveCountToday;
    }

    public int getCandidateCountToday() {
        return candidateCountToday;
    }

    public void setCandidateCountToday(int candidateCountToday) {
        this.candidateCountToday = candidateCountToday;
    }

    public int getJboCountToday() {
        return jboCountToday;
    }

    public void setJboCountToday(int jboCountToday) {
        this.jboCountToday = jboCountToday;
    }

    public int getCandidateCount() {
        return candidateCount;
    }

    public void setCandidateCount(int candidateCount) {
        this.candidateCount = candidateCount;
    }

    public int getJobcCount() {
        return jobcCount;
    }

    public void setJobcCount(int jobcCount) {
        this.jobcCount = jobcCount;
    }

    public int getEventCountToday() {
        return eventCountToday;
    }

    public void setEventCountToday(int eventCountToday) {
        this.eventCountToday = eventCountToday;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public int getSavedJobCount() {
        return savedJobCount;
    }

    public void setSavedJobCount(int savedJobCount) {
        this.savedJobCount = savedJobCount;
    }

    public void initModel(String json){
        try{
            //JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            setMoveCountToday(jsonObject.getInt("moveCountToday"));;
            setCandidateCountToday(jsonObject.getInt("candidateCountToday"));;
            setJboCountToday(jsonObject.getInt("jboCountToday"));;
            setCandidateCount(jsonObject.getInt("candidateCount"));;
            setJobcCount(jsonObject.getInt("jobcCount"));
            setEventCountToday(jsonObject.getInt("eventCountToday"));
            setMoveCount(jsonObject.getInt("moveCount"));
            setEventCount(jsonObject.getInt("eventCount"));
            setSavedJobCount(jsonObject.getInt("savedCount"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
