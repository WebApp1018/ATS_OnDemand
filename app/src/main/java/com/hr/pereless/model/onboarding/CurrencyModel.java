package com.hr.pereless.model.onboarding;

import android.util.Log;

import org.json.JSONObject;

public class CurrencyModel {
    int Currencyorder,CurrencyID;
    String CurrencyISO,CurrencyName,CurrenyEx,CurrencyLocal;

    public int getCurrencyorder() {
        return Currencyorder;
    }

    public void setCurrencyorder(int currencyorder) {
        Currencyorder = currencyorder;
    }

    public int getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(int currencyID) {
        CurrencyID = currencyID;
    }

    public String getCurrencyISO() {
        return CurrencyISO;
    }

    public void setCurrencyISO(String currencyISO) {
        CurrencyISO = currencyISO;
    }

    public String getCurrencyName() {
        return CurrencyName;
    }

    public void setCurrencyName(String currencyName) {
        CurrencyName = currencyName;
    }

    public String getCurrenyEx() {
        return CurrenyEx;
    }

    public void setCurrenyEx(String currenyEx) {
        CurrenyEx = currenyEx;
    }

    public String getCurrencyLocal() {
        return CurrencyLocal;
    }

    public void setCurrencyLocal(String currencyLocal) {
        CurrencyLocal = currencyLocal;
    }
    public void initModel(JSONObject jsonObject){
        try{
            setCurrencyorder(jsonObject.getInt("Currencyorder"));
            setCurrencyISO(jsonObject.getString("CurrencyISO"));
            setCurrencyName(jsonObject.getString("CurrencyName"));
            setCurrenyEx(jsonObject.getString("CurrenyEx"));
            setCurrencyID(jsonObject.getInt("CurrencyID"));
            setCurrencyLocal(jsonObject.getString("CurrencyLocal"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
