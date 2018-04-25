package com.anke.yingxiang.domain.anke.msg;

import java.util.List;

public class MessagesModel {
    List<MessageTargetMappings> MessageTargetMappings;
    int UnReadMessageNumber;
    int TotalPages;
    int TotalRows;
    int PageSize;

    public MessagesModel() {
    }

    public List<com.anke.yingxiang.domain.anke.msg.MessageTargetMappings> getMessageTargetMappings() {
        return MessageTargetMappings;
    }

    public void setMessageTargetMappings(List<com.anke.yingxiang.domain.anke.msg.MessageTargetMappings> messageTargetMappings) {
        MessageTargetMappings = messageTargetMappings;
    }

    public int getUnReadMessageNumber() {
        return UnReadMessageNumber;
    }

    public void setUnReadMessageNumber(int unReadMessageNumber) {
        UnReadMessageNumber = unReadMessageNumber;
    }

    public int getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(int totalPages) {
        TotalPages = totalPages;
    }

    public int getTotalRows() {
        return TotalRows;
    }

    public void setTotalRows(int totalRows) {
        TotalRows = totalRows;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }
}
