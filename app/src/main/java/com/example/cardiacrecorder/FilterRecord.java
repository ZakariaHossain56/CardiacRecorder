package com.example.cardiacrecorder;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterRecord extends Filter {

    private AdapterRecord adapter;
    private ArrayList<single_record> filterlist;

    public FilterRecord(AdapterRecord adapter, ArrayList<single_record> filterlist) {
        this.adapter = adapter;
        this.filterlist = filterlist;
    }

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

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.RecordList=(ArrayList<single_record>)results.values;
        adapter.notifyDataSetChanged();

    }
}
