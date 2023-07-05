package com.example.cardiacrecorder;

import android.widget.Filter;

import java.util.ArrayList;



public class FilterRecord extends Filter {

    private AdapterRecord adapter;
    private ArrayList<single_record> filterlist;

    /**
     *Constructs a new FilterRecord object.
     * @param adapter
     * @param filterlist
     */
    public FilterRecord(AdapterRecord adapter, ArrayList<single_record> filterlist) {
        this.adapter = adapter;
        this.filterlist = filterlist;
    }


    /**
     *Performs the filtering operation on the dataset.
     *
     *
     * @param constraint The constraint to filter the dataset by.
     * @return  The results of the filtering operation.
     */
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results= new FilterResults();
        if(constraint!=null && constraint.length()>0)
        {
            constraint=constraint.toString().toUpperCase();//make case insensitive for query
            ArrayList<single_record>filtermodel= new ArrayList<>();
            for(int i=0;i<filterlist.size();i++)
            {
                if (filterlist.get(i).getDate().toUpperCase().contains(constraint))
                {
                    filtermodel.add(filterlist.get(i));
                }

            }
            results.count= filtermodel.size();
            results.values=filtermodel;
        }
        else
        {
            results.count= filterlist.size();
            results.values=filterlist;

        }

        return results;
    }


    /**
     *   Publishes the results of the filtering operation to the adapter.
     *
     *
     * @param constraint  The constraint used for filtering.
     * @param results shows the result of filtering operation
     */
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.RecordList=(ArrayList<single_record>)results.values;
        adapter.notifyDataSetChanged();

    }
}
