package com.anke.yingxiang.rest;

import com.anke.yingxiang.domain.*;
import com.anke.yingxiang.domain.anke.*;
import com.anke.yingxiang.domain.anke.msg.MessageRequestModel;
import com.anke.yingxiang.domain.anke.msg.MessagesModel;
import com.anke.yingxiang.domain.anke.msg.MsgRequestModel;
import com.anke.yingxiang.domain.msg.*;
import com.anke.yingxiang.domain.oss.OssInfo;
import com.anke.yingxiang.service.CommonInterface;
import com.anke.yingxiang.service.RedisService;
import com.anke.yingxiang.service.ResponseService;
import com.anke.yingxiang.service.userinfo.UserInfoService;
import com.anke.yingxiang.util.ConstantUtil;
import com.anke.yingxiang.util.MapUtil;
import com.anke.yingxiang.util.RedisUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.events.Event;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */
@Service
@Component("resetService")
public class RestService {

    private RestTemplate restTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserInfoService userInfoService;

    @Value("${wx.server.path}")
    private String BASE_URL;

//    @Autowired
//    private TokenRepository tokenRepository;

//    @Autowired
//    private UserInfoService userInfoService;

    public RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(1000);
            requestFactory.setReadTimeout(1000);
            restTemplate = new RestTemplate(requestFactory);
        }
        return restTemplate;
    }

    public void getOpenCode(String url){
        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET, URI.create(url));
        ResponseEntity responseEntity = getRestTemplate().exchange(requestEntity, String.class);
        String result = responseEntity.getBody().toString();
        System.out.println(">>>>>>>"+result);
//        getRestTemplate().headForHeaders(URI.create(url));
    }

    public String getOpenid(String url){
        String result = getRestTemplate().getForObject(url, String.class);
        OpenIdModel openIdModel = new Gson().fromJson(result, OpenIdModel.class);
        return openIdModel.getOpenid();
    }

    /**
     * 获取token
     *
     * @param email
     * @param pwd
     * @return
     */
