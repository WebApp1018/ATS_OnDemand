package com.hr.pereless.model.onboarding;

import android.util.Log;

import org.json.JSONObject;

public class DocumentModel {
    String documentExternalName,documentGroupInternalName,documentType,documentGroupExternalName,documentID,documentInternalName;
    int totalPages,status,MaxRows,global;

    public String getDocumentExternalName() {
        return documentExternalName;
    }

    public void setDocumentExternalName(String documentExternalName) {
        this.documentExternalName = documentExternalName;
    }

    public String getDocumentGroupInternalName() {
        return documentGroupInternalName;
    }

    public void setDocumentGroupInternalName(String documentGroupInternalName) {
        this.documentGroupInternalName = documentGroupInternalName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentGroupExternalName() {
        return documentGroupExternalName;
    }

    public void setDocumentGroupExternalName(String documentGroupExternalName) {
        this.documentGroupExternalName = documentGroupExternalName;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getDocumentInternalName() {
        return documentInternalName;
    }

    public void setDocumentInternalName(String documentInternalName) {
        this.documentInternalName = documentInternalName;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMaxRows() {
        return MaxRows;
    }

    public void setMaxRows(int maxRows) {
        MaxRows = maxRows;
    }

    public int getGlobal() {
        return global;
    }

    public void setGlobal(int global) {
        this.global = global;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setDocumentExternalName(jsonObject.getString("documentExternalName"));
            setDocumentGroupInternalName(jsonObject.getString("documentGroupInternalName"));
            setTotalPages(jsonObject.getInt("totalPages"));
            setStatus(jsonObject.getInt("status"));
            setMaxRows(jsonObject.getInt("MaxRows"));
            setDocumentType(jsonObject.getString("documentType"));
            setDocumentGroupExternalName(jsonObject.getString("documentGroupExternalName"));
            setDocumentID(jsonObject.getString("documentID"));
            setGlobal(jsonObject.getInt("global"));
            setDocumentInternalName(jsonObject.getString("documentInternalName"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }

}
