package com.example.cardiacrecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecord extends RecyclerView.Adapter<AdapterRecord.HolderRecord>  {

    private Context context;
    public ArrayList<single_record> RecordList;

//strings
    String systolic,diastolic,heart,date,time;


    //getter-setter-constructor
    public void setRecordList(ArrayList<single_record> RecordList) {
        this.RecordList = RecordList;
    }

    public AdapterRecord(Context context, ArrayList<single_record> recordList) {
        this.context = context;
        this.RecordList = recordList;
    }



    @NonNull
    @Override
    public HolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_row,parent,false);

        return new HolderRecord(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecord holder, int position) {

        single_record modelrec=RecordList.get(position);
        String sp=modelrec.getSys();
        String dp= modelrec.getDias();
        String hr=modelrec.getRate();
        String date=modelrec.getDate();
        String time=modelrec.getTime();




        holder.SystolicTV.setText("Systolic Pressure : "+sp);
        holder.DiastolicTV.setText("Diastolic Pressure : "+dp);
        holder.heartTV.setText("Heart Rate : " +hr);
        holder.DateTV.setText("Date : "+date);
        holder.TimeTV.setText("Time : "+time);

    }

    @Override
    public int getItemCount() {
        return RecordList.size();
    }

    class HolderRecord extends RecyclerView.ViewHolder{

        private TextView SystolicTV,DiastolicTV,heartTV,DateTV,TimeTV;

         public HolderRecord(@NonNull View itemView) {
             super(itemView);
             SystolicTV=itemView.findViewById(R.id.SystolicTV);
             DiastolicTV=itemView.findViewById(R.id.DiastolicTV);
             heartTV=itemView.findViewById(R.id.heartTV);
             DateTV=itemView.findViewById(R.id.DateTV);
             TimeTV=itemView.findViewById(R.id.TimeTV);

         }
     }
}
