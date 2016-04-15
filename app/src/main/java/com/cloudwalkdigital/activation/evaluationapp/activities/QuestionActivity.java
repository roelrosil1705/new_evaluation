package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.models.DataBaseHelper;
import com.cloudwalkdigital.activation.evaluationapp.R;

import java.io.IOException;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        DataBaseHelper myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }

        String[][] text = myDbHelper.getDepartments();
        int cc = text.length - 1;
        for(int i=0;i<=cc;i++){
            Toast.makeText(getApplicationContext(),text[i][1]+"",Toast.LENGTH_LONG).show();
        }

    }
}
