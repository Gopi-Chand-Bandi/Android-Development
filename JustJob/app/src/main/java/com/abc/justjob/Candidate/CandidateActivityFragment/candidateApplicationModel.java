package com.abc.justjob.Candidate.CandidateActivityFragment;

public class candidateApplicationModel {

    private String jobTitle;
    private String jobDesc;
    private String jobLocation;
    private String jobPayment;
    private String jobApplication;

    public candidateApplicationModel() {
    }

    public candidateApplicationModel(String jobTitle, String jobDesc, String jobLocation, String jobPayment, String jobApplication) {
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.jobLocation = jobLocation;
        this.jobPayment = jobPayment;
        this.jobApplication = jobApplication;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobPayment() {
        return jobPayment;
    }

    public void setJobPayment(String jobPayment) {
        this.jobPayment = jobPayment;
    }

    public String getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(String jobApplication) {
        this.jobApplication = jobApplication;
    }
}
