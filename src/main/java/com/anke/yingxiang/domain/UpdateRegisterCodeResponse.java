package com.anke.yingxiang.domain;

import java.util.List;

public class UpdateRegisterCodeResponse {

    private boolean IsAuthenicated;
    private boolean ReturnStatus;   // true表示申请注册码成功，false表示失败
    private List<String> ReturnMessage;

    public boolean isAuthenicated() {
        return IsAuthenicated;
    }

    public void setAuthenicated(boolean authenicated) {
        IsAuthenicated = authenicated;
    }

    public boolean isReturnStatus() {
        return ReturnStatus;
    }

    public void setReturnStatus(boolean returnStatus) {
        ReturnStatus = returnStatus;
    }

    public List<String> getReturnMessage() {
        return ReturnMessage;
    }

    public void setReturnMessage(List<String> returnMessage) {
        ReturnMessage = returnMessage;
    }
}
