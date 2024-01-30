package com.hr.pereless.fragment.candidate;

import android.util.Log;

import com.hr.pereless.model.candidate.ScoreOptionModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SectionModel {
    int SCID,fkGroupID,status,CID,Scoreorder;
    String ScoreDescription,ScoreGroup,ScoreName,EnterDate,ScoreType;
    ArrayList<ScoreOptionModel>scoreOptionModels = new ArrayList<>();

    public ArrayList<ScoreOptionModel> getScoreOptionModels() {
        return scoreOptionModels;
    }

    public void setScoreOptionModels(ArrayList<ScoreOptionModel> scoreOptionModels) {
        this.scoreOptionModels = scoreOptionModels;
    }

    public int getSCID() {
        return SCID;
    }

    public void setSCID(int SCID) {
        this.SCID = SCID;
    }

    public String getScoreType() {
        return ScoreType;
    }

    public void setScoreType(String scoreType) {
        ScoreType = scoreType;
    }

    public int getFkGroupID() {
        return fkGroupID;
    }

    public void setFkGroupID(int fkGroupID) {
        this.fkGroupID = fkGroupID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getScoreorder() {
        return Scoreorder;
    }

    public void setScoreorder(int scoreorder) {
        Scoreorder = scoreorder;
    }

    public String getScoreDescription() {
        return ScoreDescription;
    }

    public void setScoreDescription(String scoreDescription) {
        ScoreDescription = scoreDescription;
    }

    public String getScoreGroup() {
        return ScoreGroup;
    }

    public void setScoreGroup(String scoreGroup) {
        ScoreGroup = scoreGroup;
    }

    public String getScoreName() {
        return ScoreName;
    }

    public void setScoreName(String scoreName) {
        ScoreName = scoreName;
    }

    public String getEnterDate() {
        return EnterDate;
    }

    public void setEnterDate(String enterDate) {
        EnterDate = enterDate;
    }

    public void initModel(JSONObject jsonObject){
        try{
            setSCID(jsonObject.getInt("SCID"));
            setScoreType(jsonObject.getString("ScoreType"));
            setScoreDescription(jsonObject.getString("ScoreDescription"));
            setCID(jsonObject.getInt("CID"));
            setScoreGroup(jsonObject.getString("ScoreGroup"));
            setScoreName(jsonObject.getString("ScoreName"));
            setEnterDate(jsonObject.getString("EnterDate"));
            JSONArray jsonArray = jsonObject.getJSONArray("option");
            scoreOptionModels.clear();
            for(int i =0;i<jsonArray.length();i++){
                ScoreOptionModel scoreOptionModel = new ScoreOptionModel();
                scoreOptionModel.initModel(jsonArray.getJSONObject(i));
                scoreOptionModels.add(scoreOptionModel);
            }

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
    public void initDetailModel(JSONObject jsonObject){
        try{
            setSCID(jsonObject.getInt("SCID"));
            JSONArray jsonArray = jsonObject.getJSONArray("options");
            scoreOptionModels.clear();
            for(int i =0;i<jsonArray.length();i++){
                ScoreOptionModel scoreOptionModel = new ScoreOptionModel();
                scoreOptionModel.initModel(jsonArray.getJSONObject(i));
                scoreOptionModels.add(scoreOptionModel);
            }

        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
