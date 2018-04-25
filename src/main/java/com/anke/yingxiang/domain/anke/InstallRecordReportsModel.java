package com.anke.yingxiang.domain.anke;


public class InstallRecordReportsModel {

    private String Id;
    private String InstallationRecordId;
    private String Path;
    private String DateUpdated;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getInstallationRecordId() {
        return InstallationRecordId;
    }

    public void setInstallationRecordId(String installationRecordId) {
        InstallationRecordId = installationRecordId;
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
