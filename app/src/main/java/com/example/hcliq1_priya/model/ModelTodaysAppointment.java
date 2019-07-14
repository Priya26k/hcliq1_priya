package com.example.hcliq1_priya.model;

public class ModelTodaysAppointment {
    private String aptmtId,patientFirstName,doctorName,hospitalName,specialistName,waitingDesc,expectedTime,doctorUnavailabilityStartTime,doctorUnavailabilityEndTime,currentShiftFlag,hospitalContactNo,hospitalLattitude,hospitalLongitude;

    public ModelTodaysAppointment(String aptmtId,String patientFirstName,String doctorName,String hospitalName,String specialistName,String waitingDesc,String expectedTime,String doctorUnavailabilityStartTime,String doctorUnavailabilityEndTime,String currentShiftFlag,String hospitalContactNo,String hospitalLattitude,String hospitalLongitude) {
        this.aptmtId = aptmtId;
        this.patientFirstName = patientFirstName;
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
        this.specialistName = specialistName;
        this.waitingDesc = waitingDesc;
        this.expectedTime = expectedTime;
        this.doctorUnavailabilityStartTime = doctorUnavailabilityStartTime;
        this.doctorUnavailabilityEndTime = doctorUnavailabilityEndTime;
        this.currentShiftFlag = currentShiftFlag;
        this.hospitalContactNo = hospitalContactNo;
        this.hospitalLattitude = hospitalLattitude;
        this.hospitalLongitude = hospitalLongitude;
    }

    public String getAptmtId() {
        return aptmtId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public String getWaitingDesc() {
        return waitingDesc;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public String getDoctorUnavailabilityStartTime() {
        return doctorUnavailabilityStartTime;
    }

    public String getDoctorUnavailabilityEndTime() {
        return doctorUnavailabilityEndTime;
    }

    public String getCurrentShiftFlag() {
        return currentShiftFlag;
    }

    public String getHospitalContactNo() {
        return hospitalContactNo;
    }

    public String getHospitalLattitude() {
        return hospitalLattitude;
    }

    public String getHospitalLongitude() {
        return hospitalLongitude;
    }
}

