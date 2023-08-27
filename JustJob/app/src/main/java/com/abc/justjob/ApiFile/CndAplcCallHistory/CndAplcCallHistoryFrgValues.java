package com.abc.justjob.ApiFile.CndAplcCallHistory;

import com.google.gson.annotations.SerializedName;

public class CndAplcCallHistoryFrgValues {

    @SerializedName("date_and_time")
    private String date_and_time;

    @SerializedName("contact_by_id")
    private String contact_by_id;

    @SerializedName("contact_by_name")
    private String contact_by_name;

    @SerializedName("contact_by_email")
    private String contact_by_email;

    @SerializedName("jobid")
    private String jobid;

    @SerializedName("job_role")
    private String job_role;

    @SerializedName("contacted_to_id")
    private String contacted_to_id;

    @SerializedName("contacted_to_name")
    private String contacted_to_name;

    @SerializedName("contacted_to_email")
    private String contacted_to_email;

    @SerializedName("contacted_by_mobile")
    private String contacted_by_mobile;

    @SerializedName("contacted_by_email")
    private String contacted_by_email;

    @SerializedName("contacted_by_resume")
    private String contacted_by_resume;

    @SerializedName("state_from")
    private String state_from;

    @SerializedName("state_to")
    private String state_to;

    public CndAplcCallHistoryFrgValues(){}

    public CndAplcCallHistoryFrgValues(String date_and_time, String contact_by_id, String contact_by_name,
                                       String contact_by_email, String jobid, String job_role, String contacted_to_id,
                                       String contacted_to_name, String contacted_to_email, String contacted_by_mobile,
                                       String contacted_by_email, String contacted_by_resume, String state_from, String state_to){

        this.date_and_time = date_and_time;
        this.contact_by_id = contact_by_id;
        this.contact_by_name = contact_by_name;
        this.contact_by_email = contact_by_email;
        this.jobid = jobid;
        this.job_role = job_role;
        this.contacted_to_id = contacted_to_id;
        this.contacted_to_name = contacted_to_name;
        this.contacted_to_email = contacted_to_email;
        this.contacted_by_mobile = contacted_by_mobile;
        this.contacted_by_email = contacted_by_email;
        this.contacted_by_resume = contacted_by_resume;
        this.state_from = state_from;
        this.state_to = state_to;

    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }

    public String getContact_by_id() {
        return contact_by_id;
    }

    public void setContact_by_id(String contact_by_id) {
        this.contact_by_id = contact_by_id;
    }

    public String getContact_by_name() {
        return contact_by_name;
    }

    public void setContact_by_name(String contact_by_name) {
        this.contact_by_name = contact_by_name;
    }

    public String getContact_by_email() {
        return contact_by_email;
    }

    public void setContact_by_email(String contact_by_email) {
        this.contact_by_email = contact_by_email;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJob_role() {
        return job_role;
    }

    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }

    public String getContacted_to_id() {
        return contacted_to_id;
    }

    public void setContacted_to_id(String contacted_to_id) {
        this.contacted_to_id = contacted_to_id;
    }

    public String getContacted_to_name() {
        return contacted_to_name;
    }

    public void setContacted_to_name(String contacted_to_name) {
        this.contacted_to_name = contacted_to_name;
    }

    public String getContacted_to_email() {
        return contacted_to_email;
    }

    public void setContacted_to_email(String contacted_by_email) {
        this.contacted_to_email = contacted_to_email;
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

    public String getContacted_by_resume() {
        return contacted_by_resume;
    }

    public void setContacted_by_resume(String contacted_by_resume) {
        this.contacted_by_resume = contacted_by_resume;
    }

    public String getState_from() {
        return state_from;
    }

    public void setState_from(String state_from) {
        this.state_from = state_from;
    }

    public String getState_to() {
        return state_to;
    }

    public void setState_to(String state_to) {
        this.state_to = state_to;
    }

}
