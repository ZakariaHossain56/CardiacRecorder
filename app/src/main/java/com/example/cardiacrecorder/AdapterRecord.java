package com.example.cardiacrecorder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AdapterRecord extends RecyclerView.Adapter<AdapterRecord.HolderRecord> implements Filterable  {

    private Context context;
    public ArrayList<single_record> RecordList,filterlist;
    private FilterRecord fild;

//strings
    String systolic,diastolic,heart,date,time;


    //getter-setter-constructor
    public void setRecordList(ArrayList<single_record> RecordList) {
        this.RecordList = RecordList;
    }

    public AdapterRecord(Context context, ArrayList<single_record> recordList) {
        this.context = context;
        this.RecordList = recordList;
        this.filterlist = new ArrayList<>(recordList);
    }


    /**
     *
     * @param parent
     * Viewgroup
     * @param viewType
     * viewType
     * @return
     */

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



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getBindingAdapterPosition();
                single_record hm=RecordList.get(pos);
                String userID=hm.getUserID();
                String userID1=hm.getRecordID();


                //Toast.makeText(context, hospitalname, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,RecordDetailsActivity.class);
                intent.putExtra("USERRRR_UID",userID);
                intent.putExtra("RECORDDD_UID",userID1);
//                String s=v.getContext().toString();
//                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                // Toast.makeText(context, "Item clicked", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return RecordList.size();
    }

    @Override
    public Filter getFilter() {
        if(fild==null){
            fild= new FilterRecord(this,filterlist);

        }
        return fild;
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
