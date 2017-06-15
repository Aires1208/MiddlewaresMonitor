package com.aires.kafka.monitor.domain.model;

import com.alibaba.fastjson.JSONObject;


/**
 * Created by 10183966 on 2016/10/10.
 */
public class ResultBuilder {
    private String status = "OK";
    private JSONObject data;
    private String message;

    protected ResultBuilder() {
    }

    public static ResultBuilder newResult() {
        return new ResultBuilder();
    }

    public static ResultBuilder newResult(String status, JSONObject data, String message) {
        ResultBuilder resultBuilder = new ResultBuilder();
        resultBuilder.status(status);
        resultBuilder.message(message);
        resultBuilder.data(data);
        return resultBuilder;
    }

    public ResultBuilder status(String status) {
        this.status = status;
        return this;
    }

    public ResultBuilder data(JSONObject data) {
        this.data = data;
        return this;
    }

    public ResultBuilder message(String message) {
        this.message = message;
        return this;
    }

    public Result build() {
        Result result = new Result(status, message);
        result.setData(data);
        return result;
    }

}
