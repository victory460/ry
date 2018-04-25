package com.anke.yingxiang.util;

import com.anke.yingxiang.domain.anke.TaskModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by qingxiangzhang on 2017/11/17.
 */
@Component("ajaxResponseUtil")
public class AjaxResponseUtil {

    public MyResponse getTaskUnsolvedSuccess(List<TaskModel> taskModels){
        return new MyResponse(MyHttpStatus.AJAX_SUCCESS.getIndex(), MyHttpStatus.AJAX_FAIL.getName(), taskModels);
    }

    public MyResponse getTaskUnsolvedFail(String content){
        return new MyResponse(MyHttpStatus.AJAX_FAIL.getIndex(), MyHttpStatus.AJAX_FAIL.getName(), content);
    }

}
