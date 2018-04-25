package com.anke.yingxiang.controller;

import com.anke.yingxiang.domain.*;
import com.anke.yingxiang.domain.anke.*;
import com.anke.yingxiang.rest.RestService;
import com.anke.yingxiang.service.cookie.CookieService;
import com.anke.yingxiang.service.userinfo.UserInfoService;
import com.anke.yingxiang.util.AjaxResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by qingxiangzhang on 2017/3/1.
 */
@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private AjaxResponseUtil ajaxResponseUtil;

    @Autowired
    private RestService restService;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private UserInfoService userInfoService;

    private String openId() {
        return cookieService.getOpenId(httpServletRequest);
    }

    @GetMapping({"/"})
    public String home(Map<String, Object> model) {
        return "index?a=123";
    }

//    @RequestMapping(value = ("/tasks"), method = {RequestMethod.POST, RequestMethod.GET})
//    @Transactional
//    public List<Product> allTasks() {
//
//        List<Product> products = new ArrayList<>();
//        Manufacturer manufacturer = new Manufacturer("XX机电");
//        for (int i = 0; i < 10; i++) {
//            Product product = new Product(i, "产品" + i, "http://ojqwtoohq.bkt.clouddn.com/19186_26.jpg", manufacturer, "￥" + (int) (Math.random() * 1000));
//            products.add(product);
//        }
//
//        return products;
//    }

    /**
     * 获取我的任务
     *
     * @param type 1：open, 2: allocated, 3: processed, 4: closed
     * @return
     */
    @Transactional
    @RequestMapping(value = "/tasks/{type}", method = {RequestMethod.GET, RequestMethod.POST})
    public List<MyTask> tasks(@PathVariable String type, @RequestParam(name = "productId") String productId, @RequestParam("all") boolean all, @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        if (productId.equals("0")) {
            productId = "00000000-0000-0000-0000-000000000000";
        }
        List<FeedbackModel> feedbackModels = restService.getFeedbackRecords(openId(), type, productId, all, page, pageSize);
        List<MyTask> tasks = new ArrayList<>();
        for (int i = 0; i < feedbackModels.size(); i++) {
            FeedbackModel one = feedbackModels.get(i);
            tasks.add(new MyTask(one.getId(), one.getProductId(), one.getProduct() == null ? "" : one.getProduct().getName(), one.getTitle(), one.getFeedbackType() == null ? "" : one.getFeedbackType().getName(), one.isReopen(), one.getPrincipal() == null ? "" : one.getPrincipal().getName(), one.getFeedbackDate().substring(0, 10), one.getFeedbackState() == null ? "" : one.getFeedbackState().getName()));
            System.out.println(one.getId());
        }
//        feedbackModels.forEach(one -> {
//            tasks.add(new MyTask(one.getId(), one.getProductId(), one.getProduct() == null ? "" : one.getProduct().getName(), one.getTitle(), one.getFeedbackType() == null ? "" : one.getFeedbackType().getName(), one.isReopen(), one.getPrincipal() == null ? "" : one.getPrincipal().getName(), one.getFeedbackDate().substring(0, 10), one.getFeedbackState() == null ? "" : one.getFeedbackState().getName()));
//            System.out.println(one.getId());
//        });
        return tasks;
    }

    /**
     * 获取一条任务
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/test/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public FeedbackModel testOneTask(@PathVariable String id) {
        FeedbackRecordModel feedbackRecordModel = restService.getFeedbackRecord(openId(), id);
        FeedbackModel feedbackModel = feedbackRecordModel.getFeedbackRecordWithReport();
        return feedbackModel;
    }

    /**
     * 获取一条任务
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/task/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public OneFeedback task(@PathVariable String id) {
        FeedbackRecordModel feedbackRecordModel = restService.getFeedbackRecord(openId(), id);
        FeedbackModel feedbackModel = feedbackRecordModel.getFeedbackRecordWithReport();

        OneFeedback oneFeedback = new OneFeedback();
        oneFeedback.setTitle(feedbackModel.getTitle());
        oneFeedback.setType(feedbackModel.getFeedbackType() == null ? "" : feedbackModel.getFeedbackType().getDisplayName());
        oneFeedback.setTypeId(feedbackModel.getFeedbackType() == null ? "" : feedbackModel.getFeedbackType().getId());
        oneFeedback.setDescription(feedbackModel.getDescription());
        oneFeedback.setFeedbackDate(feedbackModel.getFeedbackDate().substring(0, 10));
        oneFeedback.setId(feedbackModel.getId());
        oneFeedback.setPrincipleName(feedbackModel.getPrincipal() == null ? "" : feedbackModel.getPrincipal().getName());
        oneFeedback.setPrincipleId(feedbackModel.getPrincipal() == null ? "" : feedbackModel.getPrincipal().getId());
        oneFeedback.setSubmiterName(feedbackModel.getSubmitter() == null ? "" : feedbackModel.getSubmitter().getName());
        oneFeedback.setSubmiterId(feedbackModel.getSubmitter() == null ? "" : feedbackModel.getSubmitter().getId());
        oneFeedback.setProductName(feedbackModel.getProduct() == null ? "" : feedbackModel.getProduct().getName());
        oneFeedback.setProductId(feedbackModel.getProduct() == null ? "" : feedbackModel.getProduct().getId());
        oneFeedback.setFeedbackRecordReportsModels(feedbackModel.getFeedbackRecordReports());
        oneFeedback.setReOpen(feedbackModel.isReopen());

        return oneFeedback;
    }

    @Transactional
    @RequestMapping(value = "/initData", method = {RequestMethod.GET, RequestMethod.POST})
    public String initData() {
        return "太耗时，已弃用";
    }

    @Transactional
    @RequestMapping(value = "/engineers", method = {RequestMethod.GET, RequestMethod.POST})
    public List<UserModel> engineers() {
        // 负责人
        AllUserData allUserData = restService.getEngineers(openId());
        return allUserData.getUsers();
    }

    @Transactional
    @RequestMapping(value = "/types", method = {RequestMethod.GET, RequestMethod.POST})
    public List<FeedbackTypeModel> types() {
        // 反馈类型
        FeedbackType feedbackType = restService.getFeedbackTypes(openId());
        return feedbackType.getFeedbackTypes();
    }

    @Transactional
    @RequestMapping(value = "/devices", method = {RequestMethod.GET, RequestMethod.POST})
    public List<DeviceModel> devices() {
        // 设备名称
        AllDeviceData allDeviceData = restService.getValidProducts(openId());
        return allDeviceData.getProducts();
    }

    @Transactional
    @RequestMapping(value = "/fixTypes", method = {RequestMethod.GET, RequestMethod.POST})
    public List<FixTypeModel> fixTypes() {
        // 修理类型
        FixType fixType = restService.getFixTypes(openId());
        if (fixType != null) {
            return fixType.getMaintenanceTypes();
        }
        return null;
    }

    @Transactional
    @RequestMapping(value = "/fixes", method = {RequestMethod.GET, RequestMethod.POST})
    public List<OneMaintenance> fixes(@RequestParam(name = "feedbackRecordId") String feedbackRecordId) {
        FixRecordModel fixRecordModel = restService.getMaintenanceRecordByFeedbackId(openId(), feedbackRecordId);

        List<OneMaintenance> oneMaintenances = new ArrayList<>();

        for (int i = 0; i < fixRecordModel.getMaintenanceRecords().size(); i++) {
            FixModel fixModel = fixRecordModel.getMaintenanceRecords().get(i);
            OneMaintenance oneMaintenance = new OneMaintenance();
            oneMaintenance.setFeedbackRecordId(fixModel.getFeedbackRecordId());
            oneMaintenance.setClosed(fixModel.isClosed());
            oneMaintenance.setId(fixModel.getId());
            oneMaintenance.setMaintenanceDate(fixModel.getMaintenanceDate() == null ? "" : fixModel.getMaintenanceDate().substring(0, 10));
            oneMaintenance.setSubmitter(fixModel.getSubmitter() == null ? "" : fixModel.getSubmitter().getName());
            oneMaintenance.setSubmitterId(fixModel.getSubmitter() == null ? "" : fixModel.getSubmitter().getId());
            oneMaintenance.setType(fixModel.getMaintenanceType() == null ? "" : fixModel.getMaintenanceType().getDisplayName());
            oneMaintenance.setTypeId(fixModel.getMaintenanceType() == null ? "" : fixModel.getMaintenanceType().getId());
            oneMaintenance.setFixRecordReportsModels(fixModel.getMaintenanceRecordReports());
            oneMaintenance.setDescription(fixModel.getDescription());
            oneMaintenances.add(oneMaintenance);
        }

//        fixRecordModel.getMaintenanceRecords().forEach(fixModel -> {
//        });

        return oneMaintenances;
    }

    /**
     * 新建维修记录
     *
     * @param fixId
     * @param closed           false:暂存，true:提交
     * @param fixDate
     * @param description
     * @param fixTypeId
     * @param feedbackRecordId
     * @return
     */
    @Transactional
    @RequestMapping(value = "/newFix")
    public FixModel newFix(@RequestParam(name = "fixId") String fixId, @RequestParam(name = "closed") boolean closed, @RequestParam(name = "fixDate") String fixDate, @RequestParam(name = "description") String description, @RequestParam(name = "fixTypeId") String fixTypeId, @RequestParam(name = "feedbackRecordId") String feedbackRecordId) {
        FixModel fixModel = new FixModel();
        if ("-1".equals(fixId)) {
            fixId = UUID.randomUUID().toString().replace("-", "");
        }
        fixModel.setMaintenanceRecordId(fixId);
        fixModel.setClosed(closed);
        fixModel.setDescription(description);
        fixModel.setFeedbackRecordId(feedbackRecordId);
        fixModel.setMaintenanceDate(fixDate);
        fixModel.setMaintenanceTypeId(fixTypeId);

        FixModel result = restService.createMaintenanceRecord(openId(), fixModel);
        return result;
    }


    /**
     * 创建问题反馈
     *
     * @param ProductId
     * @param FeedbackTypeId
     * @param FeedbackDate
     * @param Title
     * @param Description
     * @return
     */
    @Transactional
    @RequestMapping(value = "/newTask", method = {RequestMethod.GET, RequestMethod.POST})
    public FeedbackModel newTask(@RequestParam(value = "ProductId") String ProductId, @RequestParam(value = "FeedbackTypeId") String FeedbackTypeId, @RequestParam(value = "FeedbackDate") String FeedbackDate, @RequestParam(value = "Title") String Title, @RequestParam(value = "Description") String Description) {
        String openId = openId();
        if (openId != null) {
            UserInfo userInfo = userInfoService.findByOpenid(openId);
            RequestFeedbackModel requestFeedbackModel = new RequestFeedbackModel();
            String id = UUID.randomUUID().toString().replace("-", "");
            requestFeedbackModel.setFeedbackRecordId(id);
            requestFeedbackModel.setTitle(Title);
            requestFeedbackModel.setFeedbackDate(FeedbackDate);
            requestFeedbackModel.setDescription(Description);
            requestFeedbackModel.setProductId(ProductId);
            requestFeedbackModel.setFeedbackTypeId(FeedbackTypeId);
            requestFeedbackModel.setReopen(false);
            requestFeedbackModel.setPrincipalId(userInfo.getUserid());
            System.out.println(requestFeedbackModel.toString());

            FeedbackModel result = restService.createFeedbackRecord(openId, requestFeedbackModel);
            return result;
        }

        return null;
    }

    @RequestMapping(value = "/removeTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String removeTask(@RequestParam(value = "id") String id) {
        String result = restService.deleteFeedbackRecord(openId(), id);
        return "删除成功";
    }

    /**
     * 修改FeedbackRecord
     *
     * @param FeedbackRecordId
     * @param ProductId
     * @param FeedbackTypeId
     * @param FeedbackDate
     * @param PrincipalId
     * @param Title
     * @param Description
     * @return
     */
    @Transactional
    @RequestMapping(value = "/editTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String editTask(@RequestParam(value = "FeedbackRecordId") String FeedbackRecordId, @RequestParam(value = "ProductId") String ProductId, @RequestParam(value = "FeedbackTypeId") String FeedbackTypeId, @RequestParam(value = "FeedbackDate") String FeedbackDate, @RequestParam(value = "PrincipalId") String PrincipalId, @RequestParam(value = "Title") String Title, @RequestParam(value = "Description") String Description) {
        RequestFeedbackModel requestFeedbackModel = new RequestFeedbackModel();
        requestFeedbackModel.setTitle(Title);
        requestFeedbackModel.setFeedbackDate(FeedbackDate);
        requestFeedbackModel.setDescription(Description);
        requestFeedbackModel.setFeedbackRecordId(FeedbackRecordId);
        requestFeedbackModel.setPrincipalId(PrincipalId);
        requestFeedbackModel.setProductId(ProductId);
        requestFeedbackModel.setFeedbackTypeId(FeedbackTypeId);

        System.out.println(requestFeedbackModel.toString());

        HttpStatus result = restService.allocateFeedbackRecord(openId(), requestFeedbackModel);
        if (result.is2xxSuccessful()) {
            return "操作成功";
        }
        return "操作失败";
    }


    /**
     * 对已处理的反馈记录，进行回退
     *
     * @return
     */
    @Transactional
    @RequestMapping(value = "/unProcessTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String unProcessTask(@RequestParam(value = "FeedbackRecordId") String FeedbackRecordId, @RequestParam(value = "ProductId") String ProductId, @RequestParam(value = "FeedbackTypeId") String FeedbackTypeId, @RequestParam(value = "FeedbackDate") String FeedbackDate, @RequestParam(value = "PrincipalId") String PrincipalId, @RequestParam(value = "Title") String Title, @RequestParam(value = "Description") String Description) {
        RequestFeedbackModel requestFeedbackModel = new RequestFeedbackModel();
        requestFeedbackModel.setTitle(Title);
        requestFeedbackModel.setFeedbackDate(FeedbackDate);
        requestFeedbackModel.setDescription(Description);
        requestFeedbackModel.setFeedbackRecordId(FeedbackRecordId);
        requestFeedbackModel.setPrincipalId(PrincipalId);
        requestFeedbackModel.setProductId(ProductId);
        requestFeedbackModel.setFeedbackTypeId(FeedbackTypeId);
        requestFeedbackModel.setReopen(false);
        HttpStatus result = restService.allocateFeedbackRecord(openId(), requestFeedbackModel);
        if (result.is2xxSuccessful()) {
            return "操作成功";
        }
        return "操作失败";
    }

    /**
     * 对已处理的反馈记录，进行回退
     *
     * @return
     */
    @Transactional
    @RequestMapping(value = "/closeTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String closeTask(@RequestParam(value = "FeedbackRecordId") String FeedbackRecordId, @RequestParam(value = "ProductId") String ProductId, @RequestParam(value = "FeedbackTypeId") String FeedbackTypeId, @RequestParam(value = "FeedbackDate") String FeedbackDate, @RequestParam(value = "PrincipalId") String PrincipalId, @RequestParam(value = "Title") String Title, @RequestParam(value = "Description") String Description) {
        RequestFeedbackModel requestFeedbackModel = new RequestFeedbackModel();
        requestFeedbackModel.setTitle(Title);
        requestFeedbackModel.setFeedbackDate(FeedbackDate);
        requestFeedbackModel.setDescription(Description);
        requestFeedbackModel.setFeedbackRecordId(FeedbackRecordId);
        requestFeedbackModel.setPrincipalId(PrincipalId);
        requestFeedbackModel.setProductId(ProductId);
        requestFeedbackModel.setFeedbackTypeId(FeedbackTypeId);
        requestFeedbackModel.setReopen(true);
        HttpStatus result = restService.closeFeedbackRecord(openId(), requestFeedbackModel);
        if (result.is2xxSuccessful()) {
            return "操作成功";
        }
        return "操作失败";
    }

    /**
     * 对已关闭的记录，重新打开
     *
     * @return
     */
    @Transactional
    @RequestMapping(value = "/openTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String openTask(@RequestParam(value = "FeedbackRecordId") String FeedbackRecordId, @RequestParam(value = "ProductId") String ProductId, @RequestParam(value = "FeedbackTypeId") String FeedbackTypeId, @RequestParam(value = "FeedbackDate") String FeedbackDate, @RequestParam(value = "PrincipalId") String PrincipalId, @RequestParam(value = "Title") String Title, @RequestParam(value = "Description") String Description) {
        RequestFeedbackModel requestFeedbackModel = new RequestFeedbackModel();
        requestFeedbackModel.setTitle(Title);
        requestFeedbackModel.setFeedbackDate(FeedbackDate);
        requestFeedbackModel.setDescription(Description);
        requestFeedbackModel.setFeedbackRecordId(FeedbackRecordId);
        requestFeedbackModel.setPrincipalId(PrincipalId);
        requestFeedbackModel.setProductId(ProductId);
        requestFeedbackModel.setFeedbackTypeId(FeedbackTypeId);
        requestFeedbackModel.setReopen(true);
        HttpStatus result = restService.openFeedbackRecord(openId(), requestFeedbackModel);
        if (result.is2xxSuccessful()) {
            return "操作成功";
        }
        return "操作失败";
    }


    private List<SaleInfo> handleSaleModel(AllSaleData allSaleData) {
        if (allSaleData != null) {
            List<SaleModel> saleModels = allSaleData.getSales();
            List<SaleInfo> saleInfos = new ArrayList<>();
            for (int i = 0; i < saleModels.size(); i++) {
                SaleModel one = saleModels.get(i);
                saleInfos.add(new SaleInfo(one.getId(), one.getClientId(), one.getClient().getName(), one.getProductId(), one.getProduct().getName()));
            }
//            saleModels.forEach(one -> {
////                one.getClient().getName(); //test
////                one.getClientId(); //5753
////                one.getId();  //b4fc  saleId
////                one.getProductId(); //886ae
////                one.getProduct().getName(); //test123
//                saleInfos.add(new SaleInfo(one.getId(), one.getClientId(), one.getClient().getName(), one.getProductId(), one.getProduct().getName()));
//            });
            return saleInfos;
        }
        return null;
    }

    /**
     * 可以发货的设备信息
     *
     * @return
     */
    @RequestMapping(value = "/deliveringProducts", method = {RequestMethod.GET, RequestMethod.POST})
    public List<SaleInfo> deliveringProducts() {
        AllSaleData allSaleData = restService.getProductSales(openId(), 1);
        return handleSaleModel(allSaleData);
    }

    /**
     * 可以安装的设备信息
     *
     * @return
     */
    @RequestMapping(value = "/installProducts", method = {RequestMethod.GET, RequestMethod.POST})
    public List<SaleInfo> installProducts() {
        AllSaleData allSaleData = restService.getProductSales(openId(), 2);
        return handleSaleModel(allSaleData);
    }

    /**
     * 可以培训的设备信息
     *
     * @return
     */
    @RequestMapping(value = "/trainProducts", method = {RequestMethod.GET, RequestMethod.POST})
    public List<SaleInfo> trainProducts() {
        AllSaleData allSaleData = restService.getProductSales(openId(), 3);
        return handleSaleModel(allSaleData);
    }

    /**
     * 增加发货记录
     *
     * @param departureDate
     * @param description
     * @param principal
     * @param productId
     * @param saleId
     * @return
     */
    @RequestMapping(value = "/newDeliver", method = {RequestMethod.GET, RequestMethod.POST})
    public String newDeliver(@RequestParam(name = "departureDate") String departureDate, @RequestParam(name = "description") String description, @RequestParam(name = "principal") String principal, @RequestParam(name = "productId") String productId, @RequestParam(name = "saleId") String saleId) {
        String deliverRecordId = UUID.randomUUID().toString().replace("-", "");
        DeliverModel deliverModel = new DeliverModel();
        deliverModel.setArrivalDate(departureDate);
        deliverModel.setDepartureDate(departureDate);
        deliverModel.setDescription(description);
        deliverModel.setPrincipal(principal);
        deliverModel.setProductId(productId);
        deliverModel.setSaleId(saleId);
        deliverModel.setDeliverRecordId(deliverRecordId);

        DeliverRecordModel deliverRecordModel = restService.createDeliverRecord(openId(), deliverModel);

        if (deliverRecordModel != null) {
            return deliverRecordModel.getDeliverRecord().getId();
        } else {
            return "";
        }
    }

    @RequestMapping(value = "/newInstall", method = {RequestMethod.GET, RequestMethod.POST})
    public String newInstall(@RequestParam(name = "installDate") String installDate, @RequestParam(name = "deadlineDate") String deadlineDate, @RequestParam(name = "description") String description, @RequestParam(name = "principal") String principal, @RequestParam(name = "productId") String productId, @RequestParam(name = "saleId") String saleId) {
        String installRecordId = UUID.randomUUID().toString().replace("-", "");
        InstallModel installModel = new InstallModel();
        installModel.setDescription(description);
        installModel.setInstallationDate(installDate);
        installModel.setInstallationRecordId(installRecordId);
        installModel.setProductId(productId);
        installModel.setPrincipal(principal);
        installModel.setRegistrationCodeDeadline(deadlineDate);
        installModel.setSaleId(saleId);

        InstallRecordModel installRecord = restService.createInstallRecord(openId(), installModel);

        if (installRecord != null) {
            return installRecord.getInstallationRecord().getId();
        } else {
            return "";
        }
    }

    @RequestMapping(value = "/newTrain", method = {RequestMethod.GET, RequestMethod.POST})
    public String newTrain(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate, @RequestParam(name = "description") String description, @RequestParam(name = "trainer") String trainer, @RequestParam(name = "content") String content, @RequestParam(name = "productId") String productId, @RequestParam(name = "saleId") String saleId) {
        String trainRecordId = UUID.randomUUID().toString().replace("-", "");
        TrainModel trainModel = new TrainModel();
        trainModel.setTrainingRecordId(trainRecordId);
        trainModel.setDescription(description);
        trainModel.setTrainingBeginDate(startDate);
        trainModel.setTrainingEndDate(endDate);
        trainModel.setTitle(content);
        trainModel.setTrainer(trainer);
        trainModel.setProductId(productId);
        trainModel.setSaleId(saleId);
        TrainRecordModel trainRecordModel = restService.createTrainRecord(openId(), trainModel);

        if (trainRecordModel != null) {
            return trainRecordModel.getTrainingRecord().getId();
        } else {
            return "";
        }
    }

    /**
     * 设备信息
     *
     * @param page     页数
     * @param pageSize 每页长度
     * @return
     */
    @RequestMapping(value = "/products", method = {RequestMethod.GET, RequestMethod.POST})
    public List<DeviceModel> products(@RequestParam(name = "page") int page, @RequestParam(name = "pageSize") int pageSize, @RequestParam(name = "search") String search) {
        RequestModel requestModel = new RequestModel(page, pageSize, "Name", "DESC");
        AllDeviceData allDeviceData = null;
        if (search != null && search.length() > 0) {
            allDeviceData = restService.quiryValidProducts(openId(), search);
        } else {
            allDeviceData = restService.getProductsByUser(openId(), requestModel);
        }
        if (allDeviceData != null) {
            List<DeviceModel> deviceModels = allDeviceData.getProducts();
            return deviceModels;
        }
        return null;
    }

    /**
     * 获得 某个设备的 发货记录
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/deliver", method = {RequestMethod.GET, RequestMethod.POST})
    public DeliverModel deliver(String productId) {
        DeliverRecordModel deliverRecordModel = restService.getDeliverRecordByProduct(openId(), productId);
        if (deliverRecordModel != null) {
            return deliverRecordModel.getDeliverRecordWithReport();
        }
        return null;
    }

    /**
     * 获得 某个设备的 安装记录
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/install", method = {RequestMethod.GET, RequestMethod.POST})
    public InstallModel install(String productId) {
        InstallRecordModel installRecordModel = restService.getInstallRecordByProduct(openId(), productId);
        if (installRecordModel != null) {
            return installRecordModel.getInstallationRecordWithReport();
        }
        return null;
    }

    /**
     * 获得 某个设备的 培训记录
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "/train", method = {RequestMethod.GET, RequestMethod.POST})
    public TrainModel train(String productId) {
        TrainRecordModel trainRecordModel = restService.getTrainRecordByProduct(openId(), productId);
        if (trainRecordModel != null) {
            return trainRecordModel.getTrainingRecordWithReport();
        }
        return null;
    }

    /**
     * 获取某个用户的发货记录
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/deliverRecordByUser", method = {RequestMethod.GET, RequestMethod.POST})
    public List<RecordResponseModel> deliverRecordByUser(int page, int size) {
        RequestModel requestModel = new RequestModel();
        requestModel.setPageSize(size);
        requestModel.setCurrentPageNumber(page);
        requestModel.setSortDirection("DESC");
        requestModel.setSortExpression("ProductName");
        DeliverRecordModel deliverRecordModel = restService.getDeliverRecordByUser(openId(), requestModel);

        List<RecordResponseModel> recordResponseModels = new ArrayList<>();

        List<DeliverModel> deliverModels = deliverRecordModel.getDeliverRecords();
        for (int i = 0; i < deliverModels.size(); i++) {
            DeliverModel deliverModel = deliverModels.get(i);
            String id = deliverModel.getId();
            String date = deliverModel.getDepartureDate().substring(0, 10);
            String productId = deliverModel.getSale().getProductId();
            String productName = deliverModel.getSale().getProduct().getName();
            String principal = deliverModel.getPrincipal();
            String description = deliverModel.getDescription();
            ClientModel clientModel = deliverModel.getSale().getClient();
            String location = "";
            if (clientModel != null) {
                if (clientModel.getDistrict() != null) {
                    location = clientModel.getDistrict().getMergeName() + clientModel.getAddress();
                } else {
                    location = clientModel.getAddress();
                }
            }
            RecordResponseModel recordResponseModel = new RecordResponseModel(id, date, productName, productId, principal, description, location);
            recordResponseModels.add(recordResponseModel);
        }

        return recordResponseModels;
    }

    /**
     * 获得 某个用户的 安装记录
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/installRecordByUser", method = {RequestMethod.GET, RequestMethod.POST})
    public List<RecordResponseModel> installRecordByUser(int page, int size) {
        RequestModel requestModel = new RequestModel();
        requestModel.setPageSize(size);
        requestModel.setCurrentPageNumber(page);
        requestModel.setSortDirection("DESC");
        requestModel.setSortExpression("ProductName");
        InstallRecordModel installRecordModel = restService.getInstallRecordByUser(openId(), requestModel);

        List<RecordResponseModel> recordResponseModels = new ArrayList<>();

        List<InstallModel> installModels = installRecordModel.getInstallationRecords();
        for (int i = 0; i < installModels.size(); i++) {
            InstallModel installModel = installModels.get(i);
            String id = installModel.getId();
            String date = installModel.getInstallationDate().substring(0, 10);
            String productId = installModel.getSale().getProductId();
            String productName = installModel.getSale().getProduct().getName();
            String principal = installModel.getPrincipal();
            String description = installModel.getDescription();
            ClientModel clientModel = installModel.getSale().getClient();
            String location = "";
            if (clientModel.getDistrict() != null) {
                location = clientModel.getDistrict().getMergeName() + clientModel.getAddress();
            } else {
                location = clientModel.getAddress();
            }
            RecordResponseModel recordResponseModel = new RecordResponseModel(id, date, productName, productId, principal, description, location);
            recordResponseModels.add(recordResponseModel);
        }
        return recordResponseModels;
    }

    /**
     * 获取某个用户 的 培训记录
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/trainRecordByUser", method = {RequestMethod.GET, RequestMethod.POST})
    public List<RecordResponseModel> trainRecordByUser(int page, int size) {
        RequestModel requestModel = new RequestModel();
        requestModel.setPageSize(size);
        requestModel.setCurrentPageNumber(page);
        requestModel.setSortDirection("DESC");
        requestModel.setSortExpression("ProductName");
        TrainRecordModel trainRecordModel = restService.getTrainRecordByUser(openId(), requestModel);

        List<RecordResponseModel> recordResponseModels = new ArrayList<>();

        List<TrainModel> trainingRecords = trainRecordModel.getTrainingRecords();
        for (int i = 0; i < trainingRecords.size(); i++) {
            TrainModel trainModel = trainingRecords.get(i);
            String id = trainModel.getId();
            String date = trainModel.getTrainingBeginDate().substring(0, 10);
            String productId = trainModel.getSale().getProductId();
            String productName = trainModel.getSale().getProduct().getName();
            String principal = trainModel.getTrainer();
            String description = trainModel.getDescription();
            ClientModel clientModel = trainModel.getSale().getClient();
            String location = "";
            if (clientModel.getDistrict() != null) {
                location = clientModel.getDistrict().getMergeName() + clientModel.getAddress();
            } else {
                location = clientModel.getAddress();
            }
            RecordResponseModel recordResponseModel = new RecordResponseModel(id, date, productName, productId, principal, description, location);
            recordResponseModels.add(recordResponseModel);
        }
        return recordResponseModels;
    }

    @RequestMapping(value = "/clientRecord", method = {RequestMethod.GET, RequestMethod.POST})
    public List<ClientResponseModel> clientRecord(int page, int size) {
        RequestModel requestModel = new RequestModel();
        requestModel.setPageSize(size);
        requestModel.setCurrentPageNumber(page);
        requestModel.setSortDirection("DESC");
        requestModel.setSortExpression("Name");
        ClientRecordModel clientRecord = restService.getClientRecord(openId(), requestModel);

        List<ClientResponseModel> clientResponseModels = new ArrayList<>();

        List<ClientModel> clientModels = clientRecord.getClients();
        for (int i = 0; i < clientModels.size(); i++) {
            ClientModel clientModel = clientModels.get(i);
            String name = clientModel.getName();
            String principal = clientModel.getPrincipal();
            String state = clientModel.getDistrict() != null ? clientModel.getDistrict().getMergeName() : "";
            String address = clientModel.getAddress();
            boolean status = clientModel.getInUse();
            ClientResponseModel clientResponseModel = new ClientResponseModel(clientModel.getId(), name, principal, state, address, status ? "使用中" : "未使用", clientModel.getTelephone(), clientModel.getCityId(), clientModel.getProvinceId(), clientModel.getDistrictId(), clientModel.getCountryId(), clientModel.getTechnician());
            clientResponseModels.add(clientResponseModel);
        }
        return clientResponseModels;
    }

    @RequestMapping(value = "/validSales", method = {RequestMethod.GET, RequestMethod.POST})
    public List<SaleResponseModel> validSales(int page, int size, String search, String expire) {
        RequestModel requestModel = new RequestModel();
        requestModel.setPageSize(size);
        requestModel.setCurrentPageNumber(page);
        requestModel.setSortDirection("DESC");
        requestModel.setSortExpression("ProductName");
        SaleRecordModel saleRecordModel = null;
        if((search!=null && search.length()>0) || (expire!=null && expire.length()>0)){
            saleRecordModel = restService.searchValidSales(openId(), search, expire);
        }else {
            saleRecordModel = restService.validSales(openId(), requestModel);
        }

        List<SaleResponseModel> saleResponseModels = new ArrayList<>();

        List<SaleModel> saleModels = saleRecordModel.getSales();
        for (int i = 0; i < saleModels.size(); i++) {
            SaleModel saleModel = saleModels.get(i);

            String name = saleModel.getProduct().getName();
            String type = saleModel.getProduct().getProductType().getName();
            String serialId = saleModel.getProduct().getSerialId();
            String deviceId = saleModel.getProduct().getDeviceId();

            String date = saleModel.getProduct().getRegistrationCodeDeadline().substring(0, 10);

            String deadlineDate = date.substring(0, 4) + "年" + date.substring(5, 7) + "月" + date.substring(8, 10) + "日";
            String client = saleModel.getClient().getName();

            SaleResponseModel saleResponseModel = new SaleResponseModel(name, type, serialId, deviceId, deadlineDate, client);

            saleResponseModels.add(saleResponseModel);
        }
        return saleResponseModels;
    }

    @RequestMapping(value = "/registerCodeDeadline", method = {RequestMethod.GET, RequestMethod.POST})
    public UpdateRegisterCodeResponse updateRegisterCodeDeadline(@RequestParam(name = "serialId") String serialId, @RequestParam(name = "deadline") String deadline) {

        UpdateRegisterCodeRequest updateRegisterCodeRequest = new UpdateRegisterCodeRequest();
        updateRegisterCodeRequest.setPermanent(false);
        updateRegisterCodeRequest.setSerialId(serialId);
        updateRegisterCodeRequest.setRegistrationCodeDeadline(deadline);

        UpdateRegisterCodeResponse updateRegisterCodeResponse = restService.updateRegisterCodeDeadline(openId(), updateRegisterCodeRequest);

        return updateRegisterCodeResponse;
    }

    private int getAreaIdByName(String name) {
        Query query = entityManager.createNativeQuery("select id from area where name = '" + name + "'");
        List<Integer> list = query.getResultList();
        if (list != null) {
            return list.get(0);
        }
        return 0;
    }

    @RequestMapping(value = "/newClient", method = {RequestMethod.GET, RequestMethod.POST})
    public String newClient(@RequestParam(name = "id") String id, @RequestParam(name = "address") String address, @RequestParam(name = "district") String district, @RequestParam(name = "city") String city, @RequestParam(name = "province") String province, @RequestParam(name = "country") String country, @RequestParam(name = "name") String name, @RequestParam(name = "principal") String principal, @RequestParam(name = "phone") String phone, @RequestParam(name = "technician") String technician) {
        boolean create = true;
        int countryId = -1, provinceId = -1, cityId = -1, districtId = -1;
        if (id.length() < 4) {
            // 新增
            id = UUID.randomUUID().toString().replace("-", "");
            create = true;
        } else {
            // 更新
            create = false;
        }
        Query queryCountry = entityManager.createNativeQuery("select id from country where chineseName = '" + country + "'");
        List<Short> listCountry = queryCountry.getResultList();
        if (listCountry != null) {
            countryId = listCountry.get(0);
        }

        provinceId = getAreaIdByName(province);
        cityId = getAreaIdByName(city);
        districtId = getAreaIdByName(district);

        RequestClientModel requestClientModel = new RequestClientModel();
        requestClientModel.setAddress(address);
        requestClientModel.setCityId(cityId);
        requestClientModel.setCountryId(countryId);
        requestClientModel.setProvinceId(provinceId);
        requestClientModel.setDistrictId(districtId);
        requestClientModel.setId(id);
        requestClientModel.setInUse(false);
        requestClientModel.setName(name);
        requestClientModel.setPrincipal(principal);
        requestClientModel.setTelephone(phone);
        requestClientModel.setTechnician(technician);

        String result = restService.newClientRecord(openId(), create, requestClientModel);
        return result;
    }

    @RequestMapping(value = "/removeClient", method = {RequestMethod.GET, RequestMethod.POST})
    public String removeClient(@RequestParam(name = "id") String id) {
        String result = restService.removeClient(openId(), id);
        return result;
    }

    @RequestMapping(value = "/menus", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Menu> menus() {
        ResponseAnkeMenus result = restService.myMenus(openId());
        return result.getWechatMenus();
    }

//    @GetMapping({"/home"})
//    public String home(Map<String, Object> model) {
//        return "task";
//    }
//
//    @GetMapping({"/index"})
//    public String index(Map<String, Object> model) {
//        return "index";
//    }
//
//    @RequestMapping(value = ("/unsolved"), method = {RequestMethod.POST, RequestMethod.GET})
//    @ResponseBody
//    @Transactional
//    public MyResponse sayHello(Integer start, Integer page) {
//
//        List<MyTask> taskModels = new ArrayList<>();
//
//        for(int i=0; i<10; i++){
//            MyTask taskModel = new MyTask();
//            taskModel.setEquip("设备"+i);
//            taskModel.setTime("2017-10-23");
//            taskModels.add(taskModel);
//        }
//
//        return ajaxResponseUtil.getTaskUnsolvedSuccess(taskModels);
//    }

}