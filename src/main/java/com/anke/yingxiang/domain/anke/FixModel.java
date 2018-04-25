package com.anke.yingxiang.domain.anke;


import java.io.Serializable;
import java.util.List;

public class FixModel implements Serializable {

    private static final long serialVersionUID = -7059214987300464481L;

    private String Id;
    private String MaintenanceRecordId;
    private String FeedbackRecordId;
    private String MaintenanceTypeId;
    private String Description;
    private String MaintenanceDate;
    private boolean Closed;
    private List<FixRecordReportsModel> MaintenanceRecordReports;
    private FixTypeModel MaintenanceType;
    private UserModel Submitter;
    private String Principal;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMaintenanceRecordId() {
        return MaintenanceRecordId;
    }

    public void setMaintenanceRecordId(String maintenanceRecordId) {
        MaintenanceRecordId = maintenanceRecordId;
    }

    public String getFeedbackRecordId() {
        return FeedbackRecordId;
    }

    public void setFeedbackRecordId(String feedbackRecordId) {
        FeedbackRecordId = feedbackRecordId;
    }

    public String getMaintenanceTypeId() {
        return MaintenanceTypeId;
    }

    public void setMaintenanceTypeId(String maintenanceTypeId) {
        MaintenanceTypeId = maintenanceTypeId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMaintenanceDate() {
        return MaintenanceDate;
    }

    public void setMaintenanceDate(String maintenanceDate) {
        MaintenanceDate = maintenanceDate;
    }

    public boolean isClosed() {
        return Closed;
    }

    public void setClosed(boolean closed) {
        Closed = closed;
    }

    public List<FixRecordReportsModel> getMaintenanceRecordReports() {
        return MaintenanceRecordReports;
    }

    public void setMaintenanceRecordReports(List<FixRecordReportsModel> maintenanceRecordReports) {
        MaintenanceRecordReports = maintenanceRecordReports;
    }

    public FixTypeModel getMaintenanceType() {
        return MaintenanceType;
    }

    public void setMaintenanceType(FixTypeModel maintenanceType) {
        MaintenanceType = maintenanceType;
    }

    public UserModel getSubmitter() {
        return Submitter;
    }

    public void setSubmitter(UserModel submitter) {
        Submitter = submitter;
    }

    public String getPrincipal() {
        return Principal;
    }

    public void setPrincipal(String principal) {
        Principal = principal;
    }
}