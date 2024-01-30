package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class JobFlowModel {
    String job_title,flowname,floworder;
    int cid,flownum,jid,flowcount;

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getFloworder() {
        return floworder;
    }

    public void setFloworder(String floworder) {
        this.floworder = floworder;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getFlownum() {
        return flownum;
    }

    public void setFlownum(int flownum) {
        this.flownum = flownum;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public int getFlowcount() {
        return flowcount;
    }

    public void setFlowcount(int flowcount) {
        this.flowcount = flowcount;
    }
    public void initModel(JSONObject jsonObject) {
        try {
            setCid(jsonObject.getInt("cid"));
            setFlowcount(jsonObject.getInt("flowcount"));
            setJid(jsonObject.getInt("jid"));
            setFlowname(jsonObject.getString("flowname"));
        }catch (Exception e){
            Log.d("Exception ===" , e.toString());
        }
    }
}
