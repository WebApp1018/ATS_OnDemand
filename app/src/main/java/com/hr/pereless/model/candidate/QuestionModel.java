package com.hr.pereless.model.candidate;

import android.util.Log;

import org.json.JSONObject;

public class QuestionModel {
    int questionNo,JQ_type,jobQ_id,AnsUUID,RID,JID;
    String job_title,question,answer,enterdate;

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public int getJQ_type() {
        return JQ_type;
    }

    public void setJQ_type(int JQ_type) {
        this.JQ_type = JQ_type;
    }

    public int getJobQ_id() {
        return jobQ_id;
    }

    public void setJobQ_id(int jobQ_id) {
        this.jobQ_id = jobQ_id;
    }

    public int getAnsUUID() {
        return AnsUUID;
    }

    public void setAnsUUID(int ansUUID) {
        AnsUUID = ansUUID;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public int getJID() {
        return JID;
    }

    public void setJID(int JID) {
        this.JID = JID;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(String enterdate) {
        this.enterdate = enterdate;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setQuestionNo(jsonObject.getInt("questionNo"));
            setJQ_type(jsonObject.getInt("JQ_type"));
            setJob_title(jsonObject.getString("job_title"));
            setQuestion(jsonObject.getString("question"));
            setJobQ_id(jsonObject.getInt("jobQ_id"));
            setAnswer(jsonObject.getString("answer"));
            setAnsUUID(jsonObject.getInt("AnsUUID"));
            setRID(jsonObject.getInt("RID"));
            setJID(jsonObject.getInt("JID"));
            setEnterdate(jsonObject.getString("enterdate"));

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
