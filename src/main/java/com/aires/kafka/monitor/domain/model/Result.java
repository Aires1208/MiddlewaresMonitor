package com.aires.kafka.monitor.domain.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ${10183966} on 12/6/16.
 */
public class Result {
    private String status = "OK";
    private JSONObject data;
    private String resMsg = "";


    public Result(String status, String resMsg) {
        this.status = status;
        this.resMsg = resMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", resMsg='" + resMsg + '\'' +
                '}';
    }
}
