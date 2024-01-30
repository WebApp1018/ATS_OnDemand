package com.hr.pereless.model.candidate;

import android.util.Log;

import org.json.JSONObject;

public class RecruitflowModel {
    int uniqueID;
    String recruitFlowName,recruitFlowID,color,postoffice,flowtype;
    boolean RFHMD,RFEEO,HMView,RFcommentlog,RFDocument,RFApplication,RFQuickNote,RFonboarding,RFbackground,RFInterview,RFSendemail;


    public String getFlowtype() {
        return flowtype;
    }

    public void setFlowtype(String flowtype) {
        this.flowtype = flowtype;
    }

    public String getPostoffice() {
        return postoffice;
    }

    public void setPostoffice(String postoffice) {
        this.postoffice = postoffice;
    }

    public boolean isRFHMD() {
        return RFHMD;
    }

    public void setRFHMD(boolean RFHMD) {
        this.RFHMD = RFHMD;
    }

    public boolean isRFEEO() {
        return RFEEO;
    }

    public void setRFEEO(boolean RFEEO) {
        this.RFEEO = RFEEO;
    }

    public boolean isHMView() {
        return HMView;
    }

    public void setHMView(boolean HMView) {
        this.HMView = HMView;
    }

    public boolean isRFcommentlog() {
        return RFcommentlog;
    }

    public void setRFcommentlog(boolean RFcommentlog) {
        this.RFcommentlog = RFcommentlog;
    }

    public boolean isRFDocument() {
        return RFDocument;
    }

    public void setRFDocument(boolean RFDocument) {
        this.RFDocument = RFDocument;
    }

    public boolean isRFApplication() {
        return RFApplication;
    }

    public void setRFApplication(boolean RFApplication) {
        this.RFApplication = RFApplication;
    }

    public boolean isRFQuickNote() {
        return RFQuickNote;
    }

    public void setRFQuickNote(boolean RFQuickNote) {
        this.RFQuickNote = RFQuickNote;
    }

    public boolean isRFonboarding() {
        return RFonboarding;
    }

    public void setRFonboarding(boolean RFonboarding) {
        this.RFonboarding = RFonboarding;
    }

    public boolean isRFbackground() {
        return RFbackground;
    }

    public void setRFbackground(boolean RFbackground) {
        this.RFbackground = RFbackground;
    }

    public boolean isRFInterview() {
        return RFInterview;
    }

    public void setRFInterview(boolean RFInterview) {
        this.RFInterview = RFInterview;
    }

    public boolean isRFSendemail() {
        return RFSendemail;
    }

    public void setRFSendemail(boolean RFSendemail) {
        this.RFSendemail = RFSendemail;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRecruitFlowID() {
        return recruitFlowID;
    }

    public void setRecruitFlowID(String recruitFlowID) {
        this.recruitFlowID = recruitFlowID;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getRecruitFlowName() {
        return recruitFlowName;
    }

    public void setRecruitFlowName(String recruitFlowName) {
        this.recruitFlowName = recruitFlowName;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setRecruitFlowName(jsonObject.getString("recruitFlowName"));
            setUniqueID(jsonObject.getInt("uniqueID"));
            setRecruitFlowID(jsonObject.getString("recruitFlowID"));
            setColor(jsonObject.getString("color"));
            setRFHMD(jsonObject.getBoolean("RFHMD"));
            setRFEEO(jsonObject.getBoolean("RFEEO"));
            setHMView(jsonObject.getBoolean("HMView"));
            setRFcommentlog(jsonObject.getBoolean("RFcommentlog"));
            setRFDocument(jsonObject.getBoolean("RFDocument"));
            setPostoffice(jsonObject.getString("postoffice"));
            setRFApplication(jsonObject.getBoolean("RFApplication"));
            setRFQuickNote(jsonObject.getBoolean("RFQuickNote"));
            setRFonboarding(jsonObject.getBoolean("RFonboarding"));
            setFlowtype(jsonObject.getString("flowtype"));
            setRFInterview(jsonObject.getBoolean("RFInterview"));
            setRFSendemail(jsonObject.getBoolean("RFSendemail"));
            setRFbackground(jsonObject.getBoolean("RFbackground"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
