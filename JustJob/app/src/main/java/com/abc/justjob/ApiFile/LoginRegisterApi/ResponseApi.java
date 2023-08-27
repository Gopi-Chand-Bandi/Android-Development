package com.abc.justjob.ApiFile.LoginRegisterApi;

import com.google.gson.annotations.SerializedName;

public class ResponseApi {
    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("user_email")
    private String user_email;

    public ResponseApi(Boolean error, String message, String user_id, String user_name, String user_email) {
        this.error = error;
        this.message = message;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }
}
