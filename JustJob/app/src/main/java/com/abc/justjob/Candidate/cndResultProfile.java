package com.abc.justjob.Candidate;

public class cndResultProfile {

    private String Register_login_id;
    private String Cd_full_name;
    private String Cd_contact_no;

    public cndResultProfile(String register_login_id, String cd_full_name, String cd_contact_no) {
        Register_login_id = register_login_id;
        Cd_full_name = cd_full_name;
        Cd_contact_no = cd_contact_no;
    }

    public String getRegister_login_id() {
        return Register_login_id;
    }

    public void setRegister_login_id(String register_login_id) {
        Register_login_id = register_login_id;
    }

    public String getCd_full_name() {
        return Cd_full_name;
    }

    public void setCd_full_name(String cd_full_name) {
        Cd_full_name = cd_full_name;
    }

    public String getCd_contact_no() {
        return Cd_contact_no;
    }

    public void setCd_contact_no(String cd_contact_no) {
        Cd_contact_no = cd_contact_no;
    }

}
