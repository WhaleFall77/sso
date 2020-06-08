package com.infinova.sso.exception;

/**
 * 类功能简述：
 * 类功能详述：
 *
 * @author wangh
 * @date 2020/5/30 19:28
 */
public class CustomException extends RuntimeException {

    private String message;

    public CustomException() {
        super();
    }


    public CustomException(Exception e, String message) {
        super(e);
        this.message = message;
    }

    public CustomException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        if (message == null || message.length() == 0) {
            return super.getMessage();
        } else {
            return message;
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
