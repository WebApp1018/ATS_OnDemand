package com.hr.pereless.model.candidate;

import android.util.Log;

import org.json.JSONObject;

public class AppliedJobModel {
    String jobtotalconfidential,job_title,fkactiontype,ranking,fkjoblocation,flownum,flowname,dept_no,
        recruitFlowID,state,aflag,dname,city,enterdate,trackingcode,jobowner,SGID;
    int jid;


    public String getSGID() {
        return SGID;
    }

    public void setSGID(String SGID) {
        this.SGID = SGID;
    }

    public String getJobowner() {
        return jobowner;
    }

    public void setJobowner(String jobowner) {
        this.jobowner = jobowner;
    }

    public String getJobtotalconfidential() {
        return jobtotalconfidential;
    }

    public void setJobtotalconfidential(String jobtotalconfidential) {
        this.jobtotalconfidential = jobtotalconfidential;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getFkactiontype() {
        return fkactiontype;
    }

    public void setFkactiontype(String fkactiontype) {
        this.fkactiontype = fkactiontype;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getFkjoblocation() {
        return fkjoblocation;
    }

    public void setFkjoblocation(String fkjoblocation) {
        this.fkjoblocation = fkjoblocation;
    }

    public String getFlownum() {
        return flownum;
    }

    public void setFlownum(String flownum) {
        this.flownum = flownum;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public String getRecruitFlowID() {
        return recruitFlowID;
    }

    public void setRecruitFlowID(String recruitFlowID) {
        this.recruitFlowID = recruitFlowID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAflag() {
        return aflag;
    }

    public void setAflag(String aflag) {
        this.aflag = aflag;
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

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }

    public String getTrackingcode() {
        return trackingcode;
    }

    public void setTrackingcode(String trackingcode) {
        this.trackingcode = trackingcode;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setJid(jsonObject.getInt("jid"));
            setJobtotalconfidential(jsonObject.getString("jobtotalconfidential"));
            setJob_title(jsonObject.getString("job_title"));
            setSGID(jsonObject.getString("SGID"));
            setFkactiontype(jsonObject.getString("fkactiontype"));
            setRanking(jsonObject.getString("ranking"));
            setFkjoblocation(jsonObject.getString("fkjoblocation"));
            setFlownum(jsonObject.getString("flownum"));
            setFlowname(jsonObject.getString("flowname"));
            setDept_no(jsonObject.getString("dept_no"));
            setRecruitFlowID(jsonObject.getString("recruitFlowID"));
            setState(jsonObject.getString("state"));
            setAflag(jsonObject.getString("aflag"));
            setDname(jsonObject.getString("dname"));
            setCity(jsonObject.getString("city"));
            setEnterdate(jsonObject.getString("enterdate"));
            setTrackingcode(jsonObject.getString("trackingcode"));
            setJobowner(jsonObject.getString("jobowner"));


        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
