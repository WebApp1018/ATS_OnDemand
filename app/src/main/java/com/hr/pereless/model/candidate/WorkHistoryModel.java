package com.hr.pereless.model.candidate;

import java.util.ArrayList;
import java.util.List;

public class WorkHistoryModel {
    List<PointModel> pointModels = new ArrayList<>();

    public List<PointModel> getPointModels() {
        return pointModels;
    }

    public void setPointModels(List<PointModel> pointModels) {
        this.pointModels = pointModels;
    }
}
