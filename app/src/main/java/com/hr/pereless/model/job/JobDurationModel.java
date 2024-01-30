package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class JobDurationModel {
    String ReferenceName,name,order,type,value;

    public String getReferenceName() {
        return ReferenceName;
    }

    public void setReferenceName(String referenceName) {
        ReferenceName = referenceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void initModel(JSONObject jsonObject) {
        try {
            setReferenceName(jsonObject.getString("ReferenceName"));
            setName(jsonObject.getString("name"));
            setOrder(jsonObject.getString("order"));
            setType(jsonObject.getString("type"));
            setValue(jsonObject.getString("value"));

        }catch (Exception e){
            Log.d("Exception ===" , e.toString());
        }
    }
}
