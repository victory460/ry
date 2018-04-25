package com.anke.yingxiang.domain.anke;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */
public class FeedbackModel implements Serializable{


    private static final long serialVersionUID = -7059210544600464481L;

    private String Id;                  // get
    private String FeedbackRecordId;    // post
    private String ProductId;
    private String FeedbackTypeId;
    private String FeedbackDate;
    private UserModel Allocater;
    private UserModel Submitter;
    private UserModel Principal;  // 需要
    private String PrincipalId;
    private String Title;
    private String Description;

    private List<FeedbackRecordReportsModel> FeedbackRecordReports; // upload set null

    private DeviceModel Product; //需要

    private FeedbackTypeModel FeedbackType;  // 需要
    private FeedbackStateModel FeedbackState;
    private boolean Reopen;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

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

    public UserModel getAllocater() {
        return Allocater;
    }

    public void setAllocater(UserModel allocater) {
        Allocater = allocater;
    }

    public UserModel getSubmitter() {
        return Submitter;
    }

    public void setSubmitter(UserModel submitter) {
        Submitter = submitter;
    }

    public UserModel getPrincipal() {
        return Principal;
    }

    public void setPrincipal(UserModel principal) {
        Principal = principal;
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

    public List<FeedbackRecordReportsModel> getFeedbackRecordReports() {
        return FeedbackRecordReports;
    }

    public void setFeedbackRecordReports(List<FeedbackRecordReportsModel> feedbackRecordReports) {
        FeedbackRecordReports = feedbackRecordReports;
    }

    public DeviceModel getProduct() {
        return Product;
    }

    public void setProduct(DeviceModel product) {
        Product = product;
    }

    public FeedbackTypeModel getFeedbackType() {
        return FeedbackType;
    }

    public void setFeedbackType(FeedbackTypeModel feedbackType) {
        FeedbackType = feedbackType;
    }

    public FeedbackStateModel getFeedbackState() {
        return FeedbackState;
    }

    public void setFeedbackState(FeedbackStateModel feedbackState) {
        FeedbackState = feedbackState;
    }

    public boolean isReopen() {
        return Reopen;
    }

    public void setReopen(boolean reopen) {
        Reopen = reopen;
    }

    @Override
    public String toString() {
        return "FeedbackModel{" +
                "Id='" + Id + '\'' +
                ", FeedbackRecordId='" + FeedbackRecordId + '\'' +
                ", ProductId='" + ProductId + '\'' +
                ", FeedbackTypeId='" + FeedbackTypeId + '\'' +
                ", FeedbackDate='" + FeedbackDate + '\'' +
                ", Allocater=" + Allocater +
                ", Submitter=" + Submitter +
                ", Principal=" + Principal +
                ", PrincipalId='" + PrincipalId + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", FeedbackRecordReports=" + FeedbackRecordReports +
                ", Product=" + Product +
                ", FeedbackType=" + FeedbackType +
                ", FeedbackState=" + FeedbackState +
                ", Reopen=" + Reopen +
                '}';
    }
}

