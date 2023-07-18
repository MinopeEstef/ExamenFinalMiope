package com.example.examenfinalmiope.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.examenfinalmiope.Entity.Holiday;
import com.example.examenfinalmiope.Entity.ResponseDB;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATA_BASE = "calender.db";
    private static DataBaseHelper instance;

    public DataBaseHelper(@Nullable Context context) {
        super(context,DATA_BASE,null,1);
    }
    public static synchronized DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableCalender = "create table Holiday(idHoliday integer primary key autoincrement," +
                "date text,name text, country text, localName text)";
        db.execSQL(createTableCalender);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public List<Holiday> getHolidayList(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Holiday> listHoliday = new ArrayList<>();
        String selectQuery = "SELECT * FROM Holiday";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                int idHoliday = cursor.getInt(0);
                String date = cursor.getString(1);
                String name = cursor.getString(2);
                String country = cursor.getString(3);
                String localName = cursor.getString(4);

                Holiday holiday1 = new Holiday(idHoliday,date,name,country,localName);
                listHoliday.add(holiday1);

            }while (cursor.moveToNext());

        }else{
            //Fallaría
        }
        return listHoliday;
    }
    public boolean getHolidayItem(Holiday holiday){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM Holiday WHERE date = '"+holiday.getDate()+"'" +
                " and localName = '"+holiday.getLocalName()+"'" +
                " and country = '"+holiday.getCountryCode()+"' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor.getCount() > 0;
    }
    public ResponseDB postHoliday(Holiday holiday) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", holiday.getDate());
        values.put("name", holiday.getName());
        values.put("country", holiday.getCountryCode());
        values.put("localName", holiday.getLocalName());

        try {
            long insert = db.insert("Holiday",null,values);
            if(insert == -1){
                return  new ResponseDB(false,"Error al insertar");
            }else{
                return new ResponseDB(true,"Se guardó correctamente");
            }
        }catch (Exception ex){
            Log.d("Message : ",ex.getMessage());
            return new ResponseDB(false,ex.getMessage());
        }
    }

}
