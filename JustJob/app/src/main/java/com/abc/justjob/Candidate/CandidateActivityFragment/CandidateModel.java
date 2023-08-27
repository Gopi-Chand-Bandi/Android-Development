package com.abc.justjob.Candidate.CandidateActivityFragment;

public class CandidateModel {
    private String companyImage;
    private String companyName;
    private String companyDesc;
    private String companyLocation;
    private String companySalary;
    private String companyExperience;
    private String companyEmployment;
    private String typeOfJob;
    private String companyEmail;
    private String companyPhone;

    public CandidateModel() {
    }

    public CandidateModel(String companyImage,String companyName, String companyDesc, String companyLocation,
                          String companySalary, String companyExperience, String companyEmployment,
                          String typeOfJob, String companyEmail, String companyPhone) {
        this.companyImage=companyImage;
        this.companyName = companyName;
        this.companyDesc = companyDesc;
        this.companyLocation = companyLocation;
        this.companySalary = companySalary;
        this.companyExperience = companyExperience;
        this.companyEmployment = companyEmployment;
        this.typeOfJob = typeOfJob;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getCompanySalary() {
        return companySalary;
    }

    public void setCompanySalary(String companySalary) {
        this.companySalary = companySalary;
    }

    public String getCompanyExperience() {
        return companyExperience;
    }

    public void setCompanyExperience(String companyExperience) {
        this.companyExperience = companyExperience;
    }

    public String getCompanyEmployment() {
        return companyEmployment;
    }

    public void setCompanyEmployment(String companyEmployment) {
        this.companyEmployment = companyEmployment;
    }

    public String getTypeOfJob() {
        return typeOfJob;
    }

    public void setTypeOfJob(String typeOfJob) {
        this.typeOfJob = typeOfJob;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

}
