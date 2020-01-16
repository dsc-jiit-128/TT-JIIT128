package com.example.rohan.f7;

public class SubjectDetails {
    public String subjectType;
    public String subjectName;
    public String subjectTiming;
    public String subjectFaculty;
    public String subjectVenue;
    public String subjectCode;
    public String batchName;


    public SubjectDetails(String subjectType, String subjectName, String subjectTiming, String subjectFaculty, String subjectVenue, String subjectCode, String batchName) {
        this.subjectType = subjectType;
        this.subjectName = subjectName;
        this.subjectTiming = subjectTiming;
        this.subjectFaculty = subjectFaculty;
        this.subjectVenue = subjectVenue;
        this.subjectCode = subjectCode;
        this.batchName = batchName;

    }
    public SubjectDetails(){

    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getsubjectCode() {
        return subjectCode;
    }

    public void setsubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectTiming() {
        return subjectTiming;
    }

    public void setSubjectTiming(String subjectTiming) {
        this.subjectTiming = subjectTiming;
    }

    public String getSubjectFaculty() {
        return subjectFaculty;
    }

    public void setSubjectFaculty(String subjectFaculty) {
        this.subjectFaculty = subjectFaculty;
    }

    public String getSubjectVenue() {
        return subjectVenue;
    }

    public void setSubjectVenue(String subjectVenue) {
        this.subjectVenue = subjectVenue;
    }
}
