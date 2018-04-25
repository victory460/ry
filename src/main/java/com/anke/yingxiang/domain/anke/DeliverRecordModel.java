package com.anke.yingxiang.domain.anke;

import java.util.List;

public class DeliverRecordModel {

    private DeliverModel DeliverRecord;

    private DeliverModel DeliverRecordWithReport;

    private List<DeliverModel> DeliverRecords;

    public DeliverModel getDeliverRecord() {
        return DeliverRecord;
    }

    public void setDeliverRecord(DeliverModel deliverRecord) {
        DeliverRecord = deliverRecord;
    }

    public DeliverModel getDeliverRecordWithReport() {
        return DeliverRecordWithReport;
    }

    public void setDeliverRecordWithReport(DeliverModel deliverRecordWithReport) {
        DeliverRecordWithReport = deliverRecordWithReport;
    }

    public List<DeliverModel> getDeliverRecords() {
        return DeliverRecords;
    }

    public void setDeliverRecords(List<DeliverModel> deliverRecords) {
        DeliverRecords = deliverRecords;
    }
}