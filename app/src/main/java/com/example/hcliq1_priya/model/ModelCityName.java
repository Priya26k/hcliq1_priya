package com.example.hcliq1_priya.model;

public class ModelCityName {
    private String cityCode,cityName,sortBy,cityPopularFlag;

    public ModelCityName(String cityCode,String cityName,String sortBy,String cityPopularFlag){
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.sortBy = sortBy;
        this.cityPopularFlag = cityPopularFlag;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getCityPopularFlag() {
        return cityPopularFlag;
    }

}
