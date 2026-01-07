package com.adjt.chefmanagerapi.core.exceptions;

public abstract class BaseException extends RuntimeException {
    protected BaseException(String message) {
        super(message);
    }
}