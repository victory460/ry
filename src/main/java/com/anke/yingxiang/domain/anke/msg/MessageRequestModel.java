package com.anke.yingxiang.domain.anke.msg;

public class MessageRequestModel {
    //    {"PageSize":10,"CurrentPageNumber":1,"TotalPages":0,"OnlyUnread":false,"UserId":"3b2999ac-8e6f-479d-ac2e-c0dc11566e99"}
    public int PageSize;
    public int CurrentPageNumber;
    public int TotalPages;
    public boolean OnlyUnread;
    public String UserId;

    public MessageRequestModel(int pageSize, int currentPageNumber, int totalPages, boolean onlyUnread, String userId) {
        PageSize = pageSize;
        CurrentPageNumber = currentPageNumber;
        TotalPages = totalPages;
        OnlyUnread = onlyUnread;
        UserId = userId;
    }
}
