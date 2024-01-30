package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONObject;

public class DepartmentModel {
    int buid,deptid;
    String departmentName;

    public int getBuid() {
        return buid;
    }

    public void setBuid(int buid) {
        this.buid = buid;
    }

    public int getDeptid() {
        return deptid;
    }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void initModel(JSONObject jsonObject){
        try {
            setBuid(jsonObject.getInt("buid"));
            setDeptid(jsonObject.getInt("deptid"));
            setDepartmentName(jsonObject.getString("departmentName"));
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
