package com.hr.pereless.model.candidate;

import android.util.Log;

import org.json.JSONObject;

public class QuicknoteModel {
    String hotbookname,qnotes,bgcolor,jconfidentialpost,job_title,notedate,Avatar,firstname,timezone,userName,
            gnlocation,email,qncontactID,qnhotbook,lastname,UID,uname,lang,viewallcq,userdateformat,currency,tiemzonestr
            ,handle,qntoflowid;
    int qnjid,qnprivate,role,NID,qnviewtype,Recruiter_id,CID,RID,qngradenum;

    public String getHotbookname() {
        return hotbookname;
    }

    public void setHotbookname(String hotbookname) {
        this.hotbookname = hotbookname;
    }

    public String getQnotes() {
        return qnotes;
    }

    public void setQnotes(String qnotes) {
        this.qnotes = qnotes;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getJconfidentialpost() {
        return jconfidentialpost;
    }

    public void setJconfidentialpost(String jconfidentialpost) {
        this.jconfidentialpost = jconfidentialpost;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getNotedate() {
        return notedate;
    }

    public void setNotedate(String notedate) {
        this.notedate = notedate;
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

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGnlocation() {
        return gnlocation;
    }

    public void setGnlocation(String gnlocation) {
        this.gnlocation = gnlocation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQncontactID() {
        return qncontactID;
    }

    public void setQncontactID(String qncontactID) {
        this.qncontactID = qncontactID;
    }

    public String getQnhotbook() {
        return qnhotbook;
    }

    public void setQnhotbook(String qnhotbook) {
        this.qnhotbook = qnhotbook;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
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

    public String getTiemzonestr() {
        return tiemzonestr;
    }

    public void setTiemzonestr(String tiemzonestr) {
        this.tiemzonestr = tiemzonestr;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getQntoflowid() {
        return qntoflowid;
    }

    public void setQntoflowid(String qntoflowid) {
        this.qntoflowid = qntoflowid;
    }

    public int getQnjid() {
        return qnjid;
    }

    public void setQnjid(int qnjid) {
        this.qnjid = qnjid;
    }

    public int getQnprivate() {
        return qnprivate;
    }

    public void setQnprivate(int qnprivate) {
        this.qnprivate = qnprivate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getNID() {
        return NID;
    }

    public void setNID(int NID) {
        this.NID = NID;
    }

    public int getQnviewtype() {
        return qnviewtype;
    }

    public void setQnviewtype(int qnviewtype) {
        this.qnviewtype = qnviewtype;
    }

    public int getRecruiter_id() {
        return Recruiter_id;
    }

    public void setRecruiter_id(int recruiter_id) {
        Recruiter_id = recruiter_id;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public int getQngradenum() {
        return qngradenum;
    }

    public void setQngradenum(int qngradenum) {
        this.qngradenum = qngradenum;
    }

    public void initModel(JSONObject jsonObject){
        try{
           setQnotes(jsonObject.getString("qnotes"));
           setQnprivate(jsonObject.getInt("qnprivate"));
           setQnjid(jsonObject.getInt("qnjid"));
           setHotbookname(jsonObject.getString("hotbookname"));
           setBgcolor(jsonObject.getString("bgcolor"));
           setNotedate(jsonObject.getString("notedate"));
           setNID(jsonObject.getInt("NID"));
           setQnhotbook(jsonObject.getString("qnhotbook"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
