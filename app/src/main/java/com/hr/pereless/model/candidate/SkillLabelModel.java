package com.hr.pereless.model.candidate;

import android.util.Log;

import org.json.JSONObject;

public class SkillLabelModel {
    String HBbgcolor,HotbookID,CID,HBdefault,HotbookName,HBaflag;

    public String getHBbgcolor() {
        return HBbgcolor;
    }

    public void setHBbgcolor(String HBbgcolor) {
        this.HBbgcolor = HBbgcolor;
    }

    public String getHotbookID() {
        return HotbookID;
    }

    public void setHotbookID(String hotbookID) {
        HotbookID = hotbookID;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getHBdefault() {
        return HBdefault;
    }

    public void setHBdefault(String HBdefault) {
        this.HBdefault = HBdefault;
    }

    public String getHotbookName() {
        return HotbookName;
    }

    public void setHotbookName(String hotbookName) {
        HotbookName = hotbookName;
    }

    public String getHBaflag() {
        return HBaflag;
    }

    public void setHBaflag(String HBaflag) {
        this.HBaflag = HBaflag;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setHBbgcolor(jsonObject.getString("HBbgcolor"));
            setHotbookID(jsonObject.getString("HotbookID"));
            setCID(jsonObject.getString("CID"));
            setHBdefault(jsonObject.getString("HBdefault"));
            setHotbookName(jsonObject.getString("HotbookName"));
            setHBaflag(jsonObject.getString("HBaflag"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
