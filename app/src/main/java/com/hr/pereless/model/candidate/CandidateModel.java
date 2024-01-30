package com.hr.pereless.model.candidate;

import android.util.Log;

import com.hr.pereless.model.job.RecruiterModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class CandidateModel {
    int buid,ranking,companytype,department,flownum,Aflag,fkjobowner,jobowner,Employeeid,priority,rid,dept_no,
            canstate,jid;
    String job_title,mobile,DivisionNumber,State,businessunitname,Region,dname,RegionName,city,cancity,email,
            source,canname,fkiskillscore,StatusDate,startdate,altsource,flowname,DepartmentName,phone,enterdate,trackingcode;

    CandidateUserModel candidateUserModel = new CandidateUserModel();

    public CandidateUserModel getCandidateUserModel() {
        return candidateUserModel;
    }

    public void setCandidateUserModel(CandidateUserModel candidateUserModel) {
        this.candidateUserModel = candidateUserModel;
    }

    public int getBuid() {
        return buid;
    }

    public void setBuid(int buid) {
        this.buid = buid;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getCompanytype() {
        return companytype;
    }

    public void setCompanytype(int companytype) {
        this.companytype = companytype;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getFlownum() {
        return flownum;
    }

    public void setFlownum(int flownum) {
        this.flownum = flownum;
    }

    public int getAflag() {
        return Aflag;
    }

    public void setAflag(int aflag) {
        Aflag = aflag;
    }

    public int getFkjobowner() {
        return fkjobowner;
    }

    public void setFkjobowner(int fkjobowner) {
        this.fkjobowner = fkjobowner;
    }

    public int getJobowner() {
        return jobowner;
    }

    public void setJobowner(int jobowner) {
        this.jobowner = jobowner;
    }

    public int getEmployeeid() {
        return Employeeid;
    }

    public void setEmployeeid(int employeeid) {
        Employeeid = employeeid;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    public int getCanstate() {
        return canstate;
    }

    public void setCanstate(int canstate) {
        this.canstate = canstate;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDivisionNumber() {
        return DivisionNumber;
    }

    public void setDivisionNumber(String divisionNumber) {
        DivisionNumber = divisionNumber;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getBusinessunitname() {
        return businessunitname;
    }

    public void setBusinessunitname(String businessunitname) {
        this.businessunitname = businessunitname;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCancity() {
        return cancity;
    }

    public void setCancity(String cancity) {
        this.cancity = cancity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCanname() {
        return canname;
    }

    public void setCanname(String canname) {
        this.canname = canname;
    }

    public String getFkiskillscore() {
        return fkiskillscore;
    }

    public void setFkiskillscore(String fkiskillscore) {
        this.fkiskillscore = fkiskillscore;
    }

    public String getStatusDate() {
        return StatusDate;
    }

    public void setStatusDate(String statusDate) {
        StatusDate = statusDate;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getAltsource() {
        return altsource;
    }

    public void setAltsource(String altsource) {
        this.altsource = altsource;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    public String getDisplayName(){
        String name = "";
        if(getCanname().split(" ").length>=2){
            name = String.valueOf(getCanname().split(" ")[0].charAt(0)) + String.valueOf(getCanname().split(" ")[1].charAt(0));
        }
        return name;
    }
    public void initModel(JSONObject jsonObject){
        try{
            if(jsonObject.getString("buid").length()>0)
                setBuid(jsonObject.getInt("buid"));
            setRid(jsonObject.getInt("rid"));
            setJid(jsonObject.getInt("jid"));
            setEmail(jsonObject.getString("email"));
            setEnterdate(jsonObject.getString("enterdate"));
            setSource(jsonObject.getString("source"));
            setJob_title(jsonObject.getString("job_title"));
            setFlowname(jsonObject.getString("flowname"));
            setCanname(jsonObject.getString("canname"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
    public void initDetailModel(JSONObject jsonObject){
        try{
            setRid(jsonObject.getInt("RID"));
            //setJid(jsonObject.getInt("jid"));
            setEmail(jsonObject.getString("CanEmail"));
            setEnterdate(jsonObject.getString("EnterDate"));
            setSource(jsonObject.getString("OrgSource"));
            setJob_title(jsonObject.getString("cjobtitle"));
//            setFlowname(jsonObject.getString("flowname"));
//            setCanname(jsonObject.getString("canname"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
