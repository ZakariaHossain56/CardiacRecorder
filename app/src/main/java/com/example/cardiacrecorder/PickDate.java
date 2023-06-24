package com.example.cardiacrecorder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PickDate extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    private final StringListener listener;

    public PickDate(StringListener listener){
        this.listener = listener;
    }

    public interface StringListener {
        void onStringSelected(String value);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LocalDate localDate = LocalDate.now();

        int year = localDate.getYear();
        int month = localDate.getMonthValue()-1;
        int day = localDate.getDayOfMonth();

        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        LocalDate localDate = LocalDate.of(year,month+1,day);
//        month++;
//        String d = String.valueOf(day);
//        if(d.length() < 2) d = "0"+d;
//
//        String m = String.valueOf(month);
//        if(m.length() < 2) m = "0"+m;
//
//        String date = d+"/"+m+"/"+year;

        String pattern = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);

        listener.onStringSelected(formatter.format(localDate));

    }

}
