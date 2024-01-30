package com.hr.pereless.model.schedule;

import android.util.Log;

import org.json.JSONObject;

public class ScheduleModel {
    String Etype, start_time, comment,end_time,etable,location,start_date,subject, jid,rid, myoffset,notime,sch_id,status,timezone;

    public String getEtype() {
        return Etype;
    }

    public void setEtype(String etype) {
        Etype = etype;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEtable() {
        return etable;
    }

    public void setEtable(String etable) {
        this.etable = etable;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getMyoffset() {
        return myoffset;
    }

    public void setMyoffset(String myoffset) {
        this.myoffset = myoffset;
    }

    public String getNotime() {
        return notime;
    }

    public void setNotime(String notime) {
        this.notime = notime;
    }

    public String getSch_id() {
        return sch_id;
    }

    public void setSch_id(String sch_id) {
        this.sch_id = sch_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setEtype(jsonObject.getString("Etype"));
            setStart_time(jsonObject.getString("Start_time"));
            setComment(jsonObject.getString("comment"));
            setEnd_time(jsonObject.getString("end_time"));
            setEtable(jsonObject.getString("etable"));
            setJid(jsonObject.getString("fk_JJID"));
            setRid(jsonObject.getString("fk_RID"));
            setLocation(jsonObject.getString("location"));
            setMyoffset(jsonObject.getString("myoffset"));
            setNotime(jsonObject.getString("notime"));
            setSch_id(jsonObject.getString("sch_id"));
            setStart_date(jsonObject.getString("start_date"));
            setStatus(jsonObject.getString("status"));
            setSubject(jsonObject.getString("subject"));
            setTimezone(jsonObject.getString("timezone"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
