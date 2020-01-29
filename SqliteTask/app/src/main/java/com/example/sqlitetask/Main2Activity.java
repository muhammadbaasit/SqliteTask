package com.example.sqlitetask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity{
    EditText efname,elname;
    String fname;
    String lname;
    Button btnSave,btnCancle;

    private SQLiteOpenHelper openHelper;
    Context context=getBaseContext();

    ContentValues contentValues = new ContentValues();
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        efname=findViewById(R.id.eFName);
        elname=findViewById(R.id.eLName);
        btnSave=findViewById(R.id.btnSave);
        btnCancle=findViewById(R.id.btnCancle);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname=efname.getText().toString().trim();
                lname=elname.getText().toString().trim();

                boolean isValid=true;
                if(TextUtils.isEmpty(fname)){
                    efname.setError("ENter First Name");
                    isValid=false;
                }else {
                    efname.setError(null);
                    isValid=true;
                }
                if(TextUtils.isEmpty(lname)){
                    elname.setError("ENter First Name");
                    isValid=false;
                }else {
                    elname.setError(null);
                    isValid=true;
                }
                if(isValid){

                    openHelper=new DatabaseHelper(Main2Activity.this);
                    db=openHelper.getWritableDatabase();

                    contentValues.put(DatabaseHelper.F_NAME,fname);
                    contentValues.put(DatabaseHelper.L_NAME,lname);
                    db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

                    Toast.makeText(Main2Activity.this,"Data saved",Toast.LENGTH_LONG).show();
                    Intent i =new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(i);

                }

            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Main2Activity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }




}
