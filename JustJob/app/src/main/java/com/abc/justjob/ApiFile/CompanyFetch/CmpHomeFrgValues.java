package com.abc.justjob.ApiFile.CompanyFetch;

import com.google.gson.annotations.SerializedName;

public class CmpHomeFrgValues {

    @SerializedName("cd_id")
    private String cd_id;

    @SerializedName("register_login_id")
    private String register_login_id;

    @SerializedName("cd_gender")
    private String cd_gender;

    @SerializedName("cd_full_name")
    private String cd_full_name;

    @SerializedName("cd_email")
    private String cd_email;

    @SerializedName("cd_contact_no")
    private String cd_contact_no;

    @SerializedName("cd_alter_contact")
    private String cd_alter_contact;

    @SerializedName("cd_state")
    private String cd_state;

    @SerializedName("cd_city")
    private String cd_city;

    @SerializedName("cd_current_location")
    private String cd_current_location;

    @SerializedName("cd_date_of_birth")
    private String cd_date_of_birth;

    @SerializedName("cd_communication")
    private String cd_communication;

    @SerializedName("cd_job_profile_one")
    private String cd_job_profile_one;

    @SerializedName("cd_job_designation_one")
    private String cd_job_designation_one;

    @SerializedName("cd_job_profile_two")
    private String cd_job_profile_two;

    @SerializedName("cd_job_designation_two")
    private String cd_job_designation_two;

    @SerializedName("cd_qualification_std")
    private String cd_qualification_std;

    @SerializedName("cd_qualification_stream")
    private String cd_qualification_stream;

    @SerializedName("cd_college_name")
    private String cd_college_name;

    @SerializedName("cd_qualification_start_date")
    private String cd_qualification_start_date;

    @SerializedName("cd_qualification_end_date")
    private String cd_qualification_end_date;

    @SerializedName("cd_fresher_intern_exp")
    private String cd_fresher_intern_exp;

    @SerializedName("cd_exp_job_industry")
    private String cd_exp_job_industry;

    @SerializedName("cd_exp_company_name")
    private String cd_exp_company_name;

    @SerializedName("cd_exp_current_salary")
    private String cd_exp_current_salary;

    @SerializedName("cd_exp_start_date")
    private String cd_exp_start_date;

    @SerializedName("cd_exp_end_date")
    private String cd_exp_end_date;

    @SerializedName("cd_employment_type")
    private String cd_employment_type;

    @SerializedName("cd_location_prefer")
    private String cd_location_prefer;

    @SerializedName("cd_languages")
    private String cd_languages;

    @SerializedName("cd_vehicle")
    private String cd_vehicle;

    @SerializedName("cd_licence")
    private String cd_licence;

    @SerializedName("cd_documents")
    private String cd_documents;

    @SerializedName("cd_skill")
    private String cd_skill;

    @SerializedName("cd_reference")
    private String cd_reference;

    @SerializedName("cd_resume_cv_url")
    private String cd_resume_cv_url;

