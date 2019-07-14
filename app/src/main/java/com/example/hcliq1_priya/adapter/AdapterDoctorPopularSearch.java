package com.example.hcliq1_priya.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hcliq1_priya.R;
import com.example.hcliq1_priya.activity.ActivityHomeScreen;
import com.example.hcliq1_priya.model.ModelDoctorPopularSearch;

import java.util.ArrayList;

public class AdapterDoctorPopularSearch extends RecyclerView.Adapter<AdapterDoctorPopularSearch.ViewHolder> {
    Context context;
    ArrayList<ModelDoctorPopularSearch> arrayList;
    private OnItemCLickListener listener;
    public interface OnItemCLickListener{
        void setOnClick(int pos);
    }
    public void setOnClickListener(OnItemCLickListener listener){
        this.listener = listener;
    }

    public AdapterDoctorPopularSearch(Context context, ArrayList<ModelDoctorPopularSearch> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_doctor_search,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final ModelDoctorPopularSearch modelDoctorPopularSearch =arrayList.get(i);
        viewHolder.textCityName.setText(modelDoctorPopularSearch.getFilterDescription());

        viewHolder.textCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ActivityHomeScreen.class)
                        .putExtra("searchKeyWord", modelDoctorPopularSearch.getFilterDescription()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        viewHolder.imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setOnClick(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCityName;
        ImageView imageViewSearch;
        public ViewHolder(@NonNull View view) {
            super(view);
            textCityName = (TextView)view.findViewById(R.id.text_city_name);
            imageViewSearch = (ImageView)view.findViewById(R.id.image);
        }
    }

    public void filterList(ArrayList<ModelDoctorPopularSearch> filteredList){
        arrayList = filteredList;
        notifyDataSetChanged();
    }
}
