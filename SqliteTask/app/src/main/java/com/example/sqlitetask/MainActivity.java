package com.example.sqlitetask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.example.sqlitetask.Adapter.RcAdapter;
import com.example.sqlitetask.Model.Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String fname;
    String lname;
    ContentValues contentValues = new ContentValues();
    RcAdapter rcAdapter;
    private ArrayList<Model> listItems =new ArrayList<>();
    private SQLiteDatabase db;
    private SQLiteOpenHelper openHelper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Context context=getBaseContext();

        recyclerView=findViewById(R.id.rcView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        listItems= setValues(context);

        if(listItems.size()>0){

            recyclerView.setVisibility(View.VISIBLE);
            rcAdapter=new RcAdapter(MainActivity.this,listItems);
            recyclerView.setAdapter(rcAdapter);
            rcAdapter.notifyDataSetChanged();
        }
        else {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this,"No Data",Toast.LENGTH_LONG).show();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);

               //showDialogg(view);

            }
        });
    }

    public ArrayList<Model> setValues(Context context) {

        openHelper=new DatabaseHelper(context);
        db=openHelper.getReadableDatabase();
        ArrayList<Model> storedata =new ArrayList<>();
        String sql="select * from "+DatabaseHelper.TABLE_NAME;
        cursor=db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do{
                int id=Integer.parseInt(cursor.getString(0));
                String name=cursor.getString(1);
                String llname=cursor.getString(2);
                storedata.add(new Model(id,name,llname));
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        return storedata;

    }

    public void showDialogg(View view) {


        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custum_dialog);
        final EditText efname=dialog.findViewById(R.id.eFName);
        final EditText elname=dialog.findViewById(R.id.eLName);
        Button save=dialog.findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
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

                    contentValues.put(DatabaseHelper.F_NAME,fname);
                    contentValues.put(DatabaseHelper.L_NAME,lname);
                    db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

                    Toast.makeText(MainActivity.this,"Data saved",Toast.LENGTH_LONG).show();
                    ((Activity)MainActivity.this).finish();
                    MainActivity.this.startActivity(((Activity)MainActivity.this).getIntent());
                }

            }
        });
        Button cancle=dialog.findViewById(R.id.btnCancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



}
