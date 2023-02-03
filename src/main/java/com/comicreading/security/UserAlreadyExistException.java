package com.comicreading.security;

public class UserAlreadyExistException extends Exception {
    
    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(String arg0) {
        super(arg0);
    }

    public UserAlreadyExistException(Throwable arg0) {
        super(arg0);
    }

    public UserAlreadyExistException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public UserAlreadyExistException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }
}
