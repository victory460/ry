package com.anke.yingxiang.domain.anke;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */
public class FeedbackRecordPostModel {

    private String FeedbackTypeId;
    private String ProductId;
    private int CurrentPageNumber = 1;
    private int PageSize = 10000;
    private String SortDirection = "DESC";
    private String SortExpression = "ProductName";

    public String getFeedbackTypeId() {
        return FeedbackTypeId;
    }

    public void setFeedbackTypeId(String feedbackTypeId) {
        FeedbackTypeId = feedbackTypeId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public int getCurrentPageNumber() {
        return CurrentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        CurrentPageNumber = currentPageNumber;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public String getSortDirection() {
        return SortDirection;
    }

    public void setSortDirection(String sortDirection) {
        SortDirection = sortDirection;
    }

    public String getSortExpression() {
        return SortExpression;
    }

    public void setSortExpression(String sortExpression) {
        SortExpression = sortExpression;
    }
}