package com.ryl.framework.base;

import java.io.Serializable;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-16 10:36:06
 */
public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;
    private String message;
    private Integer status;
    private T data;

    private ResultModel(Integer code, String message) {
        this.success = code.equals(200);
        this.status = code;
        this.message = message;
    }

    private ResultModel(Integer code, String message, T data) {
        this.success = code.equals(200);
        this.status = code;
        this.message = message;
        this.data = data;
    }

    public static ResultModel success() {
        return new ResultModel(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage());
    }

    public static <T> ResultModel success(T data) {
        return new ResultModel(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), data);
    }

//    //防止与success(T data)有歧义,成功一般返回data;message就是成功,一般不变。建议使用ResultModel.success().setMessage("xxxx");
//    public static ResultModel success(String message) {
//        return new ResultModel(ResultStatus.SUCCESS.getCode(), message);
//    }

    public static <T> ResultModel success(T data, String message) {
        return new ResultModel(ResultStatus.SUCCESS.getCode(), message, data);
    }


    public static ResultModel fail() {
        return new ResultModel(ResultStatus.FAILURE.getCode(), ResultStatus.FAILURE.getMessage());
    }

    public static ResultModel fail(String message) {
        return new ResultModel(ResultStatus.FAILURE.getCode(), message);
    }

    //当返回data是String时会与fail(String message)有歧义。建议使用ResultModel.fail().setDate("xxxx");
    public static <T> ResultModel fail(T data) {
        return new ResultModel(ResultStatus.FAILURE.getCode(), ResultStatus.FAILURE.getMessage(), data);
    }

    public static <T> ResultModel fail(T data, String message) {
        return new ResultModel(ResultStatus.FAILURE.getCode(), message, data);
    }

    public static ResultModel fail(ResultStatus resultStatus) {
        return new ResultModel(resultStatus.getCode(), resultStatus.getMessage());
    }

    public static <T> ResultModel fail(T data, ResultStatus resultStatus) {
        return new ResultModel(resultStatus.getCode(), resultStatus.getMessage(), data);
    }

    public T getData() {
        return this.data;
    }

    public ResultModel setData(T data) {
        this.data = data;
        return this;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public ResultModel<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ResultModel<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatus() {
        return this.status;
    }

    public ResultModel<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String toString() {
        return "ResultModel{data = " + this.data + ", success = " + this.success + ", message = '" + this.message + "', status = " + this.status + "}";
    }

}
