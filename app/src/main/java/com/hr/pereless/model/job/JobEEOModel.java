package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class JobEEOModel {
    String category,eeoc_cat_id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEeoc_cat_id() {
        return eeoc_cat_id;
    }

    public void setEeoc_cat_id(String eeoc_cat_id) {
        this.eeoc_cat_id = eeoc_cat_id;
    }
    public void initModel(JSONObject jsonObject) {
        try {
            setEeoc_cat_id(jsonObject.getString("eeoc_cat_id"));
            setCategory(jsonObject.getString("category"));

        }catch (Exception e){
            Log.d("Exception ===" , e.toString());
        }
    }
}
