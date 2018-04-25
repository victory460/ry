package com.anke.yingxiang.domain.anke.msg;


public class Message {
    MessageType MessageType;
    Sender Sender;
    String Title;
    String Content;
    String DateCreated;
    String Id;

    public Message() {
    }

    public com.anke.yingxiang.domain.anke.msg.MessageType getMessageType() {
        return MessageType;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setMessageType(com.anke.yingxiang.domain.anke.msg.MessageType messageType) {
        MessageType = messageType;
    }

    public com.anke.yingxiang.domain.anke.msg.Sender getSender() {
        return Sender;
    }

    public void setSender(com.anke.yingxiang.domain.anke.msg.Sender sender) {
        Sender = sender;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String dateCreated) {
        DateCreated = dateCreated;
    }
}