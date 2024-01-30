package com.hr.pereless.model.candidate;

import android.util.Log;

import org.json.JSONObject;

public class CommunicationModel {
    String date, CanName,job_title,recruiter_id,uname,rname,flowname,comment;
    int comment_id,canrank,UID,showcomment,viewallcq,commenttype,jid;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCanName() {
        return CanName;
    }

    public void setCanName(String canName) {
        CanName = canName;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(String recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getCanrank() {
        return canrank;
    }

    public void setCanrank(int canrank) {
        this.canrank = canrank;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getShowcomment() {
        return showcomment;
    }

    public void setShowcomment(int showcomment) {
        this.showcomment = showcomment;
    }

    public int getViewallcq() {
        return viewallcq;
    }

    public void setViewallcq(int viewallcq) {
        this.viewallcq = viewallcq;
    }

    public int getCommenttype() {
        return commenttype;
    }

    public void setCommenttype(int commenttype) {
        this.commenttype = commenttype;
    }

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setDate(jsonObject.getString("date"));
            setCanName(jsonObject.getString("CanName"));
            setJob_title(jsonObject.getString("job_title"));
            setComment_id(jsonObject.getInt("comment_id"));
            setCanrank(jsonObject.getInt("canrank"));

            setRecruiter_id(jsonObject.getString("recruiter_id"));
            setUname(jsonObject.getString("uname"));
            setRname(jsonObject.getString("rname"));
            setFlowname(jsonObject.getString("flowname"));
            setShowcomment(jsonObject.getInt("showcomment"));
            setComment(jsonObject.getString("comment"));
            setViewallcq(jsonObject.getInt("viewallcq"));
            setCommenttype(jsonObject.getInt("commenttype"));
            setUID(jsonObject.getInt("UID"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
