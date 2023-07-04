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

/**
 * Adapter for a RecyclerView and specifies the type of ViewHolder it uses (HolderRecord in this case).
 * Also implements the Filterable interface, allowing the adapter to support filtering of the data.
 */
public class AdapterRecord extends RecyclerView.Adapter<AdapterRecord.HolderRecord> implements Filterable  {


    /**
     * Member variables context, RecordList, filterlist, and fild (filter) to manage the data and
     * perform filtering operations.
     */
    private Context context;
    public ArrayList<single_record> RecordList,filterlist;
    private FilterRecord fild;

//strings
    String systolic,diastolic,heart,date,time;



    public void setRecordList(ArrayList<single_record> RecordList) {
        this.RecordList = RecordList;
    }


    /**
     *   /
     * Initializes the member variables and assigns the provided data (recordList)
     * to the RecordList and filterlist ArrayLists.
     * @param context
     * @param recordList
     */
    public AdapterRecord(Context context, ArrayList<single_record> recordList) {
        this.context = context;
        this.RecordList = recordList;
        this.filterlist = new ArrayList<>(recordList);
    }


    /**
     * The onCreateViewHolder method is responsible for inflating the layout for each item in the RecyclerView.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public HolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_row,parent,false);

        return new HolderRecord(view);
    }

    /**
     * The onBindViewHolder method is called for each item in the RecyclerView to bind the data
     * to the views in the ViewHolder. It retrieves the data for the current position and sets it
     * to the respective TextViews.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull HolderRecord holder, int position) {

        single_record modelrec=RecordList.get(position);
        String sp=modelrec.getSys();
        String dp= modelrec.getDias();
        String hr=modelrec.getRate();
        String date=modelrec.getDate();
        String time=modelrec.getTime();




        holder.SystolicTV.setText("Systolic Pressure : "+sp+ "mm Hg");
        holder.DiastolicTV.setText("Diastolic Pressure : "+dp+"mm Hg");
        holder.heartTV.setText("Heart Rate : " +hr+"bpm");
        holder.DateTV.setText("Date : "+date);
        holder.TimeTV.setText("Time : "+time);


        /**
         * The onBindViewHolder method also sets an OnClickListener on each item,
         * which opens a new activity (RecordDetailsActivity) when the item is clicked.
         * It passes some data to the new activity using intent extras.
         */
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

    /**
     * The getItemCount method returns the total number of items in the data list.
     * @return
     */
    @Override
    public int getItemCount() {
        return RecordList.size();
    }

    /**
     * The getFilter method is implemented to support filtering of the data.
     * It checks if the filter instance (fild) is null and creates a new instance if it is.
     * @return
     */
    @Override
    public Filter getFilter() {
        if(fild==null){
            fild= new FilterRecord(this,filterlist);
        }
        return fild;
    }


    /**
     * The HolderRecord class is a ViewHolder that holds references to the views in a single item layout.
     * It extends RecyclerView.ViewHolder and initializes the TextViews in its constructor.
     */
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
