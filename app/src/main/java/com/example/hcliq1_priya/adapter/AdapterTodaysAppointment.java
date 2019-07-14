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
import com.example.hcliq1_priya.model.ModelTodaysAppointment;

import java.util.ArrayList;

public class AdapterTodaysAppointment  extends RecyclerView.Adapter<AdapterTodaysAppointment.ViewHolder> {
    private Context context;
    private ArrayList<ModelTodaysAppointment> arrayList;

    public AdapterTodaysAppointment(Context context, ArrayList<ModelTodaysAppointment> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_todays_appointment,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterTodaysAppointment.ViewHolder viewHolder, final int i) {
        ModelTodaysAppointment modelTodaysAppointment=arrayList.get(i);
        viewHolder.textDoctorName.setText(modelTodaysAppointment.getDoctorName());
        viewHolder.textExpectedTime.setText(modelTodaysAppointment.getExpectedTime());
        viewHolder.textHospitalName.setText(modelTodaysAppointment.getHospitalName());
        viewHolder.textPatientName.setText(modelTodaysAppointment.getPatientFirstName());
        viewHolder.textWaitingDesc.setText(modelTodaysAppointment.getWaitingDesc());
       if (modelTodaysAppointment.getCurrentShiftFlag().equals("P")){
            viewHolder.textEmergencyMsg.setVisibility(View.VISIBLE);
            viewHolder.textEmergencyMsg.setText("You have missed your appointment. Please contact hospital to get your appointment status.");
            viewHolder.textViewAptmtTime.setText(" N/A ");
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textPatientName,textDoctorName,textHospitalName,textExpectedTime,textWaitingDesc,textEmergencyMsg,textViewAptmtTime;
        public ViewHolder(@NonNull View view){
            super(view);
            textPatientName = (TextView)view.findViewById(R.id.text_patient_name);
            textDoctorName = (TextView)view.findViewById(R.id.text_doctor_name);
            textHospitalName = (TextView)view.findViewById(R.id.text_hospital_name);
            textExpectedTime = (TextView)view.findViewById(R.id.text_expected_time);
            textWaitingDesc = (TextView)view.findViewById(R.id.text_waiting_desc);
            textEmergencyMsg = (TextView)view.findViewById(R.id.text_emergency_msg);
            textViewAptmtTime = (TextView)view.findViewById(R.id.text_appointment_time);
        }

    }
}


