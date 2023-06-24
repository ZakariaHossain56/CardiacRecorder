package com.example.cardiacrecorder;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PickTime extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private final StringListener listener;

    public PickTime(StringListener listener){
        this.listener = listener;
    }

    public interface StringListener {
        void onStringSelected(String value);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        LocalTime localTime = LocalTime.now();

        int hour = localTime.getHour();
        int minute = localTime.getMinute();

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));

    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int min) {

        LocalTime localTime = LocalTime.of(hour,min);
        String pattern = "hh:mma";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);

        listener.onStringSelected(formatter.format(localTime));

    }

}
