package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class JobkeywordModel {
    int CID,HBaflag,HotbookID ;
    String HBbgcolor, HotbookName, HBdefault;

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getHBaflag() {
        return HBaflag;
    }

    public void setHBaflag(int HBaflag) {
        this.HBaflag = HBaflag;
    }

    public String getHBdefault() {
        return HBdefault;
    }

    public void setHBdefault(String HBdefault) {
        this.HBdefault = HBdefault;
    }

    public int getHotbookID() {
        return HotbookID;
    }

    public void setHotbookID(int hotbookID) {
        HotbookID = hotbookID;
    }

    public String getHBbgcolor() {
        return HBbgcolor;
    }

    public void setHBbgcolor(String HBbgcolor) {
        this.HBbgcolor = HBbgcolor;
    }

    public String getHotbookName() {
        return HotbookName;
    }

    public void setHotbookName(String hotbookName) {
        HotbookName = hotbookName;
    }

    public void initModel(JSONObject jsonObject){
        try {
            setCID(jsonObject.getInt("CID"));
            setHBaflag(jsonObject.getInt("HBaflag"));
            setHBdefault(jsonObject.getString("HBdefault"));
            setHotbookID(jsonObject.getInt("HotbookID"));
            setHBbgcolor(jsonObject.getString("HBbgcolor"));
            setHotbookName(jsonObject.getString("HotbookName"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
