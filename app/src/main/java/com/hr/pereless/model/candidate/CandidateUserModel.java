package com.hr.pereless.model.candidate;

import android.util.Log;

import org.json.JSONObject;

public class CandidateUserModel {
    String mobile,emails,designation,url,name,image;
    int progress,cid,rid;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setRid(jsonObject.getInt("rid"));
            setCid(jsonObject.getInt("cid"));
            setName(jsonObject.getString("name"));
            setEmails(jsonObject.getString("email"));
            setMobile(jsonObject.getString("mobile"));
            setDesignation(jsonObject.getString("designation"));
            setUrl(jsonObject.getString("url"));
            setImage(jsonObject.getString("image"));
            setProgress(jsonObject.getInt("progress"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