//    public String getToken(String email, String pwd) {
//
//        String token = (String) redisUtil.get(email);
//
//        if(token==null){
//            LoginModel loginModel = new LoginModel();
//            loginModel.setEmail(email);
//            loginModel.setPassWord(pwd);
//
//            HttpEntity httpEntity = new HttpEntity(loginModel);
//
//            try {
//                ResponseEntity<String> resp = getRestTemplate().exchange(Api.LOGIN, HttpMethod.POST, httpEntity, String.class);
//                System.out.println(resp.getStatusCode() + "======" + resp.getStatusCodeValue());
//                List<String> cookies = resp.getHeaders().get("Set-Cookie");
//                token = cookies.get(0).split(";")[0];
//                redisUtil.set(email, token, 3600l);
//                System.out.println(token);
////                User user = new Gson().fromJson(resp.getBody(), User.class);
////                UserModel userModel = user.getUser();
////                userInfoService.saveUserInfo(userModel);
//                return token;
//            } catch (HttpClientErrorException e) {
//                e.printStackTrace();
//                return "";
//            } finally {
//            }
//        }
//        return token;
//    }

    /**
     * 获得token
     * @param openid
     * @param commonInterface
     * @return
     */
    public Object getToken(String openid, CommonInterface commonInterface) {

        System.out.println(">>>>openid="+openid);

        String token = redisService.getAnkeToken(openid);
        if(token == null){
            MyResponse myResponse = weChatLoginAnke(openid, true);
            token = ((LoginResponse)myResponse.getData()).getToken();
        }
        try {
            return commonInterface.call(token);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return commonInterface.call(token);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }


    private MyResponse handleException(HttpStatusCodeException e){
        MyResponse myResponse = new MyResponse();
        String resutl = e.getResponseBodyAsString();
        JsonObject jsonObject = new Gson().fromJson(resutl, JsonObject.class);
        String str = jsonObject.get("ReturnMessage").getAsString();
        System.out.println(resutl);
        myResponse.setCode(ResponseService.CODE_ERROR);
        myResponse.setMessage(str);
        return myResponse;
    }

    public MyResponse weChatBindAnke(String email, String openid){
        RegisterAnkeModel registerAnkeModel = new RegisterAnkeModel();
        registerAnkeModel.setEmail(email);
        registerAnkeModel.setOpenId(openid);

        HttpEntity httpEntity = new HttpEntity(registerAnkeModel);
        MyResponse myResponse = new MyResponse();

        try{
            ResponseEntity resp = getRestTemplate().exchange(Api.WECHANT_BIND_ANKE, HttpMethod.POST, httpEntity, String.class);
            System.out.println(resp.getStatusCode() + "======" + resp.getStatusCodeValue());
            myResponse.setCode(ResponseService.CODE_OK);
            myResponse.setMessage("已提交申请，等待管理员确认。");

            return myResponse;
        }catch (HttpStatusCodeException e){
            return handleException(e);
        }
    }

    public MyResponse weChatLoginAnke(String openId, boolean firstTime){
        LoginAnkeModel loginAnkeModel = new LoginAnkeModel();
        loginAnkeModel.setOpenId(openId);
        HttpEntity httpEntity = new HttpEntity(loginAnkeModel);
        String token = "";
        MyResponse myResponse = new MyResponse();

        try{
            ResponseEntity<String> resp = getRestTemplate().exchange(Api.WECHAT_LOGIN_ANKE, HttpMethod.POST, httpEntity, String.class);

            JsonObject result = new Gson().fromJson(resp.getBody().toString(), JsonObject.class);
//            String email = result.get("User").getAsJsonObject().get("Email").getAsString();
            JsonObject userObj = result.get("User").getAsJsonObject();

            UserModel userModel = new Gson().fromJson(userObj, UserModel.class);
            userInfoService.saveUserInfo(userModel, openId);

            String email = userModel.getEmail();
            System.out.println(resp.getStatusCode() + "======" + resp.getStatusCodeValue());
            List<String> cookies = resp.getHeaders().get("Set-Cookie");
            token = cookies.get(0).split(";")[0];
            redisService.setAnkeToken(openId, token);
            System.out.println(token);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setEmail(email);
            loginResponse.setToken(token);

            myResponse.setCode(ResponseService.CODE_OK);
            myResponse.setData(loginResponse);

            return myResponse;
        }catch (HttpStatusCodeException e){
            if(firstTime){
                weChatLoginAnke(openId, false);
            }
            return  handleException(e);
        }
    }

    /**
     * 获取任务列表
     *
     * @param token
     * @param type
     * @return
     * @throws Exception
     */
    private List<FeedbackModel> getFeedbackRecords2(String token, String type, String productId, boolean all, int page ,int pageSize) throws Exception {
        if(productId==null || productId.length()==1){
            productId = "00000000-0000-0000-0000-000000000000";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");

        FeedbackRecordPostModel feedbackRecordPostModel = new FeedbackRecordPostModel();
        feedbackRecordPostModel.setProductId(productId);
        feedbackRecordPostModel.setFeedbackTypeId("00000000-0000-0000-0000-000000000000");
        feedbackRecordPostModel.setCurrentPageNumber(page);
        feedbackRecordPostModel.setPageSize(pageSize);
        HttpEntity httpEntity = new HttpEntity(feedbackRecordPostModel, headers);
        ResponseEntity<String> res = null;
        if(all){
            if ("1".equals(type)) {
                res = getRestTemplate().exchange(Api.QuiryAllOpenFeedbackRecords, HttpMethod.POST, httpEntity, String.class);
            } else if ("2".equals(type)) {
                res = getRestTemplate().exchange(Api.QuiryAllAllocatedFeedbackRecords, HttpMethod.POST, httpEntity, String.class);
            } else if ("3".equals(type)) {
                res = getRestTemplate().exchange(Api.QuiryAllProcessedFeedbackRecords, HttpMethod.POST, httpEntity, String.class);
            } else if ("4".equals(type)) {
                res = getRestTemplate().exchange(Api.QuiryAllClosedFeedbackRecords, HttpMethod.POST, httpEntity, String.class);
            }
        }else {
            if ("1".equals(type)) {
                res = getRestTemplate().exchange(Api.QuiryOpenFeedbackRecords, HttpMethod.POST, httpEntity, String.class);
            } else if ("2".equals(type)) {
                res = getRestTemplate().exchange(Api.QuiryAllocatedFeedbackRecords, HttpMethod.POST, httpEntity, String.class);
            } else if ("3".equals(type)) {
                res = getRestTemplate().exchange(Api.QuiryProcessedFeedbackRecords, HttpMethod.POST, httpEntity, String.class);
            } else if ("4".equals(type)) {
                res = getRestTemplate().exchange(Api.QuiryClosedFeedbackRecords, HttpMethod.POST, httpEntity, String.class);
            }
        }

        String body = res.getBody();
        System.out.println(">>>>>>"+body);

        return new Gson().fromJson(body, FeedbackRecordModel.class).getFeedbackRecords();
    }

    public List<FeedbackModel> getFeedbackRecords(String openid, final String type, final String productId, final boolean all, final int page, final int pageSize) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getFeedbackRecords2(token, type, productId, all, page, pageSize);
            }
        });
        return object == null ? null : (List<FeedbackModel>) object;
    }

    private FeedbackRecordModel getFeedbackRecord2(String token, String feedbackRecordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_FEEDBACK_RECORD + "?feedbackRecordId=" + feedbackRecordId, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), FeedbackRecordModel.class);
    }

    public FeedbackRecordModel getFeedbackRecord(String openid, final String feedbackRecordId) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getFeedbackRecord2(token, feedbackRecordId);
            }
        });
        return object == null ? null : (FeedbackRecordModel) object;
    }

    private OssInfo getReadStsToken2(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_READ_STS_TOKEN, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), OssInfo.class);
    }

    public OssInfo getReadStsToken(String openid) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getReadStsToken2(token);
            }
        });
        return object == null ? null : (OssInfo) object;
    }


    private OssInfo getWriteStsToken2(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(res.getBody());
        return new Gson().fromJson(res.getBody(), OssInfo.class);
    }

    public OssInfo getWriteStsToken(final String url, String openid) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getWriteStsToken2(url, token);
            }
        });
        return object == null ? null : (OssInfo) object;
    }

    private FeedbackType getFeedbackTypes2(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_FEEDBACK_TYPES, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), FeedbackType.class);
    }

    public FeedbackType getFeedbackTypes(String openid) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getFeedbackTypes2(token);
            }
        });
        return object == null ? null : (FeedbackType) object;
    }

    private AllUserData getEngineers2(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_ENGINEERS, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), AllUserData.class);
    }

    public AllUserData getEngineers(String openid) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getEngineers2(token);
            }
        });
        return object == null ? null : (AllUserData) object;
    }

    private AllDeviceData getValidProducts2(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_VALID_PRODUCTS, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), AllDeviceData.class);
    }

    public AllDeviceData getValidProducts(String openid) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getValidProducts2(token);
            }
        });
        return object == null ? null : (AllDeviceData) object;
    }

    private FixType getFixTypes2(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_FIX_TYPES, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), FixType.class);
    }


    public FixType getFixTypes(String openid) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getFixTypes2(token);
            }
        });
        return object == null ? null : (FixType) object;
    }

    private FeedbackModel createFeedbackRecord2(String token, RequestFeedbackModel requestFeedbackModel) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestFeedbackModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.CREATE_FEEDBACK_RECORD, HttpMethod.POST, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), FeedbackRecordModel.class).getFeedbackRecord();
    }

    public FeedbackModel createFeedbackRecord(String openid, final RequestFeedbackModel requestFeedbackModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return createFeedbackRecord2(token, requestFeedbackModel);
            }
        });
        return object == null ? null : (FeedbackModel) object;
    }


    private String deleteFeedbackRecord2(String token, String feedbackRecordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.REMOVE_FEEDBACK_RECORD+"?feedbackRecordId="+feedbackRecordId, HttpMethod.GET, httpEntity, String.class);
        return res.getBody();
    }

    public String deleteFeedbackRecord(String openid, final String feedbackRecordId) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return deleteFeedbackRecord2(token, feedbackRecordId);
            }
        });
        return object == null ? null : (String) object;
    }



    private HttpStatus allocateFeedbackRecord2(String token, RequestFeedbackModel requestFeedbackModel) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestFeedbackModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.ALLOCATE_FEEDBACK_RECORD, HttpMethod.POST, httpEntity, String.class);
        return res.getStatusCode();
    }

    public HttpStatus allocateFeedbackRecord(String openid, final RequestFeedbackModel requestFeedbackModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return allocateFeedbackRecord2(token, requestFeedbackModel);
            }
        });
        return object == null ? null : (HttpStatus) object;
    }


    private HttpStatus closeFeedbackRecord2(String token, RequestFeedbackModel requestFeedbackModel) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestFeedbackModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.CLOSE_FEEDBACK_RECORD, HttpMethod.POST, httpEntity, String.class);
        return res.getStatusCode();
    }

    public HttpStatus closeFeedbackRecord(String openid, final RequestFeedbackModel requestFeedbackModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return closeFeedbackRecord2(token, requestFeedbackModel);
            }
        });
        return object == null ? null : (HttpStatus) object;
    }

    private HttpStatus openFeedbackRecord2(String token, RequestFeedbackModel requestFeedbackModel) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestFeedbackModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.OPEN_FEEDBACK_RECORD, HttpMethod.POST, httpEntity, String.class);
        return res.getStatusCode();
    }

    public HttpStatus openFeedbackRecord(String openid, final RequestFeedbackModel requestFeedbackModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return openFeedbackRecord2(token, requestFeedbackModel);
            }
        });
        return object == null ? null : (HttpStatus) object;
    }


    private FixModel createMaintenanceRecord2(String token, FixModel fixModel) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(fixModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.CREATE_MAINTENANCE_RECORD, HttpMethod.POST, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), FixRecordModel.class).getMaintenanceRecord();
    }

    public FixModel createMaintenanceRecord(String openid, final FixModel fixModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return createMaintenanceRecord2(token, fixModel);
            }
        });
        return object == null ? null : (FixModel) object;
    }


    private FixRecordModel getMaintenanceRecordByFeedbackId2(String token, String feedbackRecordId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_MAINTENANCE_RECORD_BY_FEEDBACK_RECORD+"?feedbackRecordId="+feedbackRecordId, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), FixRecordModel.class);
    }

    public FixRecordModel getMaintenanceRecordByFeedbackId(String openid, final String feedbackRecordId) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getMaintenanceRecordByFeedbackId2(token, feedbackRecordId);
            }
        });
        return object == null ? null : (FixRecordModel) object;
    }

    private AllSaleData getProductSales2(String token, int type){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);

        String url = "";
        switch (type){
            case 1:
                url = Api.GET_FOR_DELIVERING_PRODUCTS_SALES;
                break;
            case 2:
                url = Api.GET_FOR_INSTALL_PRODUCTS_SALES;
                break;
            case 3:
                url = Api.GET_FOR_TRAIN_PRODUCTS_SALES;
                break;
        }

        ResponseEntity<String> res = getRestTemplate().exchange(url, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), AllSaleData.class);
    }

    /**
     *
     * @param openid
     * @param type 1:可以发货的产品，2:可以安装的产品，3:可以培训的产品
     * @return
     */
    public AllSaleData getProductSales(String openid, final int type) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getProductSales2(token, type);
            }
        });
        return object == null ? null : (AllSaleData) object;
    }

    private DeliverRecordModel createDeliverRecord2(String token, DeliverModel deliverModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(deliverModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.CREATE_DELIVER_RECORD, HttpMethod.POST, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), DeliverRecordModel.class);
    }

    public DeliverRecordModel createDeliverRecord(String openid, final DeliverModel deliverModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return createDeliverRecord2(token, deliverModel);
            }
        });
        return object == null ? null : (DeliverRecordModel) object;
    }

    private InstallRecordModel createInstallRecord2(String token, InstallModel installModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(installModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.CREATE_INSTALL_RECORD, HttpMethod.POST, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), InstallRecordModel.class);
    }

    public InstallRecordModel createInstallRecord(String openid, final InstallModel installModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return createInstallRecord2(token, installModel);
            }
        });
        return object == null ? null : (InstallRecordModel) object;
    }

    private TrainRecordModel createTrainRecord2(String token, TrainModel trainModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(trainModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.CREATE_TRAIN_RECORD, HttpMethod.POST, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), TrainRecordModel.class);
    }

    public TrainRecordModel createTrainRecord(String openid, final TrainModel trainModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return createTrainRecord2(token, trainModel);
            }
        });
        return object == null ? null : (TrainRecordModel) object;
    }

    private AllDeviceData getProductsByUser2(String token, RequestModel requestModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        System.out.println("token="+token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_PRODUCTS_BY_USER, HttpMethod.POST, httpEntity, String.class);
        String body = res.getBody();
        System.out.println(body);
        return new Gson().fromJson(body, AllDeviceData.class);
    }

    public AllDeviceData getProductsByUser(String openid, final RequestModel requestModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getProductsByUser2(token, requestModel);
            }
        });
        return object == null ? null : (AllDeviceData) object;
    }

    private AllDeviceData quiryValidProducts2(String token, String search){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        System.out.println("token="+token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.QUIRY_VALID_PRODUCTS+"?queryCondition="+search, HttpMethod.GET, httpEntity, String.class);
        String body = res.getBody();
        System.out.println(body);
        return new Gson().fromJson(body, AllDeviceData.class);
    }

    public AllDeviceData quiryValidProducts(String openid, final String search) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return quiryValidProducts2(token, search);
            }
        });
        return object == null ? null : (AllDeviceData) object;
    }


    private DeliverRecordModel getDeliverRecordByProduct2(String token, String productId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_DELIVER_RECORD_BY_PRODUCT+"?productId="+productId, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), DeliverRecordModel.class);
    }

    public DeliverRecordModel getDeliverRecordByProduct(String openid, final String productId) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getDeliverRecordByProduct2(token, productId);
            }
        });
        return object == null ? null : (DeliverRecordModel) object;
    }

    private InstallRecordModel getInstallRecordByProduct2(String token, String productId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_INSTALLATION_RECORD_BY_PRODUCT+"?productId="+productId, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), InstallRecordModel.class);
    }

    public InstallRecordModel getInstallRecordByProduct(String openid, final String productId) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getInstallRecordByProduct2(token, productId);
            }
        });
        return object == null ? null : (InstallRecordModel) object;
    }

    private TrainRecordModel getTrainRecordByProduct2(String token, String productId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.GET_TRAIN_RECORD_BY_PRODUCT+"?productId="+productId, HttpMethod.GET, httpEntity, String.class);
        return new Gson().fromJson(res.getBody(), TrainRecordModel.class);
    }

    public TrainRecordModel getTrainRecordByProduct(String openid, final String productId) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getTrainRecordByProduct2(token, productId);
            }
        });
        return object == null ? null : (TrainRecordModel) object;
    }

    private InstallRecordModel getInstallRecordByUser2(String token, RequestModel requestModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.INSTALL_RECORD_BY_USER, HttpMethod.POST, httpEntity, String.class);

        String body = res.getBody();
        System.out.println(">>>>>"+body);

        return new Gson().fromJson(body, InstallRecordModel.class);
    }

    public InstallRecordModel getInstallRecordByUser(String openid, final RequestModel requestModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getInstallRecordByUser2(token, requestModel);
            }
        });
        return object == null ? null : (InstallRecordModel) object;
    }

    private DeliverRecordModel getDeliverRecordByUser2(String token, RequestModel requestModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.DELIVER_RECORD_BY_USER, HttpMethod.POST, httpEntity, String.class);

        return new Gson().fromJson(res.getBody(), DeliverRecordModel.class);
    }

    public DeliverRecordModel getDeliverRecordByUser(String openid, final RequestModel requestModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getDeliverRecordByUser2(token, requestModel);
            }
        });
        return object == null ? null : (DeliverRecordModel) object;
    }

    private TrainRecordModel getTrainRecordByUser2(String token, RequestModel requestModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.TRAIN_RECORD_BY_USER, HttpMethod.POST, httpEntity, String.class);

        return new Gson().fromJson(res.getBody(), TrainRecordModel.class);
    }

    public TrainRecordModel getTrainRecordByUser(String openid, final RequestModel requestModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getTrainRecordByUser2(token, requestModel);
            }
        });
        return object == null ? null : (TrainRecordModel) object;
    }

    private ClientRecordModel getClientRecord2(String token, RequestModel requestModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.CLIENTS_RECORD, HttpMethod.POST, httpEntity, String.class);

        return new Gson().fromJson(res.getBody(), ClientRecordModel.class);
    }

    public ClientRecordModel getClientRecord(String openid, final RequestModel requestModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return getClientRecord2(token, requestModel);
            }
        });
        return object == null ? null : (ClientRecordModel) object;
    }

    private SaleRecordModel validSales2(String token, RequestModel requestModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.VALID_SALES, HttpMethod.POST, httpEntity, String.class);

        return new Gson().fromJson(res.getBody(), SaleRecordModel.class);
    }

    public SaleRecordModel validSales(String openid, final RequestModel requestModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return validSales2(token, requestModel);
            }
        });
        return object == null ? null : (SaleRecordModel) object;
    }


    private SaleRecordModel searchValidSales2(String token, String search, String expirationDate){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        String url = Api.SEARCH_VALID_SALES;
        Map<String, String> params = new HashMap<>();
        if(expirationDate!=null && expirationDate.length()>0){
           params.put("expirationDate", expirationDate);
        }
        if(search!=null && search.length()>0){
            params.put("deviceIdOrClientName", search);
        }

        String paramsStr = MapUtil.buildOrderParam(params);

        System.out.println(paramsStr);

        ResponseEntity<String> res = getRestTemplate().exchange(Api.SEARCH_VALID_SALES+"?"+ paramsStr, HttpMethod.GET, httpEntity, String.class);

        return new Gson().fromJson(res.getBody(), SaleRecordModel.class);
    }

    public SaleRecordModel searchValidSales(String openid, final String search, final String expirationDate) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return searchValidSales2(token, search, expirationDate);
            }
        });
        return object == null ? null : (SaleRecordModel) object;
    }

    private UpdateRegisterCodeResponse updateRegisterCodeDeadline2(String token, UpdateRegisterCodeRequest requestModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestModel, headers);

        System.out.println(">>>"+token);

        try{
            ResponseEntity<String> res = getRestTemplate().exchange(Api.UPDATE_REGISTER_DEADLINE, HttpMethod.POST, httpEntity, String.class);
            return new Gson().fromJson(res.getBody(), UpdateRegisterCodeResponse.class);
        }catch (HttpStatusCodeException e){
            String result = e.getResponseBodyAsString();
            return new Gson().fromJson(result, UpdateRegisterCodeResponse.class);
        }

    }

    public UpdateRegisterCodeResponse updateRegisterCodeDeadline(String openid, final UpdateRegisterCodeRequest requestModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return updateRegisterCodeDeadline2(token, requestModel);
            }
        });
        return object == null ? null : (UpdateRegisterCodeResponse) object;
    }

    private String newClientRecord2(String token, boolean create, RequestClientModel requestClientModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(requestClientModel, headers);

        System.out.println(">>>"+token);

        try{
            if(create){
                ResponseEntity<String> res = getRestTemplate().exchange(Api.CREATE_CLIENT, HttpMethod.POST, httpEntity, String.class);
                return res.getBody();
            }else {
                ResponseEntity<String> res = getRestTemplate().exchange(Api.UPDATE_CLIENT, HttpMethod.POST, httpEntity, String.class);
                return res.getBody();
            }
        }catch (HttpStatusCodeException e){
            String result = e.getResponseBodyAsString();
            return result;
        }

    }

    public String newClientRecord(String openid, final boolean create, final RequestClientModel requestClientModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return newClientRecord2(token, create, requestClientModel);
            }
        });
        return object == null ? null : (String) object;
    }

    private String removeClient2(String token, String id){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);

        System.out.println(">>>"+token);

        try{
            ResponseEntity<String> res = getRestTemplate().exchange(Api.REMOVE_CLIENT+"?clientId="+ id, HttpMethod.GET, httpEntity, String.class);
            return res.getBody();
        }catch (HttpStatusCodeException e){
            String result = e.getResponseBodyAsString();
            return result;
        }

    }

    public String removeClient(String openid, final String id) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return removeClient2(token, id);
            }
        });
        return object == null ? null : (String) object;
    }

    private ResponseAnkeMenus myMenus2(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.MY_MENUS, HttpMethod.GET, httpEntity, String.class);

        return new Gson().fromJson(res.getBody(), ResponseAnkeMenus.class);
    }

    public ResponseAnkeMenus myMenus(String openid) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return myMenus2(token);
            }
        });
        return object == null ? null : (ResponseAnkeMenus) object;
    }

    private MessagesModel messagesByUser2(String token, MessageRequestModel messageRequestModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(messageRequestModel,headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.MESSAGE_BY_USER, HttpMethod.POST, httpEntity, String.class);

        return new Gson().fromJson(res.getBody(), MessagesModel.class);
    }

    public MessagesModel messagesByUser(String openid, final MessageRequestModel messageRequestModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return messagesByUser2(token, messageRequestModel);
            }
        });
        return object == null ? null : (MessagesModel) object;
    }

    private int messageDelete2(String token, String messageId, String userId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.MESSAGE_DELETE+"?messageId="+messageId+"&userId="+userId, HttpMethod.GET, httpEntity, String.class);
        return res.getStatusCode().value();
    }

    public int messageDelete(String openid, final String messageId, final String userId) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return messageDelete2(token, messageId, userId);
            }
        });
        return object == null ? null : (int) object;
    }

    private int messageRead2(String token, MsgRequestModel msgRequestModel){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(msgRequestModel, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(Api.MESSAGE_READ, HttpMethod.POST, httpEntity, String.class);
        return res.getStatusCode().value();
    }

    public int messageRead(String openid, final MsgRequestModel msgRequestModel) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return messageRead2(token, msgRequestModel);
            }
        });
        return object == null ? null : (int) object;
    }

    public MsgTemplateResponse sendMsg(String accessToken, MsgTemplateRequestModel msgTemplateRequestModel, String toUrl){
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;

        MsgTemplateModel msgTemplateModel = new MsgTemplateModel();
        msgTemplateModel.setUrl(toUrl);
        msgTemplateModel.setTemplate_id("8lk_Moy1FxNvumEB0k_kwz79rMTMucD5jLSJVYrlWLY");
        msgTemplateModel.setTopcolor("#0073FF");
        MsgTemplateDataModel msgTemplateDataModel = new MsgTemplateDataModel();
        msgTemplateDataModel.setFirst(new MsgTemplateDataItemModel(msgTemplateRequestModel.getDevice(), "#0073FF"));
        msgTemplateDataModel.setKeyword1(new MsgTemplateDataItemModel(msgTemplateRequestModel.getType(), "#0073FF"));
        msgTemplateDataModel.setKeyword2(new MsgTemplateDataItemModel(msgTemplateRequestModel.getTime(), "#0073FF"));
        msgTemplateDataModel.setRemark(new MsgTemplateDataItemModel("来自: "+msgTemplateRequestModel.getSender()+"\n标题: "+msgTemplateRequestModel.getTitle()+"\n内容: "+msgTemplateRequestModel.getContent(), "#000000"));
        msgTemplateModel.setData(msgTemplateDataModel);

        for (int i = 0; i < msgTemplateRequestModel.getOpenids().size(); i++) {
            msgTemplateModel.setTouser(msgTemplateRequestModel.getOpenids().get(i).trim());
            HttpEntity httpEntity = new HttpEntity(msgTemplateModel);
            ResponseEntity<String> resp = getRestTemplate().exchange(url, HttpMethod.POST, httpEntity, String.class);
            MsgTemplateResponse result = new Gson().fromJson(resp.getBody().toString(), MsgTemplateResponse.class);
        }

        return new MsgTemplateResponse(0, "发送成功", "msgid");
    }


    /**
     * 通过serverid从微信获取图片
     *
     * @param url
     * @return
     */
    public File getWXFile(String url, String imageName) {
        System.out.println(url);
//        restTemplate.getForObject(url, InputStream.class);
        File file = new File(imageName);
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = new URL(url).openStream();
            byte[] buffer = new byte[4096];
            int count = 0;
            fos = new FileOutputStream(file);
            while ((count = is.read(buffer)) > 0)/*将输入流以字节的形式读取并写入buffer中*/ {
                fos.write(buffer, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
        return file;
    }

    /**
     * 通过serverid从微信获取图片
     *
     * @param url
     * @return
     */
    public InputStream getWXInputStream(String url) {
        System.out.println(url);
        try {
            InputStream is = new URL(url).openStream();
            return is;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String deleteImgs2(String token, List<String> recordIds, int type) {
        String url = "";
        if (type == ConstantUtil.UPLOAD_TYPE_DEVICE_DELIVER) {
            url = Api.REMOVE_DELIVER_RECORD_REPORT;
        } else if (type == ConstantUtil.UPLOAD_TYPE_DEVICE_FEEDBACK) {
            url = Api.REMOVE_FEEDBACK_RECORD_REPORT;
        } else if (type == ConstantUtil.UPLOAD_TYPE_DEVICE_FIX) {
            url = Api.REMOVE_MAINTENANCE_RECORD_REPORT;
        } else if (type == ConstantUtil.UPLOAD_TYPE_DEVICE_INSTALL) {
            url = Api.REMOVE_INSTALLATION_RECORD_REPORT;
        } else if (type == ConstantUtil.UPLOAD_TYPE_DEVICE_TRAIN) {
            url = Api.REMOVE_TRAINING_RECORD_REPORT;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", token);
        headers.set("X-Requested-With", "XMLHttpRequest");
        HttpEntity httpEntity = new HttpEntity(recordIds, headers);
        ResponseEntity<String> res = getRestTemplate().exchange(url, HttpMethod.POST, httpEntity, String.class);
        return res.getBody();
    }

    public String deleteImgs(String openid, final List<String> recordIds, final int type) {
        Object object = getToken(openid, new CommonInterface() {
            @Override
            public Object call(String token) throws Exception {
                return deleteImgs2(token, recordIds, type);
            }
        });
        return object == null ? null : (String) object;
    }


}
