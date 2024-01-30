package com.hr.pereless.model.onboarding;

import android.util.Log;

import com.hr.pereless.model.job.RecruiterModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class TeamNotificationModel {
    String contact,contactList,teamNotificationMessage,teamNotificationName,documentID,teamNotificationAssignedTo;
    int isGlobal,taskid,totalPages,status,priority,MaxRows,teamNotificationDaysToComplete;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactList() {
        return contactList;
    }

    public void setContactList(String contactList) {
        this.contactList = contactList;
    }

    public String getTeamNotificationMessage() {
        return teamNotificationMessage;
    }

    public void setTeamNotificationMessage(String teamNotificationMessage) {
        this.teamNotificationMessage = teamNotificationMessage;
    }

    public String getTeamNotificationName() {
        return teamNotificationName;
    }

    public void setTeamNotificationName(String teamNotificationName) {
        this.teamNotificationName = teamNotificationName;
    }

    public int getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(int isGlobal) {
        this.isGlobal = isGlobal;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getTeamNotificationAssignedTo() {
        return teamNotificationAssignedTo;
    }

    public void setTeamNotificationAssignedTo(String teamNotificationAssignedTo) {
        this.teamNotificationAssignedTo = teamNotificationAssignedTo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getMaxRows() {
        return MaxRows;
    }

    public void setMaxRows(int maxRows) {
        MaxRows = maxRows;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public int getTeamNotificationDaysToComplete() {
        return teamNotificationDaysToComplete;
    }

    public void setTeamNotificationDaysToComplete(int teamNotificationDaysToComplete) {
        this.teamNotificationDaysToComplete = teamNotificationDaysToComplete;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setContact(jsonObject.getString("contact"));
            setIsGlobal(jsonObject.getInt("isGlobal"));
            setContactList(jsonObject.getString("contactList"));
            setTeamNotificationMessage(jsonObject.getString("teamNotificationMessage"));
            setTaskid(jsonObject.getInt("taskid"));
            setTotalPages(jsonObject.getInt("totalPages"));
            setTeamNotificationAssignedTo(jsonObject.getString("teamNotificationAssignedTo"));
            setTeamNotificationName(jsonObject.getString("teamNotificationName"));
            setStatus(jsonObject.getInt("status"));
            setPriority(jsonObject.getInt("priority"));
            setMaxRows(jsonObject.getInt("MaxRows"));
            setDocumentID(jsonObject.getString("documentID"));
            setTeamNotificationDaysToComplete(jsonObject.getInt("teamNotificationDaysToComplete"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
