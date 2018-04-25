package com.anke.yingxiang.domain.anke.msg;

public class MessageTargetMappings{

    boolean IsRead;
    Message Message;

    public MessageTargetMappings() {
    }

    public boolean isRead() {
        return IsRead;
    }

    public void setRead(boolean read) {
        IsRead = read;
    }

    public com.anke.yingxiang.domain.anke.msg.Message getMessage() {
        return Message;
    }

    public void setMessage(com.anke.yingxiang.domain.anke.msg.Message message) {
        Message = message;
    }
}

