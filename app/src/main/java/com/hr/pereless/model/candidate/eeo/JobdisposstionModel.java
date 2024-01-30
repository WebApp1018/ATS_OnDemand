package com.hr.pereless.model.candidate.eeo;

import android.util.Log;

import org.json.JSONObject;

public class JobdisposstionModel {
    int rid,jid;
    String job_title,flowname,rteeodisp,eeodisp,state,dname,city;

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

    public String getRteeodisp() {
        return rteeodisp;
    }

    public void setRteeodisp(String rteeodisp) {
        this.rteeodisp = rteeodisp;
    }

    public String getEeodisp() {
        return eeodisp;
    }

    public void setEeodisp(String eeodisp) {
        this.eeodisp = eeodisp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setRid(jsonObject.getInt("rid"));
            setJid(jsonObject.getInt("jid"));
            setJob_title(jsonObject.getString("job_title"));
            setEeodisp(jsonObject.getString("eeodisp"));
            setFlowname(jsonObject.getString("flowname"));
            setRteeodisp(jsonObject.getString("rteeodisp"));
            setState(jsonObject.getString("state"));
            setDname(jsonObject.getString("dname"));
            setCity(jsonObject.getString("city"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
