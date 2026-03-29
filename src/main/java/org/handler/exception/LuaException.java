package org.handler.exception;

public class LuaException extends BaseException{
    public LuaException() {
        super("Lua异常");
    }
    public LuaException(String msg) {
        super(msg);
    }
}
