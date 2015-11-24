package com.foodniche.api.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by agdubrov on 11/23/15.
 */
public class BaseResponse {

    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
