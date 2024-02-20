package com.example.waterqua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "WaterQuality.db";
    private final Context mContext;

    public DBHelper(Context applicationContext, String waterQuality, Context context, int i) {
        super(applicationContext, "WaterQuality.db", null, 1);
        this.mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1="create table bookappointment(fullname text,address text,contactno text,fees text,date text,time text)";
        db.execSQL(qry1);
        String qry2="create table water_quality_predictions(turbidity real, conductivity real, hardness real, pH real, water_quality text, recommendation text)";
        db.execSQL(qry2);
        Log.d("DBHelper", "Tables created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table  if exists users");

    }

    public void insertData(String fullname, String address, String contact, String fees, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("address", address);
        contentValues.put("contactno", contact);
        contentValues.put("fees", fees);
        contentValues.put("date", date);
        contentValues.put("time", time);
        db.insert("bookappointment", null, contentValues);
        db.close();
    }
    public void insertPrediction(double turbidity, double conductivity, double hardness, double pH, String waterQuality, String recommendation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("turbidity", turbidity);
        contentValues.put("conductivity", conductivity);
        contentValues.put("hardness", hardness);
        contentValues.put("pH", pH);
        contentValues.put("water_quality", waterQuality);
        contentValues.put("recommendation", recommendation);
        db.insert("water_quality_predictions", null, contentValues);
        db.close();
    }

    public int checkAppointmentExists(String fullname, String address, String contact, String fees, String date, String time) {
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        // Log parameter values for debugging
        Log.d("DBHelper", "Checking appointment existence:");
        Log.d("DBHelper", "Fullname: " + fullname);
        Log.d("DBHelper", "Address: " + address);
        Log.d("DBHelper", "Contact: " + contact);
        Log.d("DBHelper", "Fees: " + fees);
        Log.d("DBHelper", "Date: " + date);
        Log.d("DBHelper", "Time: " + time);

        // Define query parameters
        String[] selectionArgs = {fullname, address, contact, fees, date, time};

        Cursor c = db.rawQuery("SELECT * FROM bookappointment WHERE fullname = ? AND address = ? AND contactno = ? AND fees = ? AND date = ? AND time = ?", selectionArgs);
        if (c.moveToFirst()) {
            result = 1;
            Log.d("DBHelper", "Appointment already exists");
        } else {
            Log.d("DBHelper", "Appointment does not exist");
        }

        // Close cursor and database
        c.close();
        db.close();

        return result;
    }


    // Add a new method in your DBHelper class to retrieve all bookings
    public ArrayList<String> getAllBookings() {
        ArrayList<String> bookings = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM bookappointment", null);
        if (cursor.moveToFirst()) {
            int fullnameIndex = cursor.getColumnIndex("fullname");
            int addressIndex = cursor.getColumnIndex("address");
            int contactIndex = cursor.getColumnIndex("contactno");
            int feesIndex = cursor.getColumnIndex("fees");
            int dateIndex = cursor.getColumnIndex("date");
            int timeIndex = cursor.getColumnIndex("time");

            do {
                // Check if column index is valid
                if (fullnameIndex != -1 && addressIndex != -1 && contactIndex != -1 &&
                        feesIndex != -1 && dateIndex != -1 && timeIndex != -1) {
                    String bookingDetails = cursor.getString(fullnameIndex) + "$" +
                            cursor.getString(addressIndex) + "$" +
                            cursor.getString(contactIndex) + "$" +
                            cursor.getString(feesIndex) + "$" +
                            cursor.getString(dateIndex) + "$" +
                            cursor.getString(timeIndex);
                    bookings.add(bookingDetails);
                } else {
                    // Handle invalid column index
                    // Log an error or throw an exception
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookings;
    }

    public ArrayList<String> getAllPredictions() {
        ArrayList<String> predictions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM water_quality_predictions", null);
        if (cursor.moveToFirst()) {
            int turbidityIndex = cursor.getColumnIndex("turbidity");
            int conductivityIndex = cursor.getColumnIndex("conductivity");
            int hardnessIndex = cursor.getColumnIndex("hardness");
            int pHIndex = cursor.getColumnIndex("pH");
            int waterQualityIndex = cursor.getColumnIndex("water_quality");
            int recommendationIndex = cursor.getColumnIndex("recommendation");

            do { if(turbidityIndex != -1 && conductivityIndex != -1 && hardnessIndex!= -1 &&
                    pHIndex != -1 && waterQualityIndex != -1 && recommendationIndex != -1) {
                String predictionDetails = "Turbidity: " + cursor.getDouble(turbidityIndex) + "$" +
                        "Conductivity: " + cursor.getDouble(conductivityIndex) + "$ " +
                        "Hardness: " +  cursor.getDouble(hardnessIndex) + "$ " +
                        "pH: " + cursor.getDouble(pHIndex) + "$" +
                        "Category: " + cursor.getString(waterQualityIndex) + "$ " +
                        "Recommend: " +  cursor.getString(recommendationIndex);
                predictions.add(predictionDetails);
            }else {

            }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return predictions;
    }
    public int getPredictionsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM water_quality_predictions", null);
        int count = 0;
        if (cursor != null) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
            cursor.close();
        }
        db.close();
        return count;
    }

}




