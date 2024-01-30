package com.hr.pereless.model.email;

import android.util.Log;

import org.json.JSONObject;

public class EmailModel {
    String icon,Notes,entityid,recruiter_id,Action,Enterdate,color;
    int UID,cid,rid,jid;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getEntityid() {
        return entityid;
    }

    public void setEntityid(String entityid) {
        this.entityid = entityid;
    }

    public String getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(String recruiter_id) {
        this.recruiter_id = recruiter_id;
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

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public void initModel(JSONObject jsonObject){
        try{
            setIcon(jsonObject.getString("icon"));
            setNotes(jsonObject.getString("Notes"));
            setEntityid(jsonObject.getString("entityid"));
            setRecruiter_id(jsonObject.getString("recruiter_id"));
            setUID(jsonObject.getInt("UID"));
            setColor(jsonObject.getString("color"));
            setCid(jsonObject.getInt("cid"));
            setRid(jsonObject.getInt("rid"));
            setJid(jsonObject.getInt("jid"));
            setAction(jsonObject.getString("Action"));
            setEnterdate(jsonObject.getString("Enterdate"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
