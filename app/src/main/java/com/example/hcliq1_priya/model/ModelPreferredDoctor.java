package com.example.hcliq1_priya.model;

public class ModelPreferredDoctor {
    private String doctorId,doctorName,latestAvailable,doctorImagePath,doctorQualification;

    public ModelPreferredDoctor(String doctorId,String doctorName,String latestAvailable,String doctorImagePath,String doctorQualification) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.latestAvailable = latestAvailable;
        this.doctorImagePath = doctorImagePath;
        this.doctorQualification = doctorQualification;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getLatestAvailable() {
        return latestAvailable;
    }

    public String getDoctorImagePath() {
        return doctorImagePath;
    }

    public String getDoctorQualification() {
        return doctorQualification;
    }

}

