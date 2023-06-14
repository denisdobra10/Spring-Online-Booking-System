package com.doderasoft.onlinebooking.response;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RequestResponse {

    public static RequestResponse build(String requestType, String host, String messageToSend, boolean isFormatValid) {
        return new RequestResponse(requestType, host, messageToSend, isFormatValid);
    }

    public static RequestResponse build() {
        return new RequestResponse();
    }

    public static RequestResponse throwBadRequestResponse(String reason) {
        return RequestResponse.build()
                .setType("invalid")
                .setHost("localhost")
                .setMessage(reason)
                .setValid(false);
    }





    private String type;
    private String host;
    private String message;
    private boolean valid;

    public RequestResponse(String type, String host, String message, boolean valid) {
        this.type = type;
        this.host = host;
        this.message = message;
        this.valid = valid;
    }

    public RequestResponse(){}

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return valid;
    }

    public RequestResponse setType(String type) {
        this.type = type;
        return this;
    }

    public RequestResponse setHost(String host) {
        this.host = host;
        return this;
    }

    public RequestResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public RequestResponse setValid(boolean valid) {
        this.valid = valid;
        return this;
    }
}
