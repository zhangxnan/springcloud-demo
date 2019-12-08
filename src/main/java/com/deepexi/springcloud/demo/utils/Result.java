package com.deepexi.springcloud.demo.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO </p>
 *
 * @author zhangnan
 * @date 2019-11-09 15:51
 */
public class Result {


    private Boolean success = true;

    private String errorMsg;

    private String errorCode = "200";


    private Map<String, String> fields = new HashMap<>();


    public static  Result getSuccessResult(){
        Result result =  new Result();
        result.setErrorMsg("成功");
        result.fields.put("结果", "成功");
        return result;
    }

    public static  Result getFailResult(String remark){
        Result result =  new Result();
        result.setErrorMsg("失败");
        result.fields.put("结果", remark);
        return result;
    }


    public Result(Boolean success, String errorMsg, String errorCode) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public Result() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
