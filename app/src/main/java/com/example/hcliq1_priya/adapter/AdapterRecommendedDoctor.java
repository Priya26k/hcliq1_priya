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
import com.example.hcliq1_priya.model.ModelRecommendedDoctors;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecommendedDoctor  extends RecyclerView.Adapter<AdapterRecommendedDoctor.ViewHolder> {
    private Context context;
    private ArrayList<ModelRecommendedDoctors> arrayList;

    public AdapterRecommendedDoctor(Context context, ArrayList<ModelRecommendedDoctors> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recommended_doctor,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        ModelRecommendedDoctors modelRecommendedDoctors=arrayList.get(i);
        viewHolder.textDoctorName.setText(modelRecommendedDoctors.getDoctorName());
        Picasso.with(context).load(modelRecommendedDoctors.getDoctorImagePath()).fit().into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textDoctorName;
        CircleImageView imageView;
        public ViewHolder(@NonNull View view){
            super(view);
            textDoctorName = (TextView)view.findViewById(R.id.text_doctor_name);
            imageView = (CircleImageView)view.findViewById(R.id.circleimageview);
        }

    }
}

