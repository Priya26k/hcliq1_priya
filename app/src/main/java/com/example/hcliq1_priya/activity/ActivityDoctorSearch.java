package com.example.hcliq1_priya.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hcliq1_priya.R;
import com.example.hcliq1_priya.Service.ApiService;
import com.example.hcliq1_priya.adapter.AdapterDoctorPopularSearch;
import com.example.hcliq1_priya.adapter.AdapterDoctorRecentSearch;
import com.example.hcliq1_priya.model.ModelDoctorPopularSearch;
import com.example.hcliq1_priya.model.ModelDoctorRecentSearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityDoctorSearch extends AppCompatActivity {
    ArrayList<ModelDoctorRecentSearch> userRecentSearchKeywordList = new ArrayList<>();
    ArrayList<ModelDoctorPopularSearch> userPopularSearchKeywordList = new ArrayList<>();
    RecyclerView recyclerViewPopularSearch,recyclerViewRecentSearch;
    EditText editDoctorSearch;
    ImageView imageViewBack,imageviewCross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);
        recyclerViewPopularSearch = (RecyclerView)findViewById(R.id.recycler_view_popular_search);
        recyclerViewRecentSearch = (RecyclerView)findViewById(R.id.recycler_view_recent_search);
        editDoctorSearch = (EditText) findViewById(R.id.edit_doctor_search);
        imageviewCross = (ImageView)findViewById(R.id.cross);
        imageViewBack = (ImageView)findViewById(R.id.back);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDoctorSearch.this,ActivityHomeScreen.class);
                startActivity(intent);
            }
        });
        editDoctorSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId & EditorInfo.IME_MASK_ACTION) == EditorInfo.IME_ACTION_DONE || (actionId & EditorInfo.IME_MASK_ACTION) ==  EditorInfo.IME_ACTION_NEXT){
                    postSearchHistory(editDoctorSearch.getText().toString());
                    startActivity(new Intent(ActivityDoctorSearch.this, ActivityHomeScreen.class));
                    return true;
                }
                return false;
            }
        });
        imageviewCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDoctorSearch.setText(null);
            }
        });

        editDoctorSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()){
                    imageviewCross.setVisibility(View.GONE);
                    editDoctorSearch.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_search_black_24dp,0);

                } else {
                    imageviewCross.setVisibility(View.VISIBLE);
                    editDoctorSearch.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
            }
        });

        getSearchSuggestion();
        getPopularSearch();
    }

    //todo Get Search suggestion from server
    public void getSearchSuggestion() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://testapi.wcss.co.in/").build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.getSearchKeyWords();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String userSearchKeyword = jsonObject.getString("userSearchKeyword");
                        String lastSearchDatetime = jsonObject.getString("lastSearchDatetime");
                        userRecentSearchKeywordList.add(new ModelDoctorRecentSearch(userSearchKeyword,lastSearchDatetime));
                    }
                    final AdapterDoctorRecentSearch adapterDoctorRecentSearch = new AdapterDoctorRecentSearch(ActivityDoctorSearch.this,userRecentSearchKeywordList);
                    recyclerViewRecentSearch.setLayoutManager(new LinearLayoutManager(ActivityDoctorSearch.this));
                    recyclerViewRecentSearch.setAdapter(adapterDoctorRecentSearch);
                    adapterDoctorRecentSearch.setOnClickListener(new AdapterDoctorRecentSearch.OnItemCLickListener() {
                        @Override
                        public void setOnClick(int pos) {
                            editDoctorSearch.setText(userRecentSearchKeywordList.get(pos).getUserSearchKeyword());
                        }
                    });

                    editDoctorSearch.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            filter1(adapterDoctorRecentSearch,userRecentSearchKeywordList,s.toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getPopularSearch(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://testapi.wcss.co.in/").build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.GetFilterInfo();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String FilterID = jsonObject.getString("FilterID");
                        String FilterDescription = jsonObject.getString("FilterDescription");
                        userPopularSearchKeywordList.add(new ModelDoctorPopularSearch(FilterID,FilterDescription));
                    }
                    final AdapterDoctorPopularSearch adapterDoctorPopularSearch = new AdapterDoctorPopularSearch(ActivityDoctorSearch.this,userPopularSearchKeywordList);
                    recyclerViewPopularSearch.setLayoutManager(new LinearLayoutManager(ActivityDoctorSearch.this));
                    recyclerViewPopularSearch.setAdapter(adapterDoctorPopularSearch);

                    adapterDoctorPopularSearch.setOnClickListener(new AdapterDoctorPopularSearch.OnItemCLickListener() {
                        @Override
                        public void setOnClick(int pos) {
                            editDoctorSearch.setText(userPopularSearchKeywordList.get(pos).getFilterDescription());
                        }
                    });

                    editDoctorSearch.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            filter(adapterDoctorPopularSearch,userPopularSearchKeywordList,s.toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void filter(AdapterDoctorPopularSearch adapterDoctorPopularSearch, ArrayList<ModelDoctorPopularSearch> modelDoctorPopularSearches, String text){
        ArrayList<ModelDoctorPopularSearch> filteredList=new ArrayList<>();
        for (ModelDoctorPopularSearch item : modelDoctorPopularSearches){
            if(item.getFilterDescription().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapterDoctorPopularSearch.filterList(filteredList);
    }

    public void filter1(AdapterDoctorRecentSearch adapterDoctorRecentSearch, ArrayList<ModelDoctorRecentSearch> modelDoctorRecentSearches, String text){
        ArrayList<ModelDoctorRecentSearch> filteredList=new ArrayList<>();
        for (ModelDoctorRecentSearch item : modelDoctorRecentSearches){
            if(item.getUserSearchKeyword().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapterDoctorRecentSearch.filterList(filteredList);
    }

    public void postSearchHistory(String searchKeyword){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://testapi.wcss.co.in/").build();
        ApiService apiService=retrofit.create(ApiService.class);
        Call<ResponseBody> call=apiService.postSearchHistory("A", "", searchKeyword, "1");
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
