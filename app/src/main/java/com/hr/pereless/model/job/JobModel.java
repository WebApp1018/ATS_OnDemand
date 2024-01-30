package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class JobModel implements Serializable {
    String job_title,jobcountry,jobzip,statusdate,jobowner,State,jobtype,City,userlike,
            replacementName,customdescription,RequiredExperience,BUGroupName,jobhistory,BUlogo,experiencerequired,
            trackingcode,hirejobdate,notes,jobcity,budgetd,StatusCode,jobtitle,BusinessUnitName,dname,description,
            jobstate,quicknote,country,zip,jobkeyword,location,agencyId,customtext,joblanguage,postingdate,flsnamee,salary;
    int stateactivejobs,jobownerID,MaxRows,numberofcondidate,isChecked,jobID,candidatecount
        ,JobQ_num,futureintrest,Iscloned,JobTotalconfidential,daysopen,cid,interview,dept_no,offerApproval,
            IsjobClosed,BUID,jobsub_id,videoInterview,status,department,openings,phoneInterview,uid,
            Issavedjob,jid,totalpagesize,pubnet;

    ArrayList<RecruiterModel>recruiterModels = new ArrayList<>();
    ArrayList<RecruiterModel>hm = new ArrayList<>();


    public String getReplacementName() {
        return replacementName;
    }

    public void setReplacementName(String replacementName) {
        this.replacementName = replacementName;
    }

    public String getCustomdescription() {
        return customdescription;
    }

    public void setCustomdescription(String customdescription) {
        this.customdescription = customdescription;
    }

    public String getRequiredExperience() {
        return RequiredExperience;
    }

    public void setRequiredExperience(String requiredExperience) {
        RequiredExperience = requiredExperience;
    }

    public String getBUGroupName() {
        return BUGroupName;
    }

    public void setBUGroupName(String BUGroupName) {
        this.BUGroupName = BUGroupName;
    }

    public String getJobhistory() {
        return jobhistory;
    }

    public void setJobhistory(String jobhistory) {
        this.jobhistory = jobhistory;
    }

    public String getBUlogo() {
        return BUlogo;
    }

    public void setBUlogo(String BUlogo) {
        this.BUlogo = BUlogo;
    }

    public String getExperiencerequired() {
        return experiencerequired;
    }

    public void setExperiencerequired(String experiencerequired) {
        this.experiencerequired = experiencerequired;
    }

    public String getTrackingcode() {
        return trackingcode;
    }

    public void setTrackingcode(String trackingcode) {
        this.trackingcode = trackingcode;
    }

    public String getHirejobdate() {
        return hirejobdate;
    }

    public void setHirejobdate(String hirejobdate) {
        this.hirejobdate = hirejobdate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getJobcity() {
        return jobcity;
    }

    public void setJobcity(String jobcity) {
        this.jobcity = jobcity;
    }

    public String getBudgetd() {
        return budgetd;
    }

    public void setBudgetd(String budgetd) {
        this.budgetd = budgetd;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getBusinessUnitName() {
        return BusinessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        BusinessUnitName = businessUnitName;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobstate() {
        return jobstate;
    }

    public void setJobstate(String jobstate) {
        this.jobstate = jobstate;
    }

    public String getQuicknote() {
        return quicknote;
    }

    public void setQuicknote(String quicknote) {
        this.quicknote = quicknote;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getJobkeyword() {
        return jobkeyword;
    }

    public void setJobkeyword(String jobkeyword) {
        this.jobkeyword = jobkeyword;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getCustomtext() {
        return customtext;
    }

    public void setCustomtext(String customtext) {
        this.customtext = customtext;
    }

    public String getJoblanguage() {
        return joblanguage;
    }

    public void setJoblanguage(String joblanguage) {
        this.joblanguage = joblanguage;
    }

    public String getPostingdate() {
        return postingdate;
    }

    public void setPostingdate(String postingdate) {
        this.postingdate = postingdate;
    }

    public String getFlsnamee() {
        return flsnamee;
    }

    public void setFlsnamee(String flsnamee) {
        this.flsnamee = flsnamee;
    }

    public int getJobQ_num() {
        return JobQ_num;
    }

    public void setJobQ_num(int jobQ_num) {
        JobQ_num = jobQ_num;
    }

    public int getFutureintrest() {
        return futureintrest;
    }

    public void setFutureintrest(int futureintrest) {
        this.futureintrest = futureintrest;
    }

    public int getIscloned() {
        return Iscloned;
    }

    public void setIscloned(int iscloned) {
        Iscloned = iscloned;
    }

    public int getJobTotalconfidential() {
        return JobTotalconfidential;
    }

    public void setJobTotalconfidential(int jobTotalconfidential) {
        JobTotalconfidential = jobTotalconfidential;
    }

    public int getDaysopen() {
        return daysopen;
    }

    public void setDaysopen(int daysopen) {
        this.daysopen = daysopen;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getInterview() {
        return interview;
    }

    public void setInterview(int interview) {
        this.interview = interview;
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    public int getOfferApproval() {
        return offerApproval;
    }

    public void setOfferApproval(int offerApproval) {
        this.offerApproval = offerApproval;
    }

    public int getIsjobClosed() {
        return IsjobClosed;
    }

    public void setIsjobClosed(int isjobClosed) {
        IsjobClosed = isjobClosed;
    }

    public int getBUID() {
        return BUID;
    }

    public void setBUID(int BUID) {
        this.BUID = BUID;
    }

    public int getJobsub_id() {
        return jobsub_id;
    }

    public void setJobsub_id(int jobsub_id) {
        this.jobsub_id = jobsub_id;
    }

    public int getVideoInterview() {
        return videoInterview;
    }

    public void setVideoInterview(int videoInterview) {
        this.videoInterview = videoInterview;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getOpenings() {
        return openings;
    }

    public void setOpenings(int openings) {
        this.openings = openings;
    }

    public int getPhoneInterview() {
        return phoneInterview;
    }

    public void setPhoneInterview(int phoneInterview) {
        this.phoneInterview = phoneInterview;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIssavedjob() {
        return Issavedjob;
    }

    public void setIssavedjob(int issavedjob) {
        Issavedjob = issavedjob;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public int getTotalpagesize() {
        return totalpagesize;
    }

    public void setTotalpagesize(int totalpagesize) {
        this.totalpagesize = totalpagesize;
    }

    public int getPubnet() {
        return pubnet;
    }

    public void setPubnet(int pubnet) {
        this.pubnet = pubnet;
    }

    public ArrayList<RecruiterModel> getRecruiterModels() {
        return recruiterModels;
    }

    public void setRecruiterModels(ArrayList<RecruiterModel> recruiterModels) {
        this.recruiterModels = recruiterModels;
    }

    public ArrayList<RecruiterModel> getHm() {
        return hm;
    }

    public void setHm(ArrayList<RecruiterModel> hm) {
        this.hm = hm;
    }

    public String getLocation(){
        return getCity()+", "+ getState() +", " + getJobcountry();
    }
    public String getUserlike() {
        return userlike;
    }

    public void setUserlike(String userlike) {
        this.userlike = userlike;
    }

    public int getCandidatecount() {
        return candidatecount;
    }

    public void setCandidatecount(int candidatecount) {
        this.candidatecount = candidatecount;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJobcountry() {
        return jobcountry;
    }

    public void setJobcountry(String jobcountry) {
        this.jobcountry = jobcountry;
    }

    public String getJobzip() {
        return jobzip;
    }

    public void setJobzip(String jobzip) {
        this.jobzip = jobzip;
    }

    public String getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(String statusdate) {
        this.statusdate = statusdate;
    }

    public String getJobowner() {
        return jobowner;
    }

    public void setJobowner(String jobowner) {
        this.jobowner = jobowner;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getStateactivejobs() {
        return stateactivejobs;
    }

    public void setStateactivejobs(int stateactivejobs) {
        this.stateactivejobs = stateactivejobs;
    }

    public int getJobownerID() {
        return jobownerID;
    }

    public void setJobownerID(int jobownerID) {
        this.jobownerID = jobownerID;
    }

    public int getMaxRows() {
        return MaxRows;
    }

    public void setMaxRows(int maxRows) {
        MaxRows = maxRows;
    }

    public int getNumberofcondidate() {
        return numberofcondidate;
    }

    public void setNumberofcondidate(int numberofcondidate) {
        this.numberofcondidate = numberofcondidate;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }


    public void initModel(JSONObject jsonObject){
        try{
            setJobcountry(jsonObject.getString("jobcountry"));
            setStateactivejobs(jsonObject.getInt("stateactivejobs"));
            setJob_title(jsonObject.getString("job_title"));
            setSalary(jsonObject.getString("salary"));
            setState(jsonObject.getString("State"));
            setJobID(jsonObject.getInt("jid"));
            setCity(jsonObject.getString("jobcity"));
            setJobtype(jsonObject.getString("jobtype"));
            setUserlike(jsonObject.getString("userlike"));
            setCandidatecount(jsonObject.getInt("candidatecount"));
            setStatusdate(jsonObject.getString("statusdate"));
            setDname(jsonObject.getString("dname"));
            setJobkeyword(jsonObject.getString("jobkeyword"));
            setDescription(jsonObject.getString("description"));
            setRequiredExperience(jsonObject.getString("RequiredExperience"));
            setJobownerID(jsonObject.getInt("jobownerID"));
            JSONArray jsonArray = jsonObject.getJSONArray("recruiters");
            recruiterModels.clear();
            for(int i =0;i<jsonArray.length();i++){
                RecruiterModel recruiterModel = new RecruiterModel();
                recruiterModel.setUid(jsonArray.getJSONObject(i).getInt("uid"));
                recruiterModels.add(recruiterModel);
            }
            jsonArray = jsonObject.getJSONArray("hm");
            hm.clear();
            for(int i =0;i<jsonArray.length();i++){
                RecruiterModel recruiterModel = new RecruiterModel();
                recruiterModel.setUid(jsonArray.getJSONObject(i).getInt("recruiter_id"));
                hm.add(recruiterModel);
            }
            setJobID(jsonObject.getInt("jobID"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
