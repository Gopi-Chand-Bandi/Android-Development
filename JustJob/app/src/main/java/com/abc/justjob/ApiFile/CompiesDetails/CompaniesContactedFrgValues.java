package com.abc.justjob.ApiFile.CompiesDetails;

import com.google.gson.annotations.SerializedName;

public class CompaniesContactedFrgValues {

    @SerializedName("date_and_time")
    private String date_and_time;

    @SerializedName("contacted_to_name")
    private String contacted_to_name;

    @SerializedName("contacted_by_mobile")
    private String contacted_by_mobile;

    @SerializedName("contacted_by_email")
    private String contacted_by_email;

//    @SerializedName("call_received")
//    private String call_received;
//
//    @SerializedName("email_sent")
//    private String email_sent;
//
//    @SerializedName("email_received")
//    private String email_received;

    public CompaniesContactedFrgValues(){}

    public CompaniesContactedFrgValues(String date_and_time,String contacted_to_name,String contacted_by_mobile,String contacted_by_email){

        this.date_and_time = date_and_time;
        this.contacted_to_name = contacted_to_name;
        this.contacted_by_mobile = contacted_by_mobile;
        this.contacted_by_email = contacted_by_email;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }

    public String getContacted_to_name() {
        return contacted_to_name;
    }

    public void setContacted_to_name(String contacted_to_name) {
        this.contacted_to_name = contacted_to_name;
    }

    public String getContacted_by_mobile() {
        return contacted_by_mobile;
    }

    public void setContacted_by_mobile(String contacted_by_mobile) {
        this.contacted_by_mobile = contacted_by_mobile;
    }

    public String getContacted_by_email() {
        return contacted_by_email;
    }

    public void setContacted_by_email(String contacted_by_email) {
        this.contacted_by_email = contacted_by_email;
    }

}
