package com.ryl.framework.base;

/**
 * @author: ryl
 * @description:
 * @date: 2020-07-16 16:58:22
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Integer errorCode;
    private String errorMessage;


    /**
     * 构造新实例。
     */
    public ServiceException() {
        super();
    }

    /**
     * 用给定的异常信息构造新实例。
     *
     * @param errorMessage 异常信息。
     */
    public ServiceException(String errorMessage) {
        super((String) null);
        this.errorMessage = errorMessage;
    }

    /**
     * 用表示异常原因的对象构造新实例。
     *
     * @param cause 异常原因。
     */
    public ServiceException(Throwable cause) {
        super(null, cause);
    }

    /**
     * 用异常消息和表示异常原因的对象构造新实例。
     *
     * @param errorMessage 异常信息。
     * @param cause        异常原因。
     */
    public ServiceException(String errorMessage, Throwable cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
    }

    /**
     * 用异常消息和表示异常原因及其他信息的对象构造新实例。
     *
     * @param errorCode    错误代码。
     * @param errorMessage 异常信息。
     * @param cause        异常原因。
     */
    public ServiceException(Integer errorCode, String errorMessage, Throwable cause) {
        this(errorMessage, cause);
        this.errorCode = errorCode;
    }

    /**
     * 用异常消息和表示异常原因及其他信息的对象构造新实例。
     *
     * @param errorCode    错误代码。
     * @param errorMessage 异常信息。
     */
    public ServiceException(Integer errorCode, String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    /**
     * 返回异常信息。
     *
     * @return 异常信息。
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 返回错误代码的字符串表示。
     *
     * @return 错误代码的字符串表示。
     */
    public Integer getErrorCode() {
        return errorCode;
    }

}
