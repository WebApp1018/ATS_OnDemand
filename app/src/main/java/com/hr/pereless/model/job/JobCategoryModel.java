package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class JobCategoryModel {
    String AFlag, cat_id,catname,catname_CHI,catname_DEU,catname_FRA,catname_SPA,catname_ZHO,dfcat_id,major_id;

    public String getAFlag() {
        return AFlag;
    }

    public void setAFlag(String AFlag) {
        this.AFlag = AFlag;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCatname_CHI() {
        return catname_CHI;
    }

    public void setCatname_CHI(String catname_CHI) {
        this.catname_CHI = catname_CHI;
    }

    public String getCatname_DEU() {
        return catname_DEU;
    }

    public void setCatname_DEU(String catname_DEU) {
        this.catname_DEU = catname_DEU;
    }

    public String getCatname_FRA() {
        return catname_FRA;
    }

    public void setCatname_FRA(String catname_FRA) {
        this.catname_FRA = catname_FRA;
    }

    public String getCatname_SPA() {
        return catname_SPA;
    }

    public void setCatname_SPA(String catname_SPA) {
        this.catname_SPA = catname_SPA;
    }

    public String getCatname_ZHO() {
        return catname_ZHO;
    }

    public void setCatname_ZHO(String catname_ZHO) {
        this.catname_ZHO = catname_ZHO;
    }

    public String getDfcat_id() {
        return dfcat_id;
    }

    public void setDfcat_id(String dfcat_id) {
        this.dfcat_id = dfcat_id;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public void initModel(JSONObject jsonObject) {
        try {
            setAFlag(jsonObject.getString("AFlag"));
            setCat_id(jsonObject.getString("cat_id"));
            setCatname(jsonObject.getString("catname"));
            setCatname_CHI(jsonObject.getString("catname_CHI"));
            setCatname_DEU(jsonObject.getString("catname_DEU"));
            setCatname_FRA(jsonObject.getString("catname_FRA"));
            setCatname_SPA(jsonObject.getString("catname_SPA"));
            setCatname_ZHO(jsonObject.getString("catname_ZHO"));
            setDfcat_id(jsonObject.getString("dfcat_id"));
            setMajor_id(jsonObject.getString("dfcat_id"));
        }catch (Exception e){
            Log.d("Exception ===" , e.toString());
        }
    }
}
