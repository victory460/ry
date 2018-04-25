package com.anke.yingxiang.domain.msg;

public class MsgTemplateModel {
    private String touser;
    private String template_id;
    private String url;
    private String topcolor;
    private MsgTemplateDataModel data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public MsgTemplateDataModel getData() {
        return data;
    }

    public void setData(MsgTemplateDataModel data) {
        this.data = data;
    }
}
