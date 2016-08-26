package com.github.osvaldopina.signedcontract.enforcer;

public class Error {

    private String message;


    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
