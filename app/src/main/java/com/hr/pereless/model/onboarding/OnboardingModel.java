package com.hr.pereless.model.onboarding;

import android.util.Log;

import org.json.JSONObject;

public class OnboardingModel {
    int completeddocs,completedtasks,totaldocs,rownumber,rid,currentsteppercentage,jid,totaltasks;
    String jobstate,readytosend,job_title,candidatezip,jobcity,closingdate,department,candidatecity,candidatestate,
            hirelogdate,startingdate,candidatelastname,candidateemail,trackingcode,empemail,candidatefirstname;

    public int getCompleteddocs() {
        return completeddocs;
    }

    public void setCompleteddocs(int completeddocs) {
        this.completeddocs = completeddocs;
    }

    public int getCompletedtasks() {
        return completedtasks;
    }

    public void setCompletedtasks(int completedtasks) {
        this.completedtasks = completedtasks;
    }

    public int getTotaldocs() {
        return totaldocs;
    }

    public void setTotaldocs(int totaldocs) {
        this.totaldocs = totaldocs;
    }

    public int getRownumber() {
        return rownumber;
    }

    public void setRownumber(int rownumber) {
        this.rownumber = rownumber;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getCurrentsteppercentage() {
        return currentsteppercentage;
    }

    public void setCurrentsteppercentage(int currentsteppercentage) {
        this.currentsteppercentage = currentsteppercentage;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public int getTotaltasks() {
        return totaltasks;
    }

    public void setTotaltasks(int totaltasks) {
        this.totaltasks = totaltasks;
    }

    public String getJobstate() {
        return jobstate;
    }

    public void setJobstate(String jobstate) {
        this.jobstate = jobstate;
    }

    public String getReadytosend() {
        return readytosend;
    }

    public void setReadytosend(String readytosend) {
        this.readytosend = readytosend;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getCandidatezip() {
        return candidatezip;
    }

    public void setCandidatezip(String candidatezip) {
        this.candidatezip = candidatezip;
    }

    public String getJobcity() {
        return jobcity;
    }

    public void setJobcity(String jobcity) {
        this.jobcity = jobcity;
    }

    public String getClosingdate() {
        return closingdate;
    }

    public void setClosingdate(String closingdate) {
        this.closingdate = closingdate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCandidatecity() {
        return candidatecity;
    }

    public void setCandidatecity(String candidatecity) {
        this.candidatecity = candidatecity;
    }

    public String getCandidatestate() {
        return candidatestate;
    }

    public void setCandidatestate(String candidatestate) {
        this.candidatestate = candidatestate;
    }

    public String getHirelogdate() {
        return hirelogdate;
    }

    public void setHirelogdate(String hirelogdate) {
        this.hirelogdate = hirelogdate;
    }

    public String getStartingdate() {
        return startingdate;
    }

    public void setStartingdate(String startingdate) {
        this.startingdate = startingdate;
    }

    public String getCandidatelastname() {
        return candidatelastname;
    }

    public void setCandidatelastname(String candidatelastname) {
        this.candidatelastname = candidatelastname;
    }

    public String getCandidateemail() {
        return candidateemail;
    }

    public void setCandidateemail(String candidateemail) {
        this.candidateemail = candidateemail;
    }

    public String getTrackingcode() {
        return trackingcode;
    }

    public void setTrackingcode(String trackingcode) {
        this.trackingcode = trackingcode;
    }

    public String getEmpemail() {
        return empemail;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }

    public String getCandidatefirstname() {
        return candidatefirstname;
    }

    public void setCandidatefirstname(String candidatefirstname) {
        this.candidatefirstname = candidatefirstname;
    }
    public String getDisplayName(){
        String name = "";
        if(getCandidatefirstname().length()>0 && getCandidatelastname().length()>0)
            name = String.valueOf(getCandidatefirstname().charAt(0)) + String.valueOf(getCandidatelastname().charAt(0));

        return name;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setCompleteddocs(jsonObject.getInt("completeddocs"));
            setJobstate(jsonObject.getString("jobstate"));
            setReadytosend(jsonObject.getString("readytosend"));
            setJob_title(jsonObject.getString("job_title"));
            setCandidatezip(jsonObject.getString("candidatezip"));
            setJobcity(jsonObject.getString("jobcity"));
            setCompletedtasks(jsonObject.getInt("completedtasks"));
            setClosingdate(jsonObject.getString("closingdate"));
            setDepartment(jsonObject.getString("department"));
            setTotaldocs(jsonObject.getInt("totaldocs"));
            setCandidatecity(jsonObject.getString("candidatecity"));
            setRownumber(jsonObject.getInt("rownumber"));
            setCandidatestate(jsonObject.getString("candidatestate"));
            setRid(jsonObject.getInt("rid"));
            setCurrentsteppercentage(jsonObject.getInt("currentsteppercentage"));
            setHirelogdate(jsonObject.getString("hirelogdate"));
            setStartingdate(jsonObject.getString("startingdate"));
            setJid(jsonObject.getInt("jid"));
            setCandidatelastname(jsonObject.getString("candidatelastname"));
            setCandidateemail(jsonObject.getString("candidateemail"));
            setTotaltasks(jsonObject.getInt("totaltasks"));
            setTrackingcode(jsonObject.getString("trackingcode"));
            setEmpemail(jsonObject.getString("empemail"));
            setCandidatefirstname(jsonObject.getString("candidatefirstname"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
