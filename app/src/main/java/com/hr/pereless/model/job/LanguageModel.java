package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class LanguageModel {
    String LangID,LangISO2,LangISO3,Langstatus,LanguageLocal,LanguageName;

    public String getLangID() {
        return LangID;
    }

    public void setLangID(String langID) {
        LangID = langID;
    }

    public String getLangISO2() {
        return LangISO2;
    }

    public void setLangISO2(String langISO2) {
        LangISO2 = langISO2;
    }

    public String getLangISO3() {
        return LangISO3;
    }

    public void setLangISO3(String langISO3) {
        LangISO3 = langISO3;
    }

    public String getLangstatus() {
        return Langstatus;
    }

    public void setLangstatus(String langstatus) {
        Langstatus = langstatus;
    }

    public String getLanguageLocal() {
        return LanguageLocal;
    }

    public void setLanguageLocal(String languageLocal) {
        LanguageLocal = languageLocal;
    }

    public String getLanguageName() {
        return LanguageName;
    }

    public void setLanguageName(String languageName) {
        LanguageName = languageName;
    }

    public void initModel(JSONObject jsonObject) {
        try {
            setLangID(jsonObject.getString("LangID"));
            setLangISO2(jsonObject.getString("LangISO2"));
            setLangISO3(jsonObject.getString("LangISO3"));
            setLangstatus(jsonObject.getString("Langstatus"));
            setLanguageLocal(jsonObject.getString("LanguageLocal"));
            setLanguageName(jsonObject.getString("LanguageName"));


        }catch (Exception e){
            Log.d("Exception ===" , e.toString());
        }
    }
}
