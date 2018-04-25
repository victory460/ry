package com.anke.yingxiang.domain.anke;

import java.io.Serializable;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */
public class DeviceModel implements Serializable{

    private static final long serialVersionUID = -7060210544600464481L;

    private String Id;                  // the product id
    private String DeviceId;
    private String Name;
    private DeviceTypeModel ProductType;
    private DeviceStateModel ProductState;
    private String SerialId;
    private HvgModel Hvg;
    private TubeModel Tube;
    private OperatingSystemTypeModel OperatingSystemType;
    private String OsActivationCode;
    private String RcVersion;
    private String SscVersion;
    private String UiVersion;
    private String Description;
    private String SoftDogDeadline;
    private String RegistrationCodeDeadline;
    private String ManufactureDate;
    private String ComputerSn;
    private String AcquisitionCardType;
    private Boolean IsTrained; // 是否已培训
    private ClientModel Client;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public DeviceTypeModel getProductType() {
        return ProductType;
    }

    public void setProductType(DeviceTypeModel productType) {
        ProductType = productType;
    }

    public DeviceStateModel getProductState() {
        return ProductState;
    }

    public void setProductState(DeviceStateModel productState) {
        ProductState = productState;
    }

    public String getSerialId() {
        return SerialId;
    }

    public void setSerialId(String serialId) {
        SerialId = serialId;
    }

    public HvgModel getHvg() {
        return Hvg;
    }

    public void setHvg(HvgModel hvg) {
        Hvg = hvg;
    }

    public TubeModel getTube() {
        return Tube;
    }

    public void setTube(TubeModel tube) {
        Tube = tube;
    }

    public OperatingSystemTypeModel getOperatingSystemType() {
        return OperatingSystemType;
    }

    public void setOperatingSystemType(OperatingSystemTypeModel operatingSystemType) {
        OperatingSystemType = operatingSystemType;
    }

    public String getOsActivationCode() {
        return OsActivationCode;
    }

    public void setOsActivationCode(String osActivationCode) {
        OsActivationCode = osActivationCode;
    }

    public String getRcVersion() {
        return RcVersion;
    }

    public void setRcVersion(String rcVersion) {
        RcVersion = rcVersion;
    }

    public String getSscVersion() {
        return SscVersion;
    }

    public void setSscVersion(String sscVersion) {
        SscVersion = sscVersion;
    }

    public String getUiVersion() {
        return UiVersion;
    }

    public void setUiVersion(String uiVersion) {
        UiVersion = uiVersion;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSoftDogDeadline() {
        return SoftDogDeadline;
    }

    public void setSoftDogDeadline(String softDogDeadline) {
        SoftDogDeadline = softDogDeadline;
    }

    public String getRegistrationCodeDeadline() {
        return RegistrationCodeDeadline;
    }

    public void setRegistrationCodeDeadline(String registrationCodeDeadline) {
        RegistrationCodeDeadline = registrationCodeDeadline;
    }

    public String getManufactureDate() {
        return ManufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        ManufactureDate = manufactureDate;
    }

    public String getComputerSn() {
        return ComputerSn;
    }

    public void setComputerSn(String computerSn) {
        ComputerSn = computerSn;
    }

    public String getAcquisitionCardType() {
        return AcquisitionCardType;
    }

    public void setAcquisitionCardType(String acquisitionCardType) {
        AcquisitionCardType = acquisitionCardType;
    }

    public Boolean getTrained() {
        return IsTrained;
    }

    public void setTrained(Boolean trained) {
        IsTrained = trained;
    }

    public ClientModel getClient() {
        return Client;
    }

    public void setClient(ClientModel client) {
        Client = client;
    }
}