package com.anke.yingxiang.domain;

/**
 * Created by qingxiangzhang on 2017/12/8.
 */
public class RequestFeedbackModel {
    private String FeedbackRecordId; //新建是UUID， or FeedbackModel.getId
    private String ProductId; // SaleModel.getId 设备ID
    private String FeedbackTypeId; // FeedbackTypeModel.getId
    private String FeedbackDate; // Timestamp(date).toString
    private String PrincipalId; // UserModel.getId  // 新建的反馈，表示提交人的id；查看的反馈，表示负责人的id
    private String Title;
    private String Description;
    private boolean Reopen;

    public String getFeedbackRecordId() {
        return FeedbackRecordId;
    }

    public void setFeedbackRecordId(String feedbackRecordId) {
        FeedbackRecordId = feedbackRecordId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getFeedbackTypeId() {
        return FeedbackTypeId;
    }

    public void setFeedbackTypeId(String feedbackTypeId) {
        FeedbackTypeId = feedbackTypeId;
    }

    public String getFeedbackDate() {
        return FeedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        FeedbackDate = feedbackDate;
    }

    public String getPrincipalId() {
        return PrincipalId;
    }

    public void setPrincipalId(String principalId) {
        PrincipalId = principalId;
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

    public boolean isReopen() {
        return Reopen;
    }

    public void setReopen(boolean reopen) {
        Reopen = reopen;
    }

    @Override
    public String toString() {
        return "RequestFeedbackModel{" +
                "FeedbackRecordId='" + FeedbackRecordId + '\'' +
                ", ProductId='" + ProductId + '\'' +
                ", FeedbackTypeId='" + FeedbackTypeId + '\'' +
                ", FeedbackDate='" + FeedbackDate + '\'' +
                ", PrincipalId='" + PrincipalId + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
