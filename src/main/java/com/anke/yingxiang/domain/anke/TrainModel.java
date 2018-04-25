package com.anke.yingxiang.domain.anke;


import java.util.List;

public class TrainModel{

    private String Id;
    private String TrainingRecordId;
    private String ProductId;
    private String SaleId;
    private String Title;
    private String Description;
    private String Trainer;
    private String TrainingBeginDate;
    private String TrainingEndDate;
    private List<TrainRecordReportsModel> TrainingRecordReports;          // String shoule be -> object
    private SaleModel Sale;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTrainingRecordId() {
        return TrainingRecordId;
    }

    public void setTrainingRecordId(String trainingRecordId) {
        TrainingRecordId = trainingRecordId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getSaleId() {
        return SaleId;
    }

    public void setSaleId(String saleId) {
        SaleId = saleId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTrainer() {
        return Trainer;
    }

    public void setTrainer(String trainer) {
        Trainer = trainer;
    }

    public String getTrainingBeginDate() {
        return TrainingBeginDate;
    }

    public void setTrainingBeginDate(String trainingBeginDate) {
        TrainingBeginDate = trainingBeginDate;
    }

    public String getTrainingEndDate() {
        return TrainingEndDate;
    }

    public void setTrainingEndDate(String trainingEndDate) {
        TrainingEndDate = trainingEndDate;
    }

    public List<TrainRecordReportsModel> getTrainingRecordReports() {
        return TrainingRecordReports;
    }

    public void setTrainingRecordReports(List<TrainRecordReportsModel> trainingRecordReports) {
        TrainingRecordReports = trainingRecordReports;
    }

    public SaleModel getSale() {
        return Sale;
    }

    public void setSale(SaleModel sale) {
        Sale = sale;
    }
}