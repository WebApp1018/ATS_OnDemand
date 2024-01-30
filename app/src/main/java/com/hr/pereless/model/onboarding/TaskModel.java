package com.hr.pereless.model.onboarding;

import android.util.Log;

import org.json.JSONObject;

public class TaskModel {
    String taskSubject,completedate;
    int task_id,daysToComplete;

    public int getDaysToComplete() {
        return daysToComplete;
    }

    public void setDaysToComplete(int daysToComplete) {
        this.daysToComplete = daysToComplete;
    }

    public String getTaskSubject() {
        return taskSubject;
    }

    public void setTaskSubject(String taskSubject) {
        this.taskSubject = taskSubject;
    }

    public String getCompletedate() {
        return completedate;
    }

    public void setCompletedate(String completedate) {
        this.completedate = completedate;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setCompletedate(jsonObject.getString("completedate"));
            setTask_id(jsonObject.getInt("task_id"));
            setTaskSubject(jsonObject.getString("taskSubject"));
            if(jsonObject.getString("daysToComplete").length()!=0)
                setDaysToComplete(jsonObject.getInt("daysToComplete"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
