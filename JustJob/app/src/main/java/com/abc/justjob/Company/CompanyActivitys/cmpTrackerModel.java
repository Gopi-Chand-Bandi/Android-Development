package com.abc.justjob.Company.CompanyActivitys;

import com.google.gson.annotations.SerializedName;

public class cmpTrackerModel {

    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String message;
    @SerializedName("tracker_pojo_post_job")
    private String tracker_pojo_post_job;
    @SerializedName("tracker_pojo_balance")
    private String tracker_pojo_balance;
    @SerializedName("tracker_pojo_deleted")
    private String tracker_pojo_deleted;
    @SerializedName("tracker_aplc_viewed")
    private String tracker_aplc_viewed;
    @SerializedName("tracker_aplc_received")
    private String tracker_aplc_received;
    @SerializedName("tracker_aplc_selected")
    private String tracker_aplc_selected;
    @SerializedName("tracker_aplc_rejected")
    private String tracker_aplc_rejected;
    @SerializedName("tracker_aplc_balance")
    private String tracker_aplc_balance;
    @SerializedName("tracker_cnd_viewed")
    private String tracker_cnd_viewed;
    @SerializedName("tracker_cnd_balance")
    private String tracker_cnd_balance;

    public cmpTrackerModel() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTracker_pojo_post_job() {
        return tracker_pojo_post_job;
    }

    public void setTracker_pojo_post_job(String tracker_pojo_post_job) {
        this.tracker_pojo_post_job = tracker_pojo_post_job;
    }

    public String getTracker_pojo_balance() {
        return tracker_pojo_balance;
    }

    public void setTracker_pojo_balance(String tracker_pojo_balance) {
        this.tracker_pojo_balance = tracker_pojo_balance;
    }

    public String getTracker_pojo_deleted() {
        return tracker_pojo_deleted;
    }

    public void setTracker_pojo_deleted(String tracker_pojo_deleted) {
        this.tracker_pojo_deleted = tracker_pojo_deleted;
    }

    public String getTracker_aplc_viewed() {
        return tracker_aplc_viewed;
    }

    public void setTracker_aplc_viewed(String tracker_aplc_viewed) {
        this.tracker_aplc_viewed = tracker_aplc_viewed;
    }

    public String getTracker_aplc_received() {
        return tracker_aplc_received;
    }

    public void setTracker_aplc_received(String tracker_aplc_received) {
        this.tracker_aplc_received = tracker_aplc_received;
    }

    public String getTracker_aplc_selected() {
        return tracker_aplc_selected;
    }

    public void setTracker_aplc_selected(String tracker_aplc_selected) {
        this.tracker_aplc_selected = tracker_aplc_selected;
    }

    public String getTracker_aplc_rejected() {
        return tracker_aplc_rejected;
    }

    public void setTracker_aplc_rejected(String tracker_aplc_rejected) {
        this.tracker_aplc_rejected = tracker_aplc_rejected;
    }

    public String getTracker_aplc_balance() {
        return tracker_aplc_balance;
    }

    public void setTracker_aplc_balance(String tracker_aplc_balance) {
        this.tracker_aplc_balance = tracker_aplc_balance;
    }

    public String getTracker_cnd_viewed() {
        return tracker_cnd_viewed;
    }

    public void setTracker_cnd_viewed(String tracker_cnd_viewed) {
        this.tracker_cnd_viewed = tracker_cnd_viewed;
    }

    public String getTracker_cnd_balance() {
        return tracker_cnd_balance;
    }

    public void setTracker_cnd_balance(String tracker_cnd_balance) {
        this.tracker_cnd_balance = tracker_cnd_balance;
    }
}
