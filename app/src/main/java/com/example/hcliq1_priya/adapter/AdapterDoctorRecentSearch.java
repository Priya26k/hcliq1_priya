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
import android.widget.Toast;

import com.example.hcliq1_priya.R;
import com.example.hcliq1_priya.Service.ApiService;
import com.example.hcliq1_priya.activity.ActivityHomeScreen;
import com.example.hcliq1_priya.model.ModelDoctorRecentSearch;

import java.io.IOException;
import java.util.ArrayList;

public class AdapterDoctorRecentSearch extends RecyclerView.Adapter<AdapterDoctorRecentSearch.ViewHolder> {
    private Context context;
    private ArrayList<ModelDoctorRecentSearch> arrayList;
    private OnItemCLickListener listener;
    public interface OnItemCLickListener{
        void setOnClick(int pos);
    }
    public void setOnClickListener(OnItemCLickListener listener){
        this.listener = listener;
    }

    public AdapterDoctorRecentSearch(Context context, ArrayList<ModelDoctorRecentSearch> arrayList){
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final ModelDoctorRecentSearch modelDoctorRecentSearch =arrayList.get(i);
        viewHolder.textCityName.setText(modelDoctorRecentSearch.getUserSearchKeyword());

        viewHolder.textCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ActivityHomeScreen.class)
                .putExtra("searchKeyWord", modelDoctorRecentSearch.getUserSearchKeyword()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        viewHolder.imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setOnClick(i);
            }
        });
        final Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        viewHolder.textCityName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                vibrator.vibrate(20);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final AlertDialog alertDialog = builder.create();
                builder.setTitle(modelDoctorRecentSearch.getUserSearchKeyword());
                builder.setMessage("Remove from search history?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return false;
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
        public ViewHolder(@NonNull View view){
            super(view);
            textCityName = (TextView)view.findViewById(R.id.text_city_name);
            imageViewSearch = (ImageView)view.findViewById(R.id.image);
        }
    }

    public void filterList(ArrayList<ModelDoctorRecentSearch> filteredList){
        arrayList = filteredList;
        notifyDataSetChanged();
    }
}
