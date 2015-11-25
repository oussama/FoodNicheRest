package com.foodniche.rest.model;

/**
 * Created by agdubrov on 11/24/15.
 */

public class DataResponse<T> extends BaseResponse {
    public T data;

    public DataResponse(String message) {
        super(message);
    }

    public DataResponse(String message, T data) {
        super(message);

        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
