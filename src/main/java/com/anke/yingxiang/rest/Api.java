package com.anke.yingxiang.rest;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */
public class Api {

    public static final String BASE = "http://116.62.23.219";

    public static final String LOGIN = BASE + "/api/account/Login";
    public static final String WECHAT_LOGIN_ANKE = BASE + "/api/account/WechatLogin";

    public static final String WECHANT_BIND_ANKE = BASE + "/api/account/WeChatBinding";

    public static final String GET_OPEN_FEEDBACK_RECORDS = BASE + "/api/feedbackRecord/GetOpenFeedbackRecords";
    public static final String GET_ALLOCATED_FEEDBACK_RECORDS = BASE + "/api/feedbackRecord/GetAllocatedFeedbackRecords";
    public static final String GET_PROCESSED_FEEDBACK_RECORDS = BASE + "/api/feedbackRecord/GetProcessedFeedbackRecords";
    public static final String GET_CLOSED_FEEDBACK_RECORDS = BASE + "/api/feedbackRecord/GetClosedFeedbackRecords";


    public static final String QuiryAllOpenFeedbackRecords = BASE + "/api/feedbackRecord/QuiryAllOpenFeedbackRecords";
    public static final String QuiryAllAllocatedFeedbackRecords = BASE + "/api/feedbackRecord/QuiryAllAllocatedFeedbackRecords";
    public static final String QuiryAllProcessedFeedbackRecords = BASE + "/api/feedbackRecord/QuiryAllProcessedFeedbackRecords";
    public static final String QuiryAllClosedFeedbackRecords = BASE + "/api/feedbackRecord/QuiryAllClosedFeedbackRecords";

    public static final String QuiryOpenFeedbackRecords = BASE + "/api/feedbackRecord/QuiryOpenFeedbackRecords";
    public static final String QuiryAllocatedFeedbackRecords = BASE + "/api/feedbackRecord/QuiryAllocatedFeedbackRecords";
    public static final String QuiryProcessedFeedbackRecords = BASE + "/api/feedbackRecord/QuiryProcessedFeedbackRecords";
    public static final String QuiryClosedFeedbackRecords = BASE + "/api/feedbackRecord/QuiryClosedFeedbackRecords";


    public static final String GET_FEEDBACK_RECORD = BASE + "/api/feedbackRecord/GetFeedbackRecord";

    public static final String GET_FEEDBACK_TYPES = BASE + "/api/feedbacktype/GetFeedbackTypes";

    public static final String GET_ENGINEERS = BASE + "/api/user/GetEngineers";

    public static final String GET_VALID_PRODUCTS = BASE + "/api/product/GetValidProducts";

    public static final String GET_PRODUCTS_BY_USER = BASE + "/api/product/GetProductsByUser";
    public static final String QUIRY_VALID_PRODUCTS = BASE + "/api/product/QuiryValidProducts";

    public static final String GET_FIX_TYPES = BASE + "/api/maintenancetype/GetMaintenanceTypes";


    public static final String GET_FOR_DELIVERING_PRODUCTS_SALES = BASE + "/api/sale/GetForDeliveringProductSales";
    public static final String GET_FOR_INSTALL_PRODUCTS_SALES = BASE + "/api/sale/GetForInstallationProductSales";
    public static final String GET_FOR_TRAIN_PRODUCTS_SALES = BASE + "/api/sale/GetForTrainingProductSales";


    public static final String CREATE_DELIVER_RECORD = BASE + "/api/deliverRecord/CreateDeliverRecord";
    public static final String CREATE_INSTALL_RECORD = BASE + "/api/installationRecord/CreateInstallationRecord";
    public static final String CREATE_TRAIN_RECORD = BASE + "/api/trainingRecord/CreateTrainingRecord";

    public static final String GET_DELIVER_RECORD_BY_PRODUCT = BASE + "/api/deliverRecord/GetDeliverRecordByProduct";
    public static final String GET_INSTALLATION_RECORD_BY_PRODUCT = BASE + "/api/installationRecord/GetInstallationRecordByProduct";
    public static final String GET_TRAIN_RECORD_BY_PRODUCT = BASE + "/api/trainingRecord/GetTrainingRecordByProduct";
    public static final String GET_FEEDBACK_RECORD_BY_PRODUCT = BASE + "/api/feedbackRecord/GetFeedbackRecordByProduct";


