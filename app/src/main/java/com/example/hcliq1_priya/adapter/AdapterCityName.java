package com.example.hcliq1_priya.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hcliq1_priya.R;
import com.example.hcliq1_priya.model.ModelCityName;

import java.util.ArrayList;

public class AdapterCityName extends RecyclerView.Adapter<AdapterCityName.ViewHolder> {
    private Context context;
    private ArrayList<ModelCityName> arrayList;

    public AdapterCityName(Context context, ArrayList<ModelCityName> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    private OnItemCLickListener listener;

    public interface OnItemCLickListener{
        void setOnClick(int pos);
    }

    public void setOnClickListener(OnItemCLickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city_spinner,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        ModelCityName modelCityName=arrayList.get(i);
        viewHolder.textCityName.setText(modelCityName.getCityName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textCityName;
        public ViewHolder(@NonNull View view){
            super(view);
            textCityName = (TextView)view.findViewById(R.id.text_city_name);
        }

    }

    public void filterList(ArrayList<ModelCityName> filteredList){
        arrayList = filteredList;
        notifyDataSetChanged();
    }
}

