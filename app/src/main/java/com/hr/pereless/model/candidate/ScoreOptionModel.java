package com.hr.pereless.model.candidate;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScoreOptionModel {
    String OptionName,aflag,feedback,psSCOID,enterdate,note;
    int CID,fkSCID,score;

    public String getOptionName() {
        return OptionName;
    }

    public void setOptionName(String optionName) {
        OptionName = optionName;
    }

    public String getAflag() {
        return aflag;
    }

    public void setAflag(String aflag) {
        this.aflag = aflag;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getPsSCOID() {
        return psSCOID;
    }

    public void setPsSCOID(String psSCOID) {
        this.psSCOID = psSCOID;
    }

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getFkSCID() {
        return fkSCID;
    }

    public void setFkSCID(int fkSCID) {
        this.fkSCID = fkSCID;
    }

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

    public void initModel(JSONObject jsonObject){
        try{
            setOptionName(jsonObject.getString("OptionName"));
            if(jsonObject.has("fkSCID"))
                setFkSCID(jsonObject.getInt("fkSCID"));
           // setAflag(jsonObject.getString("aflag"));
            //setCID(jsonObject.getInt("CID"));
           // setFeedback(jsonObject.getString("feedback"));
            setPsSCOID(jsonObject.getString("psSCOID"));
           // setEnterdate(jsonObject.getString("enterdate"));
            if(jsonObject.has("score"))
                setScore(jsonObject.getInt("score"));
            if(jsonObject.has("note"))
                setNote(jsonObject.getString("note"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
