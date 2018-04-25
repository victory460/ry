package com.anke.yingxiang.domain.anke;

import java.io.Serializable;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */
public class FeedbackRecordReportsModel implements Serializable {

    private static final long serialVersionUID = -7059210544609464481L;

    private String Id;
    private String FeedbackRecordId;
    private String Path;
    private String DateUpdated;

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