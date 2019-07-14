package com.example.hcliq1_priya.model;

public class ModelDoctorRecentSearch {
    private String userSearchKeyword,lastSearchDatetime;
    public ModelDoctorRecentSearch(String userSearchKeyword, String lastSearchDatetime){
        this.userSearchKeyword = userSearchKeyword;
        this.lastSearchDatetime = lastSearchDatetime;
    }

    public String getUserSearchKeyword() {
        return userSearchKeyword;
    }

    public String getLastSearchDatetime() {
        return lastSearchDatetime;
    }

}
