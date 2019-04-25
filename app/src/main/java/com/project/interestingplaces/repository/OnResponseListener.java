package com.project.interestingplaces.repository;

public interface OnResponseListener<T> {
    void onSuccessResponse(T value);

    void onFailureResponse(Throwable throwable);
}
