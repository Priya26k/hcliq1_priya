package com.example.hcliq1_priya.model;

public class ModelRecommendedDoctors {
    private  String doctorId,doctorImagePath,doctorName,doctorSpecialityId,doctorSpecialityDesc,recommendedByUserCount;

    public ModelRecommendedDoctors(String doctorId,String doctorImagePath,String doctorName,String doctorSpecialityId,String doctorSpecialityDesc,String recommendedByUserCount){
        this.doctorId = doctorId;
        this.doctorImagePath = doctorImagePath;
        this.doctorName = doctorName;
        this.doctorSpecialityId = doctorSpecialityId;
        this.doctorSpecialityDesc = doctorSpecialityDesc;
        this.recommendedByUserCount = recommendedByUserCount;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDoctorImagePath() {
        return doctorImagePath;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorSpecialityId() {
        return doctorSpecialityId;
    }

    public String getDoctorSpecialityDesc() {
        return doctorSpecialityDesc;
    }

    public String getRecommendedByUserCount() {
        return recommendedByUserCount;
    }
}
