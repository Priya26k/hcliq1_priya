package com.example.hcliq1_priya.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hcliq1_priya.R;
import com.example.hcliq1_priya.model.ModelHomeScreen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterHomeScreen extends RecyclerView.Adapter<AdapterHomeScreen.ViewHolder> {
        private Context context;
        private ArrayList<ModelHomeScreen> arrayList;

        public AdapterHomeScreen(Context context, ArrayList<ModelHomeScreen> arrayList){
            this.context=context;
            this.arrayList=arrayList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_screen,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
            ModelHomeScreen modelHomeScreen=arrayList.get(i);
            viewHolder.textHospitalName.setText(modelHomeScreen.getHospitalName());
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView textHospitalName;
            ImageView imageView;
            public ViewHolder(@NonNull View view){
                super(view);
                textHospitalName = (TextView)view.findViewById(R.id.text_hospital_name);
                imageView = (ImageView) view.findViewById(R.id.image);
            }

        }
}

