package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class RecruiterModel {
    int uid,cid,role,viewallcq;
    String avatar,handle,status,firstname,lastname,recruiter_id,currency,userName,email;
    boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public int getViewallcq() {
        return viewallcq;
    }

    public void setViewallcq(int viewallcq) {
        this.viewallcq = viewallcq;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(String recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public void initModel(JSONObject jsonObject){
        try {
            setUid(jsonObject.getInt("uid"));
            setAvatar(jsonObject.getString("Avatar"));
            setHandle(jsonObject.getString("handle"));
            setUserName(jsonObject.getString("userName"));
        }catch (Exception e){
            Log.d("Exception == ", e.toString());
        }
    }
}
