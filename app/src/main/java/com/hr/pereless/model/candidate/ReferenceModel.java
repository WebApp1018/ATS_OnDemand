package com.hr.pereless.model.candidate;

import android.util.Log;

import org.json.JSONObject;

public class ReferenceModel {
    int ReferenceID,jid;
    String toEmaillog,Ref_Addressline,Refhowlong,emailbody,Reference_relationship,ContactMethod_telephone,PersonName,
            PersonOrgName,enterdate,PositionTitle;

    public int getReferenceID() {
        return ReferenceID;
    }

    public void setReferenceID(int referenceID) {
        ReferenceID = referenceID;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public String getToEmaillog() {
        return toEmaillog;
    }

    public void setToEmaillog(String toEmaillog) {
        this.toEmaillog = toEmaillog;
    }

    public String getRef_Addressline() {
        return Ref_Addressline;
    }

    public void setRef_Addressline(String ref_Addressline) {
        Ref_Addressline = ref_Addressline;
    }

    public String getRefhowlong() {
        return Refhowlong;
    }

    public void setRefhowlong(String refhowlong) {
        Refhowlong = refhowlong;
    }

    public String getEmailbody() {
        return emailbody;
    }

    public void setEmailbody(String emailbody) {
        this.emailbody = emailbody;
    }

    public String getReference_relationship() {
        return Reference_relationship;
    }

    public void setReference_relationship(String reference_relationship) {
        Reference_relationship = reference_relationship;
    }

    public String getContactMethod_telephone() {
        return ContactMethod_telephone;
    }

    public void setContactMethod_telephone(String contactMethod_telephone) {
        ContactMethod_telephone = contactMethod_telephone;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getPersonOrgName() {
        return PersonOrgName;
    }

    public void setPersonOrgName(String personOrgName) {
        PersonOrgName = personOrgName;
    }

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }

    public String getPositionTitle() {
        return PositionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        PositionTitle = positionTitle;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setReferenceID(jsonObject.getInt("ReferenceID"));
            setToEmaillog(jsonObject.getString("toEmaillog"));
            setRef_Addressline(jsonObject.getString("Ref_Addressline"));
            setEmailbody(jsonObject.getString("emailbody"));
            setRefhowlong(jsonObject.getString("Refhowlong"));
            setReference_relationship(jsonObject.getString("Reference_relationship"));
            setContactMethod_telephone(jsonObject.getString("ContactMethod_telephone"));
            setJid(jsonObject.getInt("jid"));
            setPersonName(jsonObject.getString("PersonName"));
            setEnterdate(jsonObject.getString("enterdate"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