    public CmpHomeFrgValues(String cd_id, String register_login_id, String cd_gender, String cd_full_name, String cd_email,
                            String cd_contact_no, String cd_alter_contact, String cd_state, String cd_city, String cd_current_location,
                            String cd_date_of_birth, String cd_communication, String cd_job_profile_one, String cd_job_designation_one,
                            String cd_job_profile_two, String cd_job_designation_two, String cd_qualification_std,
                            String cd_qualification_stream, String cd_college_name, String cd_qualification_start_date,
                            String cd_qualification_end_date, String cd_fresher_intern_exp, String cd_exp_job_industry,
                            String cd_exp_company_name, String cd_exp_current_salary, String cd_exp_start_date,
                            String cd_exp_end_date, String cd_employment_type, String cd_location_prefer,
                            String cd_languages, String cd_vehicle, String cd_licence, String cd_documents,
                            String cd_skill, String cd_reference, String cd_resume_cv_url) {
        this.cd_id = cd_id;
        this.register_login_id = register_login_id;
        this.cd_gender = cd_gender;
        this.cd_full_name = cd_full_name;
        this.cd_email = cd_email;
        this.cd_contact_no = cd_contact_no;
        this.cd_alter_contact = cd_alter_contact;
        this.cd_state = cd_state;
        this.cd_city = cd_city;
        this.cd_current_location = cd_current_location;
        this.cd_date_of_birth = cd_date_of_birth;
        this.cd_communication = cd_communication;
        this.cd_job_profile_one = cd_job_profile_one;
        this.cd_job_designation_one = cd_job_designation_one;
        this.cd_job_profile_two = cd_job_profile_two;
        this.cd_job_designation_two = cd_job_designation_two;
        this.cd_qualification_std = cd_qualification_std;
        this.cd_qualification_stream = cd_qualification_stream;
        this.cd_college_name = cd_college_name;
        this.cd_qualification_start_date = cd_qualification_start_date;
        this.cd_qualification_end_date = cd_qualification_end_date;
        this.cd_fresher_intern_exp = cd_fresher_intern_exp;
        this.cd_exp_job_industry = cd_exp_job_industry;
        this.cd_exp_company_name = cd_exp_company_name;
        this.cd_exp_current_salary = cd_exp_current_salary;
        this.cd_exp_start_date = cd_exp_start_date;
        this.cd_exp_end_date = cd_exp_end_date;
        this.cd_employment_type = cd_employment_type;
        this.cd_location_prefer = cd_location_prefer;
        this.cd_languages = cd_languages;
        this.cd_vehicle = cd_vehicle;
        this.cd_licence = cd_licence;
        this.cd_documents = cd_documents;
        this.cd_skill = cd_skill;
        this.cd_reference = cd_reference;
        this.cd_resume_cv_url = cd_resume_cv_url;
    }

    public String getCd_id() {
        return cd_id;
    }

    public void setCd_id(String cd_id) {
        this.cd_id = cd_id;
    }

    public String getRegister_login_id() {
        return register_login_id;
    }

    public void setRegister_login_id(String register_login_id) {
        this.register_login_id = register_login_id;
    }

    public String getCd_gender() {
        return cd_gender;
    }

    public void setCd_gender(String cd_gender) {
        this.cd_gender = cd_gender;
    }

    public String getCd_full_name() {
        return cd_full_name;
    }

    public void setCd_full_name(String cd_full_name) {
        this.cd_full_name = cd_full_name;
    }

    public String getCd_email() {
        return cd_email;
    }

    public void setCd_email(String cd_email) {
        this.cd_email = cd_email;
    }

    public String getCd_contact_no() {
        return cd_contact_no;
    }

    public void setCd_contact_no(String cd_contact_no) {
        this.cd_contact_no = cd_contact_no;
    }

    public String getCd_alter_contact() {
        return cd_alter_contact;
    }

    public void setCd_alter_contact(String cd_alter_contact) {
        this.cd_alter_contact = cd_alter_contact;
    }

    public String getCd_state() {
        return cd_state;
    }

    public void setCd_state(String cd_state) {
        this.cd_state = cd_state;
    }

    public String getCd_city() {
        return cd_city;
    }

    public void setCd_city(String cd_city) {
        this.cd_city = cd_city;
    }

    public String getCd_current_location() {
        return cd_current_location;
    }

    public void setCd_current_location(String cd_current_location) {
        this.cd_current_location = cd_current_location;
    }

    public String getCd_date_of_birth() {
        return cd_date_of_birth;
    }

    public void setCd_date_of_birth(String cd_date_of_birth) {
        this.cd_date_of_birth = cd_date_of_birth;
    }

    public String getCd_communication() {
        return cd_communication;
    }

    public void setCd_communication(String cd_communication) {
        this.cd_communication = cd_communication;
    }

    public String getCd_job_profile_one() {
        return cd_job_profile_one;
    }

    public void setCd_job_profile_one(String cd_job_profile_one) {
        this.cd_job_profile_one = cd_job_profile_one;
    }

    public String getCd_job_designation_one() {
        return cd_job_designation_one;
    }

    public void setCd_job_designation_one(String cd_job_designation_one) {
        this.cd_job_designation_one = cd_job_designation_one;
    }

    public String getCd_job_profile_two() {
        return cd_job_profile_two;
    }

    public void setCd_job_profile_two(String cd_job_profile_two) {
        this.cd_job_profile_two = cd_job_profile_two;
    }

