package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;
import com.cloudwalkdigital.activation.evaluationapp.utils.DatabaseAccess;
import com.cloudwalkdigital.activation.evaluationapp.utils.EvalBroadcast;
import com.cloudwalkdigital.activation.evaluationapp.utils.Globals;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;

    String LOG_IN = "User";
    ProgressDialog prgDialog;

    private UserLoginTask mAuthTask = null;

    ProgressDialog ringProgressDialog;
    Handler updateBarHandler;
    DatabaseAccess databaseAccess;

    @Bind(R.id.atv_email) EditText mEmailView;
    @Bind(R.id.et_password) EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        updateBarHandler = new Handler();

        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        //Toast.makeText(getApplicationContext(),Constant.accountsQuestion[0][2][1][1],Toast.LENGTH_LONG).show();
        prgDialog = new ProgressDialog(this);
        prgDialog.setCancelable(false);
        // BroadCase Receiver Intent Object
        Intent alarmIntent = new Intent(getApplicationContext(), EvalBroadcast.class);
        // Pending Intent Object
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Alarm Manager Object
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        // Alarm Manager calls BroadCast for every Ten seconds (10 * 1000), BroadCase further calls service to check if new records are inserted in
        // Remote MySQL DB
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 5000, 10 * 1000, pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sync_menu, menu);
        this.invalidateOptionsMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.sync_btn:
                syncEmployee();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void syncEmployee() {
        AsyncHttpClient client = new AsyncHttpClient();
        // Http Request Params Object
        RequestParams params = new RequestParams();
        prgDialog.setMessage("Syncing Employee. Please wait...");
        // Show ProgressBar
        prgDialog.show();
        // Make Http call to getusers.php
        client.post(Constant.baseUrl+"evaluation/admin/getAllEmployee/", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                // Hide ProgressBar
                prgDialog.hide();
                List<EmployeeModel> feedItemList = new ArrayList<EmployeeModel>();
                try{
                    JSONArray jsonArry = new JSONArray(response);
                    if (null == feedItemList) {
                        feedItemList = new ArrayList<EmployeeModel>();
                    }
                    for (int i = 0; i < jsonArry.length(); i++) {
                        EmployeeModel employee = new EmployeeModel();
                        JSONObject il = jsonArry.getJSONObject(i);
                        employee.setId(Integer.parseInt(il.getString("_id")));
                        employee.setFname(il.getString("emp_fname"));
                        employee.setLname(il.getString("emp_lname"));
                        employee.setDepartment(il.getString("emp_dept"));
                        employee.setEmail(il.getString("emp_email"));
                        employee.setPassword(il.getString("emp_pass"));
                        feedItemList.add(employee);
                    }
                    databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    databaseAccess.SaveSyncEmployee(feedItemList);
                    databaseAccess.close();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            // When error occured
            @Override
            public void onFailure(int statusCode, Throwable error, String content) {
                // TODO Auto-generated method stub
                // Hide ProgressBar
                prgDialog.hide();
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                } else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @OnClick(R.id.btn_log_in)
    public void Login() {
        ringProgressDialog = new ProgressDialog(LoginActivity.this);
        ringProgressDialog.setMessage("Please wait...");
        ringProgressDialog.setIndeterminate(true);
        ringProgressDialog.setCancelable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Here you should write your time consuming task...
                    // Let the progress ring for 10 seconds...
                    //Thread.sleep(10000);
                } catch (Exception e) {

                }
                //ringProgressDialog.dismiss();
            }
        }).start();
        attemptLogin();
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            ringProgressDialog.dismiss();
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);

        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private String mEvaluate = "";
        private String mRoles = "";
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;
        DatabaseAccess databaseAccess;
        Globals g = Globals.getInstance();
        EmployeeModel employee = null;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
            if(ringProgressDialog == null){
                ringProgressDialog.show();
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();

            employee = databaseAccess.getEmployee(mEmail);

            if(employee != null){
                if(employee.getPassword().equalsIgnoreCase(mPassword)){
                    result = "success";
                    g.setEmployee(employee);
                }else{
                    result = "fail";
                }
            }else{
                result = "fail";
            }
            databaseAccess.close();

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            String jsonStr = result;
            mAuthTask = null;
            //showProgress(false);
            ringProgressDialog.dismiss();
            if(result.equalsIgnoreCase("success")){
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putString("EVALUATOR", employee.getDepartment());
                editor.putString("EID", employee.getId()+"");
                editor.putString("USER", "evaluator");
                startActivity(new Intent(getApplicationContext(), ProjectsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                editor.commit();
                finish();
            }else{
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
                Toast.makeText(getApplicationContext(), "RECORD NOT FOUND", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled(){
            mAuthTask = null;
            ringProgressDialog.dismiss();
            //showProgress(false);
        }
    }
}
