package com.example.hcliq1_priya.model;

public class ModelDoctorPopularSearch {
    private String FilterID,FilterDescription;

    public ModelDoctorPopularSearch(String filterID, String filterDescription) {
        FilterID = filterID;
        FilterDescription = filterDescription;
    }

    public String getFilterID() {
        return FilterID;
    }

    public String getFilterDescription() {
        return FilterDescription;
    }
}
