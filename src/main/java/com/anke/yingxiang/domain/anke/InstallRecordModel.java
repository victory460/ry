package com.anke.yingxiang.domain.anke;

import java.util.List;

public class InstallRecordModel {

    private InstallModel InstallationRecord;
    private InstallModel InstallationRecordWithReport;
    private List<InstallModel> InstallationRecords;

    public InstallModel getInstallationRecord() {
        return InstallationRecord;
    }

    public void setInstallationRecord(InstallModel installationRecord) {
        InstallationRecord = installationRecord;
    }

    public InstallModel getInstallationRecordWithReport() {
        return InstallationRecordWithReport;
    }

    public void setInstallationRecordWithReport(InstallModel installationRecordWithReport) {
        InstallationRecordWithReport = installationRecordWithReport;
    }

    public List<InstallModel> getInstallationRecords() {
        return InstallationRecords;
    }

    public void setInstallationRecords(List<InstallModel> installationRecords) {
        InstallationRecords = installationRecords;
    }
}