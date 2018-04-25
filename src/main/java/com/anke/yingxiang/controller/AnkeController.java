package com.anke.yingxiang.controller;


import com.anke.yingxiang.domain.MyResponse;
import com.anke.yingxiang.domain.oss.OssInfo;
import com.anke.yingxiang.domain.oss.OssToken;
import com.anke.yingxiang.rest.Api;
import com.anke.yingxiang.rest.RestService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("anke")
public class AnkeController {

    @Autowired
    private RestService restService;

    @Autowired
    private EntityManager entityManager;

//    @RequestMapping(value = "/token", method = {RequestMethod.GET, RequestMethod.POST})
//    public String getToken() {
//        String token = restService.getToken("zhangqingxiang@ankenj.com", "Zx19881023@");
//        return token;
//    }

    @RequestMapping(value = "/bindAnke", method = {RequestMethod.GET, RequestMethod.POST})
    public MyResponse bindAnke(@RequestParam(name = "email") String email, @RequestParam(name = "openid") String openid) {
        return restService.weChatBindAnke(email, openid);
    }
    
    @RequestMapping(value = "/loginAnke", method = {RequestMethod.GET, RequestMethod.POST})
    public MyResponse loginAnke(@RequestParam(name = "openid") String openId) {
        System.out.println(">>>>>>openId="+openId);
        return restService.weChatLoginAnke(openId, true);
    }

    @RequestMapping(value = "/countries", method = {RequestMethod.GET, RequestMethod.POST})
    public String assembleCountries(){

        Query query = entityManager.createNativeQuery("select chineseName, isoCode from country");
        List<Object[]> result = query.getResultList();
        List<Country> countries = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            countries.add(new Country((String)result.get(i)[0], (String) result.get(i)[1]));
        }

        return new Gson().toJson(countries);
    }

    @RequestMapping(value = "/areas", method = {RequestMethod.GET, RequestMethod.POST})
    public String assembleAreas(){
        List<Area> topAreas = topAreas();

        List<Area> result = new ArrayList<>();
        for (int i = 0; i < topAreas.size(); i++) {
            result.add(addSubAreas(topAreas.get(i)));
        }
        return new Gson().toJson(result);
    }

    private List<Area> topAreas(){
        Query query = entityManager.createNativeQuery("select id, name from area where level = 1");
        List<Object[]> result = query.getResultList();
        List<Area> areas = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Area area = new Area((int)result.get(i)[0], (String) result.get(i)[1]);
            areas.add(area);
        }

        Area qingxuanze = new Area(-1, "选择省份");
        List<Area> qingxuanzeSub = new ArrayList<>();
        Area area = new Area(-1, "请选择");
        qingxuanzeSub.add(area);
        qingxuanze.setAreas(qingxuanzeSub);
        areas.add(0, qingxuanze);

        return areas;
    }

    private Area addSubAreas(Area area){
        Query query = entityManager.createNativeQuery("select id, name from area where pid = "+area.getId());
        List<Object[]> result = query.getResultList();
        List<Area> areas = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Area one = new Area((int)result.get(i)[0], (String) result.get(i)[1]);
            addSubAreas(one);
            areas.add(one);
        }
        if(areas.size()>0){
            Area one = new Area(-1, "请选择");
            areas.add(0, one);
            area.setAreas(areas);
        }
        return area;
    }

    class Country{
        String name;
        String code;

        public Country(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    class Area{

        transient int id;
        String name;

        List<Area> sub;

        public Area(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Area> getAreas() {
            return sub;
        }

        public void setAreas(List<Area> areas) {
            this.sub = areas;
        }

        @Override
        public String toString() {
            return "Area{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


//    @RequestMapping("/writeToken")
//    public OssToken writeToken(){
//        OssInfo ossInfo = restService.getWriteStsToken(Api.GET_WRITE_DELIVER_RECORDS_STS_TOKEN, "zhangqingxiang@ankenj.com", "Zx19881023@");
//        return ossInfo.getData();
//    }


}
