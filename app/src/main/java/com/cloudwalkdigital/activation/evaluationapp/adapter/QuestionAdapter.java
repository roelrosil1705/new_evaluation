package com.cloudwalkdigital.activation.evaluationapp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.activities.QuestionActivity;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;
import com.cloudwalkdigital.activation.evaluationapp.fragments.AccountsFragment_q1;
import com.cloudwalkdigital.activation.evaluationapp.fragments.AccountsFragment_q2;
import com.cloudwalkdigital.activation.evaluationapp.fragments.Choices3Fragment;
import com.cloudwalkdigital.activation.evaluationapp.fragments.QuestionFragment;

/**
 * Created by henry on 02/05/2016.
 */
public class QuestionAdapter extends FragmentPagerAdapter {

    public static Context mContext;

    public static final String PREFS_NAME = "MyPref";
    public static SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;
    String LOG_IN = "User";

    public String EVALUATOR = "AE";
    public String DEPT_QUESTION = "Accounts";
    public String EVENT_NAME = "DOVE EVENT";

    public QuestionAdapter(FragmentManager fm) {
        super(fm);
    }

    public static void setContext(Context context){
        mContext = context;
        sharedPreference = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Fragment getItem(int position) {
        LOG_IN = sharedPreference.getString("EVALUATOR", "");
        switch(LOG_IN){
            case "accounts": return QuestionFragment.newInstance(position,"accounts");
            case "cmtuva": return Choices3Fragment.newInstance(position,"cmtuva");
        }
        return null;

    }

    @Override
    public int getCount() {
        LOG_IN = sharedPreference.getString("EVALUATOR", "");
        switch(LOG_IN){
            case "accounts":
                return Constant.accountsQuestion.length;
            case "cmtuva":
                return Constant.accountsQuestion.length;
        }
        return 0;
    }
}
