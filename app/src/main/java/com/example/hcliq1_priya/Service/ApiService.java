package com.example.hcliq1_priya.Service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    //http://testapi.wcss.co.in/api/userHomeScreen/homeScreenPopularHospitals/{authKey}/{userId}/{cityParam}

    @GET("api/userHomeScreen/homeScreenPopularHospitals/{authKey}/{userId}/{cityParam}")
    Call<ResponseBody> homeScreenPopularHospitals(@Path("authKey") String authKey,
                                                  @Path("userId") String userId,
                                                  @Path("cityParam") String cityParam);

    //http://testapi.wcss.co.in/api/userHomeScreen/homeScreenPopularSpeciality/{authKey}/{userId}/{cityParam}

    @GET("api/userHomeScreen/homeScreenPopularSpeciality/{authKey}/{userId}/{cityParam}")
    Call<ResponseBody> homeScreenPopularSpeciality(@Path("authKey") String authKey,
                                                   @Path("userId") String userId,
                                                   @Path("cityParam") String cityParam);

    //http://testapi.wcss.co.in/api/userHomeScreen/homeScreenPreferredDoc/{authKey}/{userId}

    @GET("api/userHomeScreen/homeScreenPreferredDoc/{authKey}/{userId}")
    Call<ResponseBody> homeScreenPreferredDoc(@Path("authKey") String authKey,
                                              @Path("userId") String userId);
    //http://testapi.wcss.co.in/api/userHomeScreen/todayAptmtList/{authKey}/{userId}

    @GET("api/userHomeScreen/todayAptmtList/{authKey}/{userId}")
    Call<ResponseBody> todayAptmtList(@Path("authKey") String authKey,
                                      @Path("userId") String userId);

    //http://testapi.wcss.co.in/api/userHomeScreen/getRecommendedDoctorInCity/{authKey}/{cityParam}
    @GET("api/userHomeScreen/getRecommendedDoctorInCity/{authKey}/{cityParam}")
    Call<ResponseBody> getRecommendedDoctorInCity(@Path("authKey") String authKey,
                                                  @Path("cityParam") String cityParam);

    //Cities Name
    //http://testapi.wcss.co.in/api/cityListOperation/getCityLists/A/NULL/NULL/NULL
    @GET("api/cityListOperation/getCityLists/A/NULL/NULL/NULL")
    Call<ResponseBody> getCityLists();

    //http://testapi.wcss.co.in/api/keyWords/getSearchKeyWords/A/"+userId+"/NULL
    //http://testapi.wcss.co.in/api/keyWords/getSearchKeyWords/A/1/NULL
    @GET("api/keyWords/getSearchKeyWords/A/1/NULL")
    Call<ResponseBody> getSearchKeyWords();

    //http://testapi.wcss.co.in/api/filterInfo/GetFilterInfo/NULL/NULL/NULL/AF
    @GET("api/filterInfo/GetFilterInfo/NULL/NULL/NULL/AF")
    Call<ResponseBody> GetFilterInfo();

    @FormUrlEncoded
    @POST("api/insert/searchHistory")
    Call<ResponseBody> postSearchHistory(@Field("opr") String opr,
                                         @Field("cityId") String cityId,
                                         @Field("searchKeywords") String searchKeywords,
                                         @Field("userId") String userId);

    @FormUrlEncoded
    @POST("api/cityListOperation/updatePreferredCity")
    Call<ResponseBody> postCity(@Field("opr") String opr,
                                @Field("userId") String userId,
                                @Field("cityNameSearch") String cityNameSearch,
                                @Field("cityCode") String cityCode);

}
