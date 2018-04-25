package com.anke.yingxiang.domain.anke;

import java.util.List;

public class FixRecordModel {

    private FixModel MaintenanceRecord;
    private List<FixModel> MaintenanceRecords;

    public FixModel getMaintenanceRecord() {
        return MaintenanceRecord;
    }

    public void setMaintenanceRecord(FixModel maintenanceRecord) {
        MaintenanceRecord = maintenanceRecord;
    }

    public List<FixModel> getMaintenanceRecords() {
        return MaintenanceRecords;
    }

    public void setMaintenanceRecords(List<FixModel> maintenanceRecords) {
        MaintenanceRecords = maintenanceRecords;
    }
}