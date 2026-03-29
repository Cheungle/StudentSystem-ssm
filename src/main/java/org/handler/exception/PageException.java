package org.handler.exception;

public class PageException extends BaseException {
    public PageException() {
        super("页码异常");
    }

    public PageException(String msg) {
        super(msg);
    }

}
