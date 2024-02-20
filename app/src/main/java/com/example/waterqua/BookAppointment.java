package com.example.waterqua;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {
    private EditText ed1, ed2, ed3, ed4;
    private TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button datebutton,timebutton,backbutton,bookbutton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        // Find views by ID
        tv = findViewById(R.id.textView2);
        ed1 = findViewById(R.id.bookappointmentedittext);
        ed2 = findViewById(R.id.editTextbookappointmentaddress);
        ed3 = findViewById(R.id.editbookappointmet);
        ed4 = findViewById(R.id.editTextbookappointmentfee);
        timebutton=findViewById(R.id.buttondate);
        datebutton=findViewById(R.id.buttontime);
        backbutton=findViewById(R.id.buttonbackbook);
        bookbutton=findViewById(R.id.buttonbookappointment);


        // Disable editing for pre-filled fields
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        // Handle intent data (assuming data is passed correctly)
        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons fees: " + fees + "/=");

        // Initialize date picker (modify as needed)
        initDatePicker();
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                datebutton.setText(i2+"/"+i1+"/"+i);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year= cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int Day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,Day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

}
    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
             timebutton.setText(i+":"+i1);

            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs= cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style= AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointment.this,DetailedActivity.class));
            }
        });
        bookbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = getIntent();
                String title = it.getStringExtra("text1");
                String fullname = ed1.getText().toString();
                String address = ed2.getText().toString();
                String contact = ed3.getText().toString();
                String fees = ed4.getText().toString();
                //DBHelper db= new DBHelper(BookAppointment.this, "WaterQuality.db", null, 1);

                DBHelper db = new DBHelper(getApplicationContext(),"WaterQuality",null,1);
                if(db.checkAppointmentExists(title+"=>"+fullname,address,contact,fees,datebutton.getText().toString(),timebutton.getText().toString())==1){
                    Toast.makeText(getApplicationContext(), "Appointment already done", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.insertData(title+"=>"+fullname,address,contact,fees ,datebutton.getText().toString(),timebutton.getText().toString());//Float.parseFloat(fees
                    Toast.makeText(getApplicationContext(), "Appointment booking was successfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointment.this,CheckbookActivity.class));
                }
            }
        });

    }
}
