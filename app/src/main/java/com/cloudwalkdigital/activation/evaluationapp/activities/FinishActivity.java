package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;

import java.util.Map;

public class FinishActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        TextView operations1 = (TextView) findViewById(R.id.operation1);
        TextView operations2 = (TextView) findViewById(R.id.operation2);
        TextView pm1 = (TextView) findViewById(R.id.pm1);
        TextView pm2 = (TextView) findViewById(R.id.pm2);
        int pageNum = 1;
        Map<String, ?> allEntries = sharedPreference.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
        }
        //t1.setText(""+sharedPreference.getInt("a"+pageNum, 0));
        int operationsScore = 0;
        int pmScore = 0;
        String rem = "PASSED";
        switch(sharedPreference.getString("EVALUATOR", "")){
            case "accounts":
                int curPosition = 1;
                int totalQuestion = Constant.accountsQuestion.length;
                for(int i=1;i<=totalQuestion;i++){
                    int qnum = i - 1;
                    int totalAnswers = Constant.accountsQuestion[qnum][2][0][0].length() / 2;
                    if(i > 0 && i <= 5){
                        //dLabel.setText("Operations");
                        if(sharedPreference.getInt("a"+i, 0) <= totalAnswers){
                            operationsScore = operationsScore + 1;
                        }
                    }else if(i > 5 && i <= 9){
                        //dLabel.setText("Project Managers");
                        if(sharedPreference.getInt("a"+i, 0) <= totalAnswers){
                            pmScore = pmScore + 1;
                        }
                    }else if(i > 9 && i <= 13){
                        //dLabel.setText("Team Leaders Rating");
                    }else if(i > 13 && i <= 15){
                        //dLabel.setText("Setup");
                    }else if(i > 15 && i <= 22){
                        //dLabel.setText("Setup Leaders Assessment");
                    }else if(i > 22 && i <= 36){
                        //dLabel.setText("Production");
                    }else if(i > 36 && i <= 47){
                        //dLabel.setText("Inventory");
                    }else if(i > 47 && i <= 52){
                        //dLabel.setText("Human Resource Department");
                    }
                }
                if(operationsScore == 5){
                    rem = "EXCELENT";
                }else if(operationsScore > 2 && operationsScore < 5){
                    rem = "GOOD";
                }else{
                    rem = "BAD";
                }
                operations1.setText("Operations");
                operations2.setText(""+operationsScore);
                if(pmScore == 4){
                    rem = "EXCELENT";
                }else if(pmScore > 1 && pmScore < 4){
                    rem = "GOOD";
                }else{
                    rem = "BAD";
                }
                pm1.setText("PROJECT MANAGER");
                pm2.setText(""+pmScore);
                break;
        }
    }
}
