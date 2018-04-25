package com.anke.yingxiang.domain.anke;

import java.util.List;

public class TrainRecordModel {

    private TrainModel TrainingRecord;
    private TrainModel TrainingRecordWithReport;
    private List<TrainModel> TrainingRecords;

    public TrainModel getTrainingRecord() {
        return TrainingRecord;
    }

    public void setTrainingRecord(TrainModel trainingRecord) {
        TrainingRecord = trainingRecord;
    }

    public TrainModel getTrainingRecordWithReport() {
        return TrainingRecordWithReport;
    }

    public void setTrainingRecordWithReport(TrainModel trainingRecordWithReport) {
        TrainingRecordWithReport = trainingRecordWithReport;
    }

    public List<TrainModel> getTrainingRecords() {
        return TrainingRecords;
    }

    public void setTrainingRecords(List<TrainModel> trainingRecords) {
        TrainingRecords = trainingRecords;
    }

}