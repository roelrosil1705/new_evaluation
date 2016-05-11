package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.adapter.ProjectAdapter;

/**
 * Created by Laiza-PC on 29/04/2016.
 */

public class EventActivity extends AppCompatActivity{
    Toolbar mActionBarToolbar;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, EventActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluator_type);

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(" Type of Event");
    }

}