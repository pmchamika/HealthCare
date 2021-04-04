package com.android.healthcare.IT17152938.Adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class DatePickerAddapter implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText _editText;
    private int _day;
    private int _month;
    private int _Year;
    private Context _context;


    public DatePickerAddapter(Context context, int editTextViewID)
    {
        Activity act = (Activity)context;
        this._editText = (EditText)act.findViewById(editTextViewID);
        this._editText.setOnClickListener(this);
        this._context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        _Year = year;
        _month = monthOfYear;
        _day = dayOfMonth;
        updateDisplay();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        String a=_editText.getText().toString();
        if("".equalsIgnoreCase(a)){
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

            DatePickerDialog dialog = new DatePickerDialog(_context, this,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }else{
            try {

                DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate date1 = LocalDate.parse( a , pattern );
                DatePickerDialog dialog = new DatePickerDialog(_context, this,
                        date1.getYear(),date1.getMonthValue()-1,
                        date1.getDayOfMonth());
                dialog.show();
            } catch (Exception e) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                DatePickerDialog dialog = new DatePickerDialog(_context, this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        }


    }

    // updates the date in the birth date EditText
    private void updateDisplay() {

        _editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_Year).append("-").append(_month + 1).append("-").append(_day).append(""));
    }
}
