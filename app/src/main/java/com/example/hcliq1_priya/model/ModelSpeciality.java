package com.example.hcliq1_priya.model;

public class ModelSpeciality {
    private String specialityId,specialistName,specialistCount,specialityImgPath;

    public ModelSpeciality(String specialityId,String specialistName,String specialistCount,String specialityImgPath) {
        this.specialityId = specialityId;
        this.specialistName = specialistName;
        this.specialistCount = specialistCount;
        this.specialityImgPath = specialityImgPath;

    }

    public String getSpecialityId() {
        return specialityId;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public String getSpecialistCount() {
        return specialistCount;
    }

    public String getSpecialityImgPath() {
        return specialityImgPath;
    }

}
