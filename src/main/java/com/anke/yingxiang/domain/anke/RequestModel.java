package com.anke.yingxiang.domain.anke;


public class RequestModel {

    private int CurrentPageNumber;
    private int PageSize;
    private String SortExpression;
    private String SortDirection;

    public RequestModel() {
    }

    public RequestModel(int currentPageNumber, int pageSize, String sortExpression, String sortDirection) {
        CurrentPageNumber = currentPageNumber;
        PageSize = pageSize;
        SortExpression = sortExpression;
        SortDirection = sortDirection;
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

    public String getSortExpression() {
        return SortExpression;
    }

    public void setSortExpression(String sortExpression) {
        SortExpression = sortExpression;
    }

    public String getSortDirection() {
        return SortDirection;
    }

    public void setSortDirection(String sortDirection) {
        SortDirection = sortDirection;
    }
}