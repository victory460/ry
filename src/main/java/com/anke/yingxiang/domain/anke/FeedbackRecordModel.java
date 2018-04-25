package com.anke.yingxiang.domain.anke;

import java.util.List;

/**
 * Created by qingxiangzhang on 2017/11/29.
 */
public class FeedbackRecordModel {

    private FeedbackModel FeedbackRecord;
    private FeedbackModel FeedbackRecordWithReport;
    private List<FeedbackModel> FeedbackRecords;

    public FeedbackModel getFeedbackRecord() {
        return FeedbackRecord;
    }

    public void setFeedbackRecord(FeedbackModel feedbackRecord) {
        FeedbackRecord = feedbackRecord;
    }

    public FeedbackModel getFeedbackRecordWithReport() {
        return FeedbackRecordWithReport;
    }

    public void setFeedbackRecordWithReport(FeedbackModel feedbackRecordWithReport) {
        FeedbackRecordWithReport = feedbackRecordWithReport;
    }

    public List<FeedbackModel> getFeedbackRecords() {
        return FeedbackRecords;
    }

    public void setFeedbackRecords(List<FeedbackModel> feedbackRecords) {
        FeedbackRecords = feedbackRecords;
    }
}
