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
import com.example.hcliq1_priya.model.ModelPreferredDoctor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterPreferredDoctor  extends RecyclerView.Adapter<AdapterPreferredDoctor.ViewHolder> {
    private Context context;
    private ArrayList<ModelPreferredDoctor> arrayList;

    public AdapterPreferredDoctor(Context context, ArrayList<ModelPreferredDoctor> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_preferred_doctor,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        ModelPreferredDoctor modelPreferredDoctor=arrayList.get(i);
        viewHolder.textDoctorName.setText(modelPreferredDoctor.getDoctorName());
        viewHolder.textDoctorQualification.setText(modelPreferredDoctor.getDoctorQualification());
        Picasso.with(context).load(modelPreferredDoctor.getDoctorImagePath()).fit().into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textDoctorName,textDoctorQualification,textDoctorSpecififcation;
        CircleImageView imageView;
        public ViewHolder(@NonNull View view){
            super(view);
            textDoctorName = (TextView)view.findViewById(R.id.text_doctor_name);
            textDoctorQualification = (TextView)view.findViewById(R.id.text_doctor_qualification);
            textDoctorSpecififcation = (TextView)view.findViewById(R.id.text_doctor_specification);
            imageView = (CircleImageView) view.findViewById(R.id.image);
        }

    }

}
