package com.hr.pereless.model.job;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BusinessUnitModel {
    String BUpayrollID,MaxRows,address1,address2,city,companyurl ,country,description,logo,name,state,zipcode;
    int id,industry,status,totalPages;
    ArrayList<DepartmentModel>departmentModels = new ArrayList<>();

    public String getBUpayrollID() {
        return BUpayrollID;
    }

    public void setBUpayrollID(String BUpayrollID) {
        this.BUpayrollID = BUpayrollID;
    }

    public String getMaxRows() {
        return MaxRows;
    }

    public void setMaxRows(String maxRows) {
        MaxRows = maxRows;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyurl() {
        return companyurl;
    }

    public void setCompanyurl(String companyurl) {
        this.companyurl = companyurl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndustry() {
        return industry;
    }

    public void setIndustry(int industry) {
        this.industry = industry;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<DepartmentModel> getDepartmentModels() {
        return departmentModels;
    }

    public void setDepartmentModels(ArrayList<DepartmentModel> departmentModels) {
        this.departmentModels = departmentModels;
    }

    public void initModel(JSONObject jsonObject){
        try {
            setId(jsonObject.getInt("id"));
            setIndustry(jsonObject.getInt("industry"));
            setName(jsonObject.getString("name"));
            departmentModels.clear();
            JSONArray jsonArray = jsonObject.getJSONArray("departments");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                DepartmentModel departmentModel = new DepartmentModel();
                departmentModel.initModel(json);
                departmentModels.add(departmentModel);
            }
        }catch (Exception e){
            Log.d("Exception=====" ,e.toString());
        }
    }
}
