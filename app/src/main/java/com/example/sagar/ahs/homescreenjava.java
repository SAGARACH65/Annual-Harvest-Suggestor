package com.example.sagar.ahs;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;



public class homescreenjava extends AppCompatActivity {
    private Cursor cursor;
    private SQLiteDatabase db;
    public void addNewFields(View view) {

        Intent callnew =new Intent(this,AddNewField.class);
        startActivity(callnew);
        finish();
    }
    public void showUserInfo(View view){

        Intent callnew =new Intent(this,showuserinfo.class);
        startActivity(callnew);
        finish();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        ListView m_listview = (ListView) findViewById(R.id.list_view);

        SQLiteOpenHelper AgroDatabase = new AgroDatabase(this);
         db = AgroDatabase.getReadableDatabase();
        cursor=db.query("fieldinfo",new String[]{"_id", "FieldName","Area","Measure_Unit"},null,null,null,null,null);
        cursor.moveToFirst();




        //THIS PORTION IS SHOWING THE USER FIELD NAMES IN LISTACTIVITY
        CursorAdapter listAdapter= new SimpleCursorAdapter(this,
                                            android.R.layout.simple_list_item_1,
                                            cursor,
                                            new String[]{"FieldName"},
                                            new int[]{android.R.id.text1},0);

        m_listview.setAdapter(listAdapter);
        m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(homescreenjava.this, Addition1.class);
                final String name= cursor.getString(1);
                final String Area=cursor.getString(2);
                final String M_unit=cursor.getString(3);
                //final String Crop_grown=cursor.getString(4);
            Bundle extras = new Bundle();

                extras.putString("name",name);
                extras.putString("Area",Area);
                extras.putString("Measurement Unit",M_unit);
              // extras.putString("Crop planted",Crop_grown);
               intent.putExtras(extras);
                startActivity(intent);


            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }





}