    public String getCd_job_designation_two() {
        return cd_job_designation_two;
    }

    public void setCd_job_designation_two(String cd_job_designation_two) {
        this.cd_job_designation_two = cd_job_designation_two;
    }

    public String getCd_qualification_std() {
        return cd_qualification_std;
    }

    public void setCd_qualification_std(String cd_qualification_std) {
        this.cd_qualification_std = cd_qualification_std;
    }

    public String getCd_qualification_stream() {
        return cd_qualification_stream;
    }

    public void setCd_qualification_stream(String cd_qualification_stream) {
        this.cd_qualification_stream = cd_qualification_stream;
    }

    public String getCd_college_name() {
        return cd_college_name;
    }

    public void setCd_college_name(String cd_college_name) {
        this.cd_college_name = cd_college_name;
    }

    public String getCd_qualification_start_date() {
        return cd_qualification_start_date;
    }

    public void setCd_qualification_start_date(String cd_qualification_start_date) {
        this.cd_qualification_start_date = cd_qualification_start_date;
    }

    public String getCd_qualification_end_date() {
        return cd_qualification_end_date;
    }

    public void setCd_qualification_end_date(String cd_qualification_end_date) {
        this.cd_qualification_end_date = cd_qualification_end_date;
    }

    public String getCd_fresher_intern_exp() {
        return cd_fresher_intern_exp;
    }

    public void setCd_fresher_intern_exp(String cd_fresher_intern_exp) {
        this.cd_fresher_intern_exp = cd_fresher_intern_exp;
    }

    public String getCd_exp_job_industry() {
        return cd_exp_job_industry;
    }

    public void setCd_exp_job_industry(String cd_exp_job_industry) {
        this.cd_exp_job_industry = cd_exp_job_industry;
    }

    public String getCd_exp_company_name() {
        return cd_exp_company_name;
    }

    public void setCd_exp_company_name(String cd_exp_company_name) {
        this.cd_exp_company_name = cd_exp_company_name;
    }

    public String getCd_exp_current_salary() {
        return cd_exp_current_salary;
    }

    public void setCd_exp_current_salary(String cd_exp_current_salary) {
        this.cd_exp_current_salary = cd_exp_current_salary;
    }

    public String getCd_exp_start_date() {
        return cd_exp_start_date;
    }

    public void setCd_exp_start_date(String cd_exp_start_date) {
        this.cd_exp_start_date = cd_exp_start_date;
    }

    public String getCd_exp_end_date() {
        return cd_exp_end_date;
    }

    public void setCd_exp_end_date(String cd_exp_end_date) {
        this.cd_exp_end_date = cd_exp_end_date;
    }

    public String getCd_employment_type() {
        return cd_employment_type;
    }

    public void setCd_employment_type(String cd_employment_type) {
        this.cd_employment_type = cd_employment_type;
    }

    public String getCd_location_prefer() {
        return cd_location_prefer;
    }

    public void setCd_location_prefer(String cd_location_prefer) {
        this.cd_location_prefer = cd_location_prefer;
    }

    public String getCd_languages() {
        return cd_languages;
    }

    public void setCd_languages(String cd_languages) {
        this.cd_languages = cd_languages;
    }

    public String getCd_vehicle() {
        return cd_vehicle;
    }

    public void setCd_vehicle(String cd_vehicle) {
        this.cd_vehicle = cd_vehicle;
    }

    public String getCd_licence() {
        return cd_licence;
    }

    public void setCd_licence(String cd_licence) {
        this.cd_licence = cd_licence;
    }

    public String getCd_documents() {
        return cd_documents;
    }

    public void setCd_documents(String cd_documents) {
        this.cd_documents = cd_documents;
    }

    public String getCd_skill() {
        return cd_skill;
    }

    public void setCd_skill(String cd_skill) {
        this.cd_skill = cd_skill;
    }

    public String getCd_reference() {
        return cd_reference;
    }

    public void setCd_reference(String cd_reference) {
        this.cd_reference = cd_reference;
    }

    public String getCd_resume_cv_url() {
        return cd_resume_cv_url;
    }

    public void setCd_resume_cv_url(String cd_resume_cv_url) {
        this.cd_resume_cv_url = cd_resume_cv_url;
    }

}
