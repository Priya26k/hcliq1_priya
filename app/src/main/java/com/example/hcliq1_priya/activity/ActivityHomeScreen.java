package com.example.hcliq1_priya.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hcliq1_priya.R;
import com.example.hcliq1_priya.Service.ApiService;
import com.example.hcliq1_priya.adapter.AdapterCityName;
import com.example.hcliq1_priya.adapter.AdapterHomeScreen;
import com.example.hcliq1_priya.adapter.AdapterPreferredDoctor;
import com.example.hcliq1_priya.adapter.AdapterRecommendedDoctor;
import com.example.hcliq1_priya.adapter.AdapterSpeciality;
import com.example.hcliq1_priya.adapter.AdapterTodaysAppointment;
import com.example.hcliq1_priya.adapter.ColoredCursorAdapter;
import com.example.hcliq1_priya.model.ModelCityName;
import com.example.hcliq1_priya.model.ModelHomeScreen;
import com.example.hcliq1_priya.model.ModelPreferredDoctor;
import com.example.hcliq1_priya.model.ModelRecommendedDoctors;
import com.example.hcliq1_priya.model.ModelSpeciality;
import com.example.hcliq1_priya.model.ModelTodaysAppointment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityHomeScreen extends AppCompatActivity {

    RecyclerView recyclerViewDoctor,recyclerViewSpecialist,recyclerViewPreferredDoctor,recyclerViewTodaysAppointment,recyclerViewRecommendedDoctor,recyclerViewOtherCity,recyclerViewPopularCity;
    CircleImageView circleImageView,actionImage;
    CardView cardview;
    EditText editCityName;
    TextView textPopularCities,textOtherCities;
    TextView textSearchCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewDoctor = (RecyclerView)findViewById(R.id.recycler_view_doctor);
        recyclerViewSpecialist = (RecyclerView)findViewById(R.id.recycler_view_speciality);
        recyclerViewPreferredDoctor = (RecyclerView)findViewById(R.id.recycler_view_preferred_doctor);
        recyclerViewTodaysAppointment = (RecyclerView)findViewById(R.id.recycler_view_todays_appointment);
        recyclerViewRecommendedDoctor = (RecyclerView)findViewById(R.id.recycler_view_recommended_doctors);
        circleImageView = (CircleImageView)findViewById(R.id.circleimageview);
        actionImage = (CircleImageView)findViewById(R.id.action_image);
        textSearchCity = (TextView)findViewById(R.id.text_search_city);
        cardview = (CardView)findViewById(R.id.cardView);

        actionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardview.setVisibility(View.GONE);
            }
        });

        textSearchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCityName();
            }
        });

        homeScreenPopularHospitals();
        homeScreenPopularSpeciality();
        homeScreenPreferredDoc();
        todayAptmtList();
        getRecommendedDoctorInCity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_search:
                startActivity(new Intent(this, ActivityDoctorSearch.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void homeScreenPopularHospitals() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://testapi.wcss.co.in/").build();
        final ApiService apiService = retrofit.create(ApiService.class);

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Data fetching from server");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Call<ResponseBody> call = apiService.homeScreenPopularHospitals("kH4J3RXsw5cBMKvpLEwTDUCVi", "1", "09502900001");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ArrayList<ModelHomeScreen> homeScreenArrayList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String hospitalCode = jsonObject.getString("hospitalCode");
                        String hospitalName = jsonObject.getString("hospitalName");
                        String docCount = jsonObject.getString("docCount");
                        String hospitalImagePath = jsonObject.getString("hospitalImagePath");
                        String hospitalLocalityName = jsonObject.getString("hospitalLocalityName");
                        String hospitalContactNo = jsonObject.getString("hospitalContactNo");
                        homeScreenArrayList.add(new ModelHomeScreen(hospitalCode,hospitalName,docCount,hospitalImagePath,hospitalLocalityName,hospitalContactNo));
                    }
                    progressDialog.dismiss();
                    recyclerViewDoctor.setLayoutManager(new LinearLayoutManager(ActivityHomeScreen.this,LinearLayoutManager.HORIZONTAL, false));
                    recyclerViewDoctor.setAdapter(new AdapterHomeScreen(ActivityHomeScreen.this, homeScreenArrayList));

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void homeScreenPopularSpeciality(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://testapi.wcss.co.in/").build();
        final ApiService apiService =retrofit.create(ApiService.class);
        Call<ResponseBody> call= apiService.homeScreenPopularSpeciality("kH4J3RXsw5cBMKvpLEwTDUCVi","1","09502900001");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ArrayList<ModelSpeciality> modelSpecialityArrayList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String specialityId = jsonObject.getString("specialityId");
                        String specialistName = jsonObject.getString("specialistName");
                        String specialistCount = jsonObject.getString("specialistCount");
                        String specialityImgPath = jsonObject.getString("specialityImgPath");
                        modelSpecialityArrayList.add(new ModelSpeciality(specialityId,specialistName,specialistCount,specialityImgPath));
                    }
                    recyclerViewSpecialist.setLayoutManager(new LinearLayoutManager(ActivityHomeScreen.this,LinearLayoutManager.HORIZONTAL, false));
                    recyclerViewSpecialist.setAdapter(new AdapterSpeciality(ActivityHomeScreen.this, modelSpecialityArrayList));


                }catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void homeScreenPreferredDoc(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://testapi.wcss.co.in/").build();
        final ApiService apiService =retrofit.create(ApiService.class);
        Call<ResponseBody> call= apiService.homeScreenPreferredDoc("kH4J3RXsw5cBMKvpLEwTDUCVi","1");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ArrayList<ModelPreferredDoctor> preferredDoctorArrayList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String doctorId = jsonObject.getString("doctorId");
                        String doctorName = jsonObject.getString("doctorName");
                        String latestAvailable = jsonObject.getString("latestAvailable");
                        String doctorImagePath = jsonObject.getString("doctorImagePath");
                        String doctorQualification = jsonObject.getString("doctorQualification");
                        preferredDoctorArrayList.add(new ModelPreferredDoctor(doctorId,doctorName,latestAvailable,doctorImagePath,doctorQualification));
                    }
                    recyclerViewPreferredDoctor.setLayoutManager(new LinearLayoutManager(ActivityHomeScreen.this));
                    recyclerViewPreferredDoctor.setAdapter(new AdapterPreferredDoctor(ActivityHomeScreen.this, preferredDoctorArrayList));

                }catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void todayAptmtList(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://testapi.wcss.co.in/").build();
        final ApiService apiService =retrofit.create(ApiService.class);
        Call<ResponseBody> call= apiService.todayAptmtList("kH4J3RXsw5cBMKvpLEwTDUCVi","1");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ArrayList<ModelTodaysAppointment> todaysAppointmentArrayList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String aptmtId = jsonObject.getString("aptmtId");
                        String patientFirstName = jsonObject.getString("patientFirstName");
                        String doctorName = jsonObject.getString("doctorName");
                        String hospitalName = jsonObject.getString("hospitalName");
                        String specialistName = jsonObject.getString("specialistName");
                        String waitingDesc = jsonObject.getString("waitingDesc");
                        String expectedTime = jsonObject.getString("expectedTime");
                        String doctorUnavailabilityStartTime = jsonObject.getString("doctorUnavailabilityStartTime");
                        String doctorUnavailabilityEndTime = jsonObject.getString("doctorUnavailabilityEndTime");
                        String currentShiftFlag = jsonObject.getString("currentShiftFlag");
                        String hospitalContactNo = jsonObject.getString("hospitalContactNo");
                        String hospitalLattitude = jsonObject.getString("hospitalLattitude");
                        String hospitalLongitude = jsonObject.getString("hospitalLongitude");
                       todaysAppointmentArrayList.add(new ModelTodaysAppointment(aptmtId,patientFirstName,doctorName,hospitalName,specialistName,waitingDesc,expectedTime,doctorUnavailabilityStartTime,doctorUnavailabilityEndTime,currentShiftFlag,hospitalContactNo,hospitalLattitude,hospitalLongitude));
                    }
                    recyclerViewTodaysAppointment.setLayoutManager(new LinearLayoutManager(ActivityHomeScreen.this));
                    recyclerViewTodaysAppointment.setAdapter(new AdapterTodaysAppointment(ActivityHomeScreen.this, todaysAppointmentArrayList));


                }catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public  void getRecommendedDoctorInCity(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://testapi.wcss.co.in/").build();
        final ApiService apiService =retrofit.create(ApiService.class);
        Call<ResponseBody> call= apiService.getRecommendedDoctorInCity("kH4J3RXsw5cBMKvpLEwTDUCVi","09502900001");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ArrayList<ModelRecommendedDoctors> modelRecommendedDoctorsArrayList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String doctorId = jsonObject.getString("doctorId");
                        String doctorImagePath = jsonObject.getString("doctorImagePath");
                        String doctorName = jsonObject.getString("doctorName");
                        String doctorSpecialityId = jsonObject.getString("doctorSpecialityId");
                        String doctorSpecialityDesc = jsonObject.getString("doctorSpecialityDesc");
                        String recommendedByUserCount = jsonObject.getString("recommendedByUserCount");
                        modelRecommendedDoctorsArrayList.add(new ModelRecommendedDoctors(doctorId,doctorImagePath,doctorName,doctorSpecialityId,doctorSpecialityDesc,recommendedByUserCount));
                    }
                    recyclerViewRecommendedDoctor.setLayoutManager(new LinearLayoutManager(ActivityHomeScreen.this,LinearLayoutManager.HORIZONTAL, false));
                    recyclerViewRecommendedDoctor.setAdapter(new AdapterRecommendedDoctor(ActivityHomeScreen.this, modelRecommendedDoctorsArrayList));

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void filter(AdapterCityName adapterCityName, ArrayList<ModelCityName> cityNameArrayList, String text){
        ArrayList<ModelCityName> filteredList=new ArrayList<>();
        for (ModelCityName item : cityNameArrayList){
            if(item.getCityName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapterCityName.filterList(filteredList);
    }

    public void getCityLists(final AlertDialog alertDialog){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://testapi.wcss.co.in/").build();
        ApiService apiService=retrofit.create(ApiService.class);
        Call<ResponseBody> call=apiService.getCityLists();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    final ArrayList<ModelCityName> othercityarraylist = new ArrayList<>();
                    final ArrayList<ModelCityName> popularcityarraylist = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String cityCode = jsonObject.getString("cityCode");
                        String cityName = jsonObject.getString("cityName");
                        String sortBy = jsonObject.getString("sortBy");
                        String cityPopularFlag = jsonObject.getString("cityPopularFlag");
                        if(cityPopularFlag.equals("F")){
                            othercityarraylist.add(new ModelCityName(cityCode,cityName,sortBy,cityPopularFlag));
                        }else {
                            popularcityarraylist.add(new ModelCityName(cityCode,cityName,sortBy,cityPopularFlag));
                        }
                    }
                    final AdapterCityName adapterOtherCityName = new AdapterCityName(getApplicationContext(),othercityarraylist);
                    recyclerViewOtherCity.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerViewOtherCity.setAdapter(adapterOtherCityName);

                    final AdapterCityName adapterPopularCityName = new AdapterCityName(getApplicationContext(),popularcityarraylist);
                    recyclerViewPopularCity.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerViewPopularCity.setAdapter(adapterPopularCityName);
                    adapterOtherCityName.setOnClickListener(new AdapterCityName.OnItemCLickListener() {
                        @Override
                        public void setOnClick(int pos) {
                            postCityName(othercityarraylist.get(pos).getCityName(), othercityarraylist.get(pos).getCityCode());
                            alertDialog.dismiss();
                        }
                    });
                    adapterPopularCityName.setOnClickListener(new AdapterCityName.OnItemCLickListener() {
                        @Override
                        public void setOnClick(int pos) {
                            postCityName(popularcityarraylist.get(pos).getCityName(), popularcityarraylist.get(pos).getCityCode());
                            alertDialog.dismiss();
                        }
                    });
                    editCityName.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            filter(adapterOtherCityName,othercityarraylist,s.toString());
                            filter(adapterPopularCityName,popularcityarraylist,s.toString());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ActivityHomeScreen.this, "on Failure :"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void searchCityName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityHomeScreen.this);
        builder.setTitle("Select City");
        View view = LayoutInflater.from(ActivityHomeScreen.this).inflate(R.layout.layout_city_search, null);
        builder.setView(view);
        recyclerViewOtherCity = (RecyclerView)view.findViewById(R.id.recycler_view_other_city);
        recyclerViewPopularCity = (RecyclerView)view.findViewById(R.id.recycler_view_popular_city);
        editCityName = (EditText)view.findViewById(R.id.edit_city_name);
        textPopularCities = (TextView)view.findViewById(R.id.text_popular_cities);
        textOtherCities = (TextView)view.findViewById(R.id.text_other_cities);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        getCityLists(alertDialog);

    }

    public void postCityName(String cityNameSearch, String cityCode){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://testapi.wcss.co.in/").build();
        ApiService apiService=retrofit.create(ApiService.class);
        Call<ResponseBody> call=apiService.postCity("B", "1", cityNameSearch,cityCode);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
