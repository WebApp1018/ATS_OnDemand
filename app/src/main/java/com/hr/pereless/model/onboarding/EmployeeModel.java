package com.hr.pereless.model.onboarding;

import android.util.Log;

import org.json.JSONObject;

public class EmployeeModel {
    String currentpaystr,canaddress,empbonus,emppstartdate,empprimaryphone,empprogram,employeeid,canzip
            ,Currentlocation,canfirstname,currentdeptid,empcurrency,canstate,canlastname,empworkphone,
            currentsupid,empotheremail,empdate,empstate,currentjobtitle,cancity,empemail,emppenddate;
    int paytype,jid;

    public String getCurrentpaystr() {
        return currentpaystr;
    }

    public void setCurrentpaystr(String currentpaystr) {
        this.currentpaystr = currentpaystr;
    }

    public String getCanaddress() {
        return canaddress;
    }

    public void setCanaddress(String canaddress) {
        this.canaddress = canaddress;
    }

    public String getEmpbonus() {
        return empbonus;
    }

    public void setEmpbonus(String empbonus) {
        this.empbonus = empbonus;
    }

    public String getEmppstartdate() {
        return emppstartdate;
    }

    public void setEmppstartdate(String emppstartdate) {
        this.emppstartdate = emppstartdate;
    }

    public String getEmpprimaryphone() {
        return empprimaryphone;
    }

    public void setEmpprimaryphone(String empprimaryphone) {
        this.empprimaryphone = empprimaryphone;
    }

    public String getEmpprogram() {
        return empprogram;
    }

    public void setEmpprogram(String empprogram) {
        this.empprogram = empprogram;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getCanzip() {
        return canzip;
    }

    public void setCanzip(String canzip) {
        this.canzip = canzip;
    }

    public String getCurrentlocation() {
        return Currentlocation;
    }

    public void setCurrentlocation(String currentlocation) {
        Currentlocation = currentlocation;
    }

    public String getCanfirstname() {
        return canfirstname;
    }

    public void setCanfirstname(String canfirstname) {
        this.canfirstname = canfirstname;
    }

    public String getCurrentdeptid() {
        return currentdeptid;
    }

    public void setCurrentdeptid(String currentdeptid) {
        this.currentdeptid = currentdeptid;
    }

    public String getEmpcurrency() {
        return empcurrency;
    }

    public void setEmpcurrency(String empcurrency) {
        this.empcurrency = empcurrency;
    }

    public String getCanstate() {
        return canstate;
    }

    public void setCanstate(String canstate) {
        this.canstate = canstate;
    }

    public String getCanlastname() {
        return canlastname;
    }

    public void setCanlastname(String canlastname) {
        this.canlastname = canlastname;
    }

    public String getEmpworkphone() {
        return empworkphone;
    }

    public void setEmpworkphone(String empworkphone) {
        this.empworkphone = empworkphone;
    }

    public String getCurrentsupid() {
        return currentsupid;
    }

    public void setCurrentsupid(String currentsupid) {
        this.currentsupid = currentsupid;
    }

    public String getEmpotheremail() {
        return empotheremail;
    }

    public void setEmpotheremail(String empotheremail) {
        this.empotheremail = empotheremail;
    }

    public String getEmpdate() {
        return empdate;
    }

    public void setEmpdate(String empdate) {
        this.empdate = empdate;
    }

    public String getEmpstate() {
        return empstate;
    }

    public void setEmpstate(String empstate) {
        this.empstate = empstate;
    }

    public String getCurrentjobtitle() {
        return currentjobtitle;
    }

    public void setCurrentjobtitle(String currentjobtitle) {
        this.currentjobtitle = currentjobtitle;
    }

    public String getCancity() {
        return cancity;
    }

    public void setCancity(String cancity) {
        this.cancity = cancity;
    }

    public String getEmpemail() {
        return empemail;
    }

    public void setEmpemail(String empemail) {
        this.empemail = empemail;
    }

    public String getEmppenddate() {
        return emppenddate;
    }

    public void setEmppenddate(String emppenddate) {
        this.emppenddate = emppenddate;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setCurrentpaystr(jsonObject.getString("currentpaystr"));
            setCanaddress(jsonObject.getString("canaddress"));
            setEmpbonus(jsonObject.getString("empbonus"));
            setEmppstartdate(jsonObject.getString("emppstartdate"));
            setEmpprimaryphone(jsonObject.getString("empprimaryphone"));
            setEmpprogram(jsonObject.getString("empprogram"));
            setEmployeeid(jsonObject.getString("employeeid"));
            setCanzip(jsonObject.getString("canzip"));
            setCurrentlocation(jsonObject.getString("Currentlocation"));
            setCanfirstname(jsonObject.getString("canfirstname"));
            setCurrentdeptid(jsonObject.getString("currentdeptid"));
            setEmpcurrency(jsonObject.getString("empcurrency"));
            setCanstate(jsonObject.getString("canstate"));
            setCanlastname(jsonObject.getString("canlastname"));
            setEmpworkphone(jsonObject.getString("empworkphone"));
            setPaytype(jsonObject.getInt("paytype"));
            setCurrentsupid(jsonObject.getString("currentsupid"));
            setEmpotheremail(jsonObject.getString("empotheremail"));
            setJid(jsonObject.getInt("jid"));
            setEmpdate(jsonObject.getString("empdate"));
            setEmpstate(jsonObject.getString("empstate"));
            setCurrentjobtitle(jsonObject.getString("currentjobtitle"));
            setCancity(jsonObject.getString("cancity"));
            setEmpemail(jsonObject.getString("empemail"));
            setEmppenddate(jsonObject.getString("emppenddate"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
