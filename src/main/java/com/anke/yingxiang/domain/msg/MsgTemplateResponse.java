package com.anke.yingxiang.domain.msg;

public class MsgTemplateResponse {
    private int errcode;
    private String errmsg;
    private String msgid;

    public MsgTemplateResponse() {
    }

    public MsgTemplateResponse(int errcode, String errmsg, String msgid) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.msgid = msgid;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
