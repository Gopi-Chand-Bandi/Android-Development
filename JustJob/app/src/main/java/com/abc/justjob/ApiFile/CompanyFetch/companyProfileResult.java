package com.abc.justjob.ApiFile.CompanyFetch;

public class companyProfileResult {

    private boolean error;
    private String message;
    private String date_time;
    private String posted_job_number;
    private String cmp_uniq_id;
    private String cmp_id;
    private String cmp_full_name;
    private String cmp_contact;
    private String cmp_designation;
    private String cmp_email;
    private String cmp_company_name;
    private String cmp_head_office_location;
    private String cmp_industry;
    private String cmp_company_category;
    private String cmp_job_type;
    private String cmp_company_address;
    private String cmp_about_company;

    public companyProfileResult() {
    }

    public companyProfileResult(String date_time, String cmp_id, String cmp_full_name, String cmp_contact, String cmp_designation, String cmp_email, String cmp_company_name, String cmp_head_office_location, String cmp_industry, String cmp_company_category, String cmp_job_type, String cmp_company_address, String cmp_about_company) {
        this.date_time = date_time;
        this.cmp_id = cmp_id;
        this.cmp_full_name = cmp_full_name;
        this.cmp_contact = cmp_contact;
        this.cmp_designation = cmp_designation;
        this.cmp_email = cmp_email;
        this.cmp_company_name = cmp_company_name;
        this.cmp_head_office_location = cmp_head_office_location;
        this.cmp_industry = cmp_industry;
        this.cmp_company_category = cmp_company_category;
        this.cmp_job_type = cmp_job_type;
        this.cmp_company_address = cmp_company_address;
        this.cmp_about_company = cmp_about_company;
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

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getPosted_job_number() {
        return posted_job_number;
    }

    public void setPosted_job_number(String posted_job_number) {
        this.posted_job_number = posted_job_number;
    }

    public String getCmp_uniq_id() {
        return cmp_uniq_id;
    }

    public void setCmp_uniq_id(String cmp_uniq_id) {
        this.cmp_uniq_id = cmp_uniq_id;
    }

    public String getCmp_id() {
        return cmp_id;
    }

    public void setCmp_id(String cmp_id) {
        this.cmp_id = cmp_id;
    }

    public String getCmp_full_name() {
        return cmp_full_name;
    }

    public void setCmp_full_name(String cmp_full_name) {
        this.cmp_full_name = cmp_full_name;
    }

    public String getCmp_contact() {
        return cmp_contact;
    }

    public void setCmp_contact(String cmp_contact) {
        this.cmp_contact = cmp_contact;
    }

    public String getCmp_designation() {
        return cmp_designation;
    }

    public void setCmp_designation(String cmp_designation) {
        this.cmp_designation = cmp_designation;
    }

    public String getCmp_email() {
        return cmp_email;
    }

    public void setCmp_email(String cmp_email) {
        this.cmp_email = cmp_email;
    }

    public String getCmp_company_name() {
        return cmp_company_name;
    }

    public void setCmp_company_name(String cmp_company_name) {
        this.cmp_company_name = cmp_company_name;
    }

    public String getCmp_head_office_location() {
        return cmp_head_office_location;
    }

    public void setCmp_head_office_location(String cmp_head_office_location) {
        this.cmp_head_office_location = cmp_head_office_location;
    }

    public String getCmp_industry() {
        return cmp_industry;
    }

    public void setCmp_industry(String cmp_industry) {
        this.cmp_industry = cmp_industry;
    }

    public String getCmp_company_category() {
        return cmp_company_category;
    }

    public void setCmp_company_category(String cmp_company_category) {
        this.cmp_company_category = cmp_company_category;
    }

    public String getCmp_job_type() {
        return cmp_job_type;
    }

    public void setCmp_job_type(String cmp_job_type) {
        this.cmp_job_type = cmp_job_type;
    }

    public String getCmp_company_address() {
        return cmp_company_address;
    }

    public void setCmp_company_address(String cmp_company_address) {
        this.cmp_company_address = cmp_company_address;
    }

    public String getCmp_about_company() {
        return cmp_about_company;
    }

    public void setCmp_about_company(String cmp_about_company) {
        this.cmp_about_company = cmp_about_company;
    }
}
