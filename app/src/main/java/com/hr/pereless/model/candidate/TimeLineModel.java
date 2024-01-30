package com.hr.pereless.model.candidate;

import android.util.Log;

import org.json.JSONObject;

public class TimeLineModel {
    int type =1 ,entityid,recruiter_id,cid,rid,jid;
    String icon,jobstate,Notes,canname= "",job_title,jobcity,UID,color,r_flowid,Action,Enterdate,UserName;
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public int getEntityid() {
        return entityid;
    }

    public void setEntityid(int entityid) {
        this.entityid = entityid;
    }

    public int getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(int recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getJobstate() {
        return jobstate;
    }

    public void setJobstate(String jobstate) {
        this.jobstate = jobstate;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getCanname() {
        return canname;
    }

    public void setCanname(String canname) {
        this.canname = canname;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJobcity() {
        return jobcity;
    }

    public void setJobcity(String jobcity) {
        this.jobcity = jobcity;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getR_flowid() {
        return r_flowid;
    }

    public void setR_flowid(String r_flowid) {
        this.r_flowid = r_flowid;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getEnterdate() {
        return Enterdate;
    }

    public void setEnterdate(String enterdate) {
        Enterdate = enterdate;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setIcon(jsonObject.getString("icon"));
//            setRid(jsonObject.getInt("rid"));
//            setJid(jsonObject.getInt("jid"));
            setJobstate(jsonObject.getString("jobstate"));
            setEnterdate(jsonObject.getString("Enterdate"));
            setUserName(jsonObject.getString("UserName"));
            setJob_title(jsonObject.getString("job_title"));
            setNotes(jsonObject.getString("Notes"));
            setCanname(jsonObject.getString("canname"));
            setAction(jsonObject.getString("Action"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
