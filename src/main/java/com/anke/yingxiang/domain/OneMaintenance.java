package com.anke.yingxiang.domain;

import com.anke.yingxiang.domain.anke.FixRecordReportsModel;

import java.util.List;

public class OneMaintenance {
    private String id; // 维修id
    private String feedbackRecordId;
    private String submitter; // 维修记录创建人
    private String submitterId; //
    private String type; // 维修类型
    private String typeId;
    private String maintenanceDate; // 维修日期
    private String description;
    private boolean closed;
    private List<FixRecordReportsModel> fixRecordReportsModels;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeedbackRecordId() {
        return feedbackRecordId;
    }

    public void setFeedbackRecordId(String feedbackRecordId) {
        this.feedbackRecordId = feedbackRecordId;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(String maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FixRecordReportsModel> getFixRecordReportsModels() {
        return fixRecordReportsModels;
    }

    public void setFixRecordReportsModels(List<FixRecordReportsModel> fixRecordReportsModels) {
        this.fixRecordReportsModels = fixRecordReportsModels;
    }
}
