package com.hr.pereless.fragment.candidate;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScorecardModel {
    ArrayList<SectionModel> sectionModels = new ArrayList<>();
    String SGID="",GroupDescription,status,GroupName,enterdate,global,note;
    int CID,score;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSGID() {
        return SGID;
    }

    public void setSGID(String SGID) {
        this.SGID = SGID;
    }

    public String getGroupDescription() {
        return GroupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        GroupDescription = groupDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }

    public String getGlobal() {
        return global;
    }

    public void setGlobal(String global) {
        this.global = global;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public ArrayList<SectionModel> getSectionModels() {
        return sectionModels;
    }

    public void setSectionModels(ArrayList<SectionModel> sectionModels) {
        this.sectionModels = sectionModels;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setSGID(jsonObject.getString("SGID"));
            setGroupDescription(jsonObject.getString("GroupDescription"));
            setStatus(jsonObject.getString("status"));
            setCID(jsonObject.getInt("CID"));
            setGroupName(jsonObject.getString("GroupName"));
            setEnterdate(jsonObject.getString("enterdate"));
            setGlobal(jsonObject.getString("global"));
            JSONArray jsonArray = jsonObject.getJSONArray("section");
            sectionModels.clear();
            for(int i =0;i<jsonArray.length();i++){
                JSONObject sectionObject = jsonArray.getJSONObject(i);
                SectionModel sectionModel = new SectionModel();
                sectionModel.initModel(sectionObject);
                sectionModels.add(sectionModel);
            }

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
    public void initDetailModel(JSONObject jsonObject){
        try{
            setSGID(jsonObject.getString("SGID"));
            setGroupDescription(jsonObject.getString("GroupDescription"));
            if(jsonObject.getString("score").length()>0)
                setScore(jsonObject.getInt("score"));
            setNote(jsonObject.getString("note"));
            JSONArray jsonArray = jsonObject.getJSONArray("sections");
            sectionModels.clear();
            for(int i =0;i<jsonArray.length();i++){
                JSONObject sectionObject = jsonArray.getJSONObject(i);
                SectionModel sectionModel = new SectionModel();
                sectionModel.initDetailModel(sectionObject);
                sectionModels.add(sectionModel);
            }

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
