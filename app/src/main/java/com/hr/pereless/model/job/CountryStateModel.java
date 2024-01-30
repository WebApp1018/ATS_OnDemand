package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class CountryStateModel {
    String iso,name;

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void initModel(JSONObject jsonObject){
        try {
            setIso(jsonObject.getString("iso"));
            setName(jsonObject.getString("name"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
