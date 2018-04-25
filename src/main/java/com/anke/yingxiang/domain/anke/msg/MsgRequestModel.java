package com.anke.yingxiang.domain.anke.msg;

public class MsgRequestModel {
    public String MessageId;
    public String ReceiverId;
    public boolean IsRead;

    public MsgRequestModel() {
    }

    public String getMessageId() {
        return MessageId;
    }

    public void setMessageId(String messageId) {
        MessageId = messageId;
    }

    public String getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(String receiverId) {
        ReceiverId = receiverId;
    }

    public boolean isRead() {
        return IsRead;
    }

    public void setRead(boolean read) {
        IsRead = read;
    }
}
