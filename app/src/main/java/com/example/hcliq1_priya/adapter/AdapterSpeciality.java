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
import com.example.hcliq1_priya.model.ModelSpeciality;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSpeciality  extends RecyclerView.Adapter<AdapterSpeciality.ViewHolder> {
        private Context context;
        private ArrayList<ModelSpeciality> arrayList;

        public AdapterSpeciality(Context context, ArrayList<ModelSpeciality> arrayList){
            this.context=context;
            this.arrayList=arrayList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_speciality,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
            ModelSpeciality modelSpeciality=arrayList.get(i);
            viewHolder.textSpecialistName.setText(modelSpeciality.getSpecialistName());
            Picasso.with(context).load(modelSpeciality.getSpecialityImgPath()).fit().into(viewHolder.imageView);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView textSpecialistName;
            ImageView imageView;
            public ViewHolder(@NonNull View view){
                super(view);
                textSpecialistName = (TextView)view.findViewById(R.id.text_specialist_name);
                imageView = (ImageView) view.findViewById(R.id.image);
            }

        }

}
