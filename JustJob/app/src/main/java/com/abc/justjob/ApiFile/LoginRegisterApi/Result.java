package com.abc.justjob.ApiFile.LoginRegisterApi;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("isSelected")
    private String isSelected;

    @SerializedName("countTenBol")
    private Boolean countTenBol;

    @SerializedName("payedRowsCount")
    private int payedRowsCount;

    @SerializedName("planeExpire")
    private boolean planeExpire;

    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    /*@SerializedName("user")
    private Users users;*/

    @SerializedName("regUserId")
    private String users_id;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("user_email")
    private String user_email;

    @SerializedName("last_id")
    private String last_id;

    @SerializedName("responseReg")
    private boolean responseReg;

    @SerializedName("file_size")
    private int file_size;

    public Result(String isSelected,Boolean countTenBol, int payedRowsCount,Boolean error, String message, String users_id, String user_name, String user_email, String last_id, boolean responseReg) {
        this.isSelected = isSelected;
        this.countTenBol = countTenBol;
        this.payedRowsCount = payedRowsCount;
        this.error = error;
        this.message = message;
        this.users_id = users_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.last_id = last_id;
        this.responseReg = responseReg;
    }

    public boolean isPlaneExpire() {
        return planeExpire;
    }

    public void setPlaneExpire(boolean planeExpire) {
        this.planeExpire = planeExpire;
    }

    public String getSelected() {
        return isSelected;
    }

    public void setSelected(String selected) {
        isSelected = selected;
    }

    public Boolean getCountTenBol() {
        return countTenBol;
    }

    public void setCountTenBol(Boolean countTenBol) {
        this.countTenBol = countTenBol;
    }

    public int getPayedRowsCount() {
        return payedRowsCount;
    }

    public void setPayedRowsCount(int payedRowsCount) {
        this.payedRowsCount = payedRowsCount;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsers_id() {
        return users_id;
    }

    public void setUsers_id(String users_id) {
        this.users_id = users_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getLast_id() {
        return last_id;
    }

    public void setLast_id(String last_id) {
        this.last_id = last_id;
    }

    public boolean isResponseReg() {
        return responseReg;
    }

    public void setResponseReg(boolean responseReg) {
        this.responseReg = responseReg;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }
}