    public static final String CREATE_FEEDBACK_RECORD = BASE + "/api/feedbackRecord/CreateFeedbackRecord";
    public static final String ALLOCATE_FEEDBACK_RECORD = BASE + "/api/feedbackRecord/AllocateFeedbackRecord";
    public static final String CLOSE_FEEDBACK_RECORD = BASE + "/api/feedbackRecord/CloseFeedbackRecord";
    public static final String OPEN_FEEDBACK_RECORD = BASE + "/api/feedbackRecord/OpenFeedbackRecord";


    public static final String CREATE_MAINTENANCE_RECORD = BASE + "/api/maintenanceRecord/CreateMaintenanceRecord";
    public static final String UPDATE_MAINTENANCE_RECORD = BASE + "/api/maintenanceRecord/UpdateMaintenanceRecord";

    public static final String GET_MAINTENANCE_RECORD_BY_FEEDBACK_RECORD = BASE + "/api/maintenanceRecord/GetMaintenanceRecordByFeedbackRecord";


    // 记录
    public static final String INSTALL_RECORD_BY_USER = BASE + "/api/installationRecord/QuiryInstallationRecordsByUser";
    public static final String DELIVER_RECORD_BY_USER = BASE + "/api/deliverRecord/QuiryDeliverRecordsByUser";
    public static final String TRAIN_RECORD_BY_USER = BASE + "/api/trainingRecord/QuiryTrainingRecordsByUser";
    public static final String CLIENTS_RECORD = BASE + "/api/client/QuiryAvaliableClients";
    public static final String VALID_SALES = BASE + "/api/sale/QuiryValidSales";
    public static final String SEARCH_VALID_SALES = BASE + "/api/sale/SearchValidSales";





    public static final String UPDATE_REGISTER_DEADLINE = BASE + "/api/product/UpdateProductRegistrationCodeDeadline";

    // 客户
    public static final String CREATE_CLIENT = BASE + "/api/client/CreateClient";
    public static final String UPDATE_CLIENT = BASE + "/api/client/UpdateClient";
    public static final String REMOVE_CLIENT = BASE + "/api/client/RemoveClient";


    //菜单
    public static final String MY_MENUS = BASE + "/api/Main/GetWechatMenu";

//    消息
    public static final String MESSAGE_BY_USER = BASE + "/api/messageTargetMapping/MessageTargetMappingInquiryByUser";
    public static final String MESSAGE_DELETE = BASE + "/api/messageTargetMapping/DeleteMessageTargetMappingByMessage";
    public static final String MESSAGE_READ = BASE + "/api/messageTargetMapping/UpdateMessageTargetMapping";


//    删除记录
    public static final String REMOVE_FEEDBACK_RECORD = BASE + "/api/feedbackRecord/RemoveFeedbackRecord";

//    删除图片
    public static final String REMOVE_DELIVER_RECORD_REPORT = BASE + "/api/deliverRecord/RemoveDeliverRecordReport";
    public static final String REMOVE_INSTALLATION_RECORD_REPORT = BASE + "/api/installationRecord/RemoveInstallationRecordReport";
    public static final String REMOVE_TRAINING_RECORD_REPORT = BASE + "/api/trainingRecord/RemoveTrainingRecordReport";
    public static final String REMOVE_FEEDBACK_RECORD_REPORT = BASE + "/api/feedbackRecord/RemoveFeedbackRecordReport";
    public static final String REMOVE_MAINTENANCE_RECORD_REPORT = BASE + "/api/maintenanceRecord/RemoveMaintenanceRecordReport";

    //    oss token
    public static final String GET_READ_STS_TOKEN = BASE + "/api/oss/GetReadStsToken";
    public static final String GET_WRITE_DELIVER_RECORDS_STS_TOKEN = BASE + "/api/oss/GetWriteDeliverRecordStsToken";
    public static final String GET_WRITE_INSTALL_RECORDS_STS_TOKEN = BASE + "/api/oss/GetWriteInstallationRecordStsToken";
    public static final String GET_WRITE_TRAIN_RECORDS_STS_TOKEN = BASE + "/api/oss/GetWriteTrainingRecordStsToken";
    public static final String GET_WRITE_FEEDBACK_RECORDS_STS_TOKEN = BASE + "/api/oss/GetWriteFeedbackRecordStsToken";
    public static final String GET_WRITE_MAINTENANCE_RECORDS_STS_TOKEN = BASE + "/api/oss/GetWriteMaintenanceRecordStsToken";



}
