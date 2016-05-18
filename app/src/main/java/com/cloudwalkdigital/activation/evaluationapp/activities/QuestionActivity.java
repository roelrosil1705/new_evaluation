package com.cloudwalkdigital.activation.evaluationapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.adapter.AccountsAdapter;
import com.cloudwalkdigital.activation.evaluationapp.adapter.QuestionAdapter;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;
import com.cloudwalkdigital.activation.evaluationapp.models.DataBaseHelper;
import com.cloudwalkdigital.activation.evaluationapp.R;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends AppCompatActivity {

    @Bind(R.id.header_title)
    TextView hTitle;
    @Bind(R.id.department_label)
    TextView dLabel;
    @Bind(R.id.section_label)
    TextView sLabel;
    @Bind(R.id.question_label)
    TextView qLabel;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ll_heading)
    LinearLayout ll_heading;
    @Bind(R.id.vp_container)
    ViewPager vp_container;
    @Bind(R.id.progressBar)
    ProgressBar pBar;
    int currentPage;


    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    String LOG_IN = "User";

    public String EVALUATOR = "AE";
    public String DEPT_QUESTION = "Accounts";
    public String EVENT_NAME = "DOVE EVENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        pBar.setProgress(0);
        sharedPreference = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        LOG_IN = sharedPreference.getString("EVALUATOR", "");
        switch(LOG_IN){
            case "accounts":
                dLabel.setText("Operations");
                toolbar.setBackgroundResource(R.color.cmtuvaColor);
                ll_heading.setBackgroundResource(R.color.cmtuvaColor);
                break;
            case "cmtuva":
                toolbar.setBackgroundResource(R.color.cmtuvaColor);
                ll_heading.setBackgroundResource(R.color.cmtuvaColor);
                break;
        }
        //Constant.accountsQuestion[0][0][0][0] //choices count
        //Constant.accountsQuestion[0][1][0][0] //question
        //Constant.accountsQuestion[0][2][1][0] //answers
        //Toast.makeText(getApplicationContext(),"length = " + Constant.accountsQuestion[0][2][1][0],Toast.LENGTH_LONG).show();
        QuestionAdapter.setContext(getApplicationContext());
        //vp_container.setAdapter(new AccountsAdapter(getSupportFragmentManager()));
        vp_container.setAdapter(new QuestionAdapter(getSupportFragmentManager()));
        qLabel.setText("Question "+(vp_container.getCurrentItem() + 1)+" of "+vp_container.getAdapter().getCount());
        vp_container.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            public void onPageSelected(int position) {
                qLabel.setText("Question "+(vp_container.getCurrentItem() + 1)+" of "+vp_container.getAdapter().getCount());
                currentPage = position;
                int curPosition = vp_container.getCurrentItem() + 1;
                int totalItems = vp_container.getAdapter().getCount();
                float progress = (float) curPosition / totalItems;
                float pTotal = progress * 100;
                pBar.setProgress((int)((long) pTotal));
                toolbar.setTitleTextColor(Color.WHITE);
                toolbar.setSubtitleTextColor(Color.WHITE);
                hTitle.setTextColor(Color.WHITE);
                dLabel.setTextColor(Color.WHITE);
                switch(LOG_IN){
                    case "accounts":
                        if(curPosition > 0 && curPosition <= 5){
                            dLabel.setText("Operations");
                            toolbar.setBackgroundResource(R.color.cmtuvaColor);
                            ll_heading.setBackgroundResource(R.color.cmtuvaColor);
                        }else if(curPosition > 5 && curPosition <= 9){
                            dLabel.setText("Project Managers");
                            toolbar.setBackgroundResource(R.color.pmColor);
                            ll_heading.setBackgroundResource(R.color.pmColor);
                            toolbar.setTitleTextColor(Color.BLACK);
                            toolbar.setSubtitleTextColor(Color.BLACK);
                            hTitle.setTextColor(Color.BLACK);
                            dLabel.setTextColor(Color.BLACK);
                        }else if(curPosition > 9 && curPosition <= 13){
                            dLabel.setText("Team Leaders Rating");
                            toolbar.setBackgroundResource(R.color.pmColor);
                            ll_heading.setBackgroundResource(R.color.pmColor);
                            toolbar.setTitleTextColor(Color.BLACK);
                            toolbar.setSubtitleTextColor(Color.BLACK);
                            hTitle.setTextColor(Color.BLACK);
                            dLabel.setTextColor(Color.BLACK);
                        }else if(curPosition > 13 && curPosition <= 15){
                            dLabel.setText("Setup");
                            toolbar.setBackgroundResource(R.color.pmColor);
                            ll_heading.setBackgroundResource(R.color.pmColor);
                            toolbar.setTitleTextColor(Color.BLACK);
                            toolbar.setSubtitleTextColor(Color.BLACK);
                            hTitle.setTextColor(Color.BLACK);
                            dLabel.setTextColor(Color.BLACK);
                        }else if(curPosition > 15 && curPosition <= 22){
                            dLabel.setText("Setup Leaders Assessment");
                            toolbar.setBackgroundResource(R.color.pmColor);
                            ll_heading.setBackgroundResource(R.color.pmColor);
                            toolbar.setTitleTextColor(Color.BLACK);
                            toolbar.setSubtitleTextColor(Color.BLACK);
                            hTitle.setTextColor(Color.BLACK);
                            dLabel.setTextColor(Color.BLACK);
                        }else if(curPosition > 22 && curPosition <= 36){
                            dLabel.setText("Production");
                            toolbar.setBackgroundResource(R.color.prColor);
                            ll_heading.setBackgroundResource(R.color.prColor);
                        }else if(curPosition > 36 && curPosition <= 47){
                            dLabel.setText("Inventory");
                            toolbar.setBackgroundResource(R.color.invColor);
                            ll_heading.setBackgroundResource(R.color.invColor);
                        }else if(curPosition > 47 && curPosition <= 52){
                            dLabel.setText("Human Resource Department");
                            toolbar.setBackgroundResource(R.color.hrColor);
                            ll_heading.setBackgroundResource(R.color.hrColor);
                        }
                        break;
                    case "cmtuva":
                        toolbar.setBackgroundResource(R.color.cmtuvaColor);
                        ll_heading.setBackgroundResource(R.color.cmtuvaColor);
                        break;
                }
            }
        });


        Typeface mostserratBold = Typeface.createFromAsset(getAssets(), Constant.mostserratBold);
        Typeface mostserratRegular = Typeface.createFromAsset(getAssets(), Constant.mostserratRegular);
        Typeface mostserratLight = Typeface.createFromAsset(getAssets(), Constant.mostserratLight);
        hTitle.setTypeface(mostserratRegular);
        sLabel.setTypeface(mostserratRegular);
        qLabel.setTypeface(mostserratRegular);


    }

    @OnClick(R.id.nxtBtn)
    public void nextPage(){
        int totalPage = vp_container.getAdapter().getCount();
        int page = getItem() + 1;
        if(page <= totalPage){
            vp_container.setCurrentItem(page, true);
        }
    }

    @OnClick(R.id.prevBtn)
    public void prevPage(){
        int page = getItem() - 1;
        if(page >= 0){
            vp_container.setCurrentItem(page, true);
        }
    }

    private int getItem() {
        return vp_container.getCurrentItem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
