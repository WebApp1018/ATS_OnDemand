package com.hr.pereless.model.setting;

import android.util.Log;

import com.hr.pereless.model.home.ActivityChatModel;
import com.hr.pereless.model.home.StatusModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserModel {
    int id,recruiterID = 0,uid,status,cid,role,timezone;
    StatusModel statusModel = new StatusModel();
    ActivityChatModel activityChatModel = new ActivityChatModel();
    String lastname,lang,Avatar,firstname,viewallcq,userdateformat,currency,handle,tiemzonestr,userName,email,phone;
    HashMap<Integer, ArrayList<Integer>> chartFlwowData = new HashMap<>();

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        setId(uid);
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getViewallcq() {
        return viewallcq;
    }

    public void setViewallcq(String viewallcq) {
        this.viewallcq = viewallcq;
    }

    public String getUserdateformat() {
        return userdateformat;
    }

    public void setUserdateformat(String userdateformat) {
        this.userdateformat = userdateformat;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getTiemzonestr() {
        return tiemzonestr;
    }

    public void setTiemzonestr(String tiemzonestr) {
        this.tiemzonestr = tiemzonestr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRecruiterID() {
        return recruiterID;
    }

    public void setRecruiterID(int recruiterID) {
        this.recruiterID = recruiterID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public ActivityChatModel getActivityChatModel() {
        return activityChatModel;
    }

    public HashMap<Integer, ArrayList<Integer>> getChartFlwowData() {
        return chartFlwowData;
    }

    public void setChartFlwowData(HashMap<Integer, ArrayList<Integer>> chartFlwowData) {
        this.chartFlwowData = chartFlwowData;
    }

    public void setActivityChatModel(ActivityChatModel activityChatModel) {
        this.activityChatModel = activityChatModel;
    }

    public StatusModel getStatusModel() {
        return statusModel;
    }

    public void setStatusModel(StatusModel statusModel) {
        this.statusModel = statusModel;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setLastname(jsonObject.getString("lastname"));
            setRecruiterID(jsonObject.getInt("recruiter_id"));
            setUid(jsonObject.getInt("uid"));
            setStatus(jsonObject.getInt("status"));
            setCid(jsonObject.getInt("cid"));
            setRole(jsonObject.getInt("role"));
            setLang(jsonObject.getString("lang"));
            setAvatar(jsonObject.getString("Avatar"));
            setFirstname(jsonObject.getString("firstname"));
            setViewallcq(jsonObject.getString("viewallcq"));
            setUserdateformat(jsonObject.getString("userdateformat"));
            setCurrency(jsonObject.getString("currency"));
            setTiemzonestr(jsonObject.getString("tiemzonestr"));
            setHandle(jsonObject.getString("handle"));
            setTimezone(jsonObject.getInt("timezone"));
            setUserName(jsonObject.getString("userName"));
            setEmail(jsonObject.getString("email"));
            setPhone(jsonObject.getString("phone"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
