package com.anke.yingxiang.domain.anke;

import java.io.Serializable;

public class FixRecordReportsModel implements Serializable {

    private static final long serialVersionUID = -7059210934200464481L;

    private String Id;
    private String MaintenanceRecordId;
    private String Path;
    private String DateUpdated;

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

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getDateUpdated() {
        return DateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        DateUpdated = dateUpdated;
    }
}
