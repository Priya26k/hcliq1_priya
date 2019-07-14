package com.example.hcliq1_priya.model;

public class ModelHomeScreen {
    private String hospitalCode,hospitalName,docCount,hospitalImagePath,hospitalLocalityName,hospitalContactNo;

    public ModelHomeScreen(String hospitalCode,String hospitalName,String docCount,String hospitalImagePath,String hospitalLocalityName,String hospitalContactNo) {
        this.hospitalCode = hospitalCode;
        this.hospitalName = hospitalName;
        this.docCount = docCount;
        this.hospitalImagePath = hospitalImagePath;
        this.hospitalLocalityName = hospitalLocalityName;
        this.hospitalContactNo = hospitalContactNo;

     }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getDocCount() {
        return docCount;
    }

    public String getHospitalImagePath() {
        return hospitalImagePath;
    }

    public String getHospitalLocalityName() {
        return hospitalLocalityName;
    }

    public String getHospitalContactNo() {
        return hospitalContactNo;
    }

}

