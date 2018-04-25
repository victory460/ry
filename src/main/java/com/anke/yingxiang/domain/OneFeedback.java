package com.anke.yingxiang.domain;

import com.anke.yingxiang.domain.anke.FeedbackRecordReportsModel;

import java.util.List;

/**
 * Created by qingxiangzhang on 2017/12/7.
 */
public class OneFeedback {
    private String id;
    private String title; // 问题名称
    private String description; // 备注信息
    private String type; // 问题类型
    private String typeId;
    private String productName; // 设备名称
    private String productId;
    private String submiterName; // 问题提交人
    private String submiterId;
    private String principleName; // 负责人
    private String principleId;
    private String feedbackDate; // 反馈时间
    private List<FeedbackRecordReportsModel> feedbackRecordReportsModels;
    private boolean reOpen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubmiterName() {
        return submiterName;
    }

    public void setSubmiterName(String submiterName) {
        this.submiterName = submiterName;
    }

    public String getPrincipleName() {
        return principleName;
    }

    public void setPrincipleName(String principleName) {
        this.principleName = principleName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSubmiterId() {
        return submiterId;
    }

    public void setSubmiterId(String submiterId) {
        this.submiterId = submiterId;
    }

    public String getPrincipleId() {
        return principleId;
    }

    public void setPrincipleId(String principleId) {
        this.principleId = principleId;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public List<FeedbackRecordReportsModel> getFeedbackRecordReportsModels() {
        return feedbackRecordReportsModels;
    }

    public void setFeedbackRecordReportsModels(List<FeedbackRecordReportsModel> feedbackRecordReportsModels) {
        this.feedbackRecordReportsModels = feedbackRecordReportsModels;
    }

    public boolean isReOpen() {
        return reOpen;
    }

    public void setReOpen(boolean reOpen) {
        this.reOpen = reOpen;
    }
}
