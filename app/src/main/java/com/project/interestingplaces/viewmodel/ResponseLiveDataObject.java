package com.project.interestingplaces.viewmodel;

import retrofit2.Response;

public class ResponseLiveDataObject<T> {

    public ResponseLiveDataObject (T apiResponse, Throwable throwable, boolean isFailureResponse) {
        this.apiResponse = apiResponse;
        this.throwable = throwable;
        this.isFailureResponse = isFailureResponse;
    }

    private T apiResponse;
    private Throwable throwable;
    private boolean isFailureResponse;

    public T getApiResponse() {
        return apiResponse;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public boolean isFailureResponse() {
        return isFailureResponse;
    }

}
