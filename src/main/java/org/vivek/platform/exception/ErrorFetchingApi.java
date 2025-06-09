package org.vivek.platform.exception;

public class ErrorFetchingApi extends RuntimeException {
    public ErrorFetchingApi(String message) {
        super(message);
    }
}
