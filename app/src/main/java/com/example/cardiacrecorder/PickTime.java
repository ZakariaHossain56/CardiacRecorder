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

    /**
     * Constructs a new PickTime object with the specified listener.
     *
     * @param listener The listener to be notified when a time is selected.
     */
    public PickTime(StringListener listener){
        this.listener = listener;
    }



    public interface StringListener {
        void onStringSelected(String value);
    }

    /**
     * Called when the dialog is being created.
     *
     * @param savedInstanceState The saved instance state.
     * @return The created dialog.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        LocalTime localTime = LocalTime.now();

        int hour = localTime.getHour();
        int minute = localTime.getMinute();

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));

    }


    /**
     * Called when a time is set in the time picker.
     *
     * @param timePicker The time picker view.
     * @param hour The selected hour.
     * @param min The selected minute.
     */


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int min) {

        LocalTime localTime = LocalTime.of(hour,min);
        String pattern = "hh:mma";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);

        listener.onStringSelected(formatter.format(localTime));

    }

}
