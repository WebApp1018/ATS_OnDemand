package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class JobSubcategoryModel {
    String fkmajor_id,sub_id,subcatname,subcatname_CHI,subcatname_DEU,subcatname_FRA,subcatname_SPA,subcatname_ZHO;

    public String getFkmajor_id() {
        return fkmajor_id;
    }

    public void setFkmajor_id(String fkmajor_id) {
        this.fkmajor_id = fkmajor_id;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getSubcatname() {
        return subcatname;
    }

    public void setSubcatname(String subcatname) {
        this.subcatname = subcatname;
    }

    public String getSubcatname_CHI() {
        return subcatname_CHI;
    }

    public void setSubcatname_CHI(String subcatname_CHI) {
        this.subcatname_CHI = subcatname_CHI;
    }

    public String getSubcatname_DEU() {
        return subcatname_DEU;
    }

    public void setSubcatname_DEU(String subcatname_DEU) {
        this.subcatname_DEU = subcatname_DEU;
    }

    public String getSubcatname_FRA() {
        return subcatname_FRA;
    }

    public void setSubcatname_FRA(String subcatname_FRA) {
        this.subcatname_FRA = subcatname_FRA;
    }

    public String getSubcatname_SPA() {
        return subcatname_SPA;
    }

    public void setSubcatname_SPA(String subcatname_SPA) {
        this.subcatname_SPA = subcatname_SPA;
    }

    public String getSubcatname_ZHO() {
        return subcatname_ZHO;
    }

    public void setSubcatname_ZHO(String subcatname_ZHO) {
        this.subcatname_ZHO = subcatname_ZHO;
    }

    public void initModel(JSONObject jsonObject) {
        try {
            setFkmajor_id(jsonObject.getString("fkmajor_id"));
            setSub_id(jsonObject.getString("sub_id"));
            setSubcatname(jsonObject.getString("subcatname"));
            setSubcatname_CHI(jsonObject.getString("catname_CHI"));
            setSubcatname_DEU(jsonObject.getString("catname_DEU"));
            setSubcatname_FRA(jsonObject.getString("catname_FRA"));
            setSubcatname_SPA(jsonObject.getString("catname_SPA"));
            setSubcatname_ZHO(jsonObject.getString("catname_ZHO"));
        }catch (Exception e){
            Log.d("Exception ===" , e.toString());
        }
    }
}
