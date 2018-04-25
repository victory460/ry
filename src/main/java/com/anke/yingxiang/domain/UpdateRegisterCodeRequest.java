package com.anke.yingxiang.domain;

public class UpdateRegisterCodeRequest {
    private String SerialId;
    private String RegistrationCodeDeadline;
    private boolean Permanent;

    public String getSerialId() {
        return SerialId;
    }

    public void setSerialId(String serialId) {
        SerialId = serialId;
    }

    public String getRegistrationCodeDeadline() {
        return RegistrationCodeDeadline;
    }

    public void setRegistrationCodeDeadline(String registrationCodeDeadline) {
        RegistrationCodeDeadline = registrationCodeDeadline;
    }

    public boolean isPermanent() {
        return Permanent;
    }

    public void setPermanent(boolean permanent) {
        Permanent = permanent;
    }
}
