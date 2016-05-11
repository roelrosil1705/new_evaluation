package com.cloudwalkdigital.activation.evaluationapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.activities.AdminDashboardActivity;
import com.cloudwalkdigital.activation.evaluationapp.activities.FinishActivity;
import com.cloudwalkdigital.activation.evaluationapp.adapter.QuestionAdapter;
import com.cloudwalkdigital.activation.evaluationapp.constant.Constant;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    @Bind(R.id.choices1)            TextView choices1;
    @Bind(R.id.questionHead_num)    TextView qNum;
    @Bind(R.id.questionHead_label)  TextView qLabel;
    @Bind(R.id.single)              LinearLayout llSingle;
    @Bind(R.id.multiple)            LinearLayout llMultiple;
    @Bind(R.id.rb1)                 RadioButton rb1;
    @Bind(R.id.rb2)                 RadioButton rb2;
    @Bind(R.id.rb3)                 RadioButton rb3;
    @Bind(R.id.rb4)                 RadioButton rb4;
    @Bind(R.id.rb5)                 RadioButton rb5;
    @Bind(R.id.rb6)                 RadioButton rb6;
    @Bind(R.id.rb7)                 RadioButton rb7;
    @Bind(R.id.rb8)                 RadioButton rb8;
    @Bind(R.id.ck1)                 CheckBox ck1;
    @Bind(R.id.ck2)                 CheckBox ck2;
    @Bind(R.id.ck3)                 CheckBox ck3;
    @Bind(R.id.ck4)                 CheckBox ck4;
    @Bind(R.id.ck5)                 CheckBox ck5;
    @Bind(R.id.ck6)                 CheckBox ck6;
    @Bind(R.id.ck7)                 CheckBox ck7;
    @Bind(R.id.ck8)                 CheckBox ck8;

    public static final String PREFS_NAME = "MyPref";
    SharedPreferences sharedPreference;
    ViewPager vp;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(int param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            sharedPreference = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }
        vp = (ViewPager) getActivity().findViewById(R.id.vp_container);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.answer_sheet, container, false);
        ButterKnife.bind(this,v);
        //layout
        switch(Constant.accountsQuestion[mParam1][0][0][0]){
            case "1":
                llSingle.setVisibility(View.VISIBLE);
                rb1.setVisibility(View.VISIBLE);
                rb2.setVisibility(View.VISIBLE);
                choices1.setVisibility(View.VISIBLE);
                break;
            case "2":
                llMultiple.setVisibility(View.VISIBLE);
                ck1.setVisibility(View.VISIBLE);
                ck2.setVisibility(View.VISIBLE);
                break;
            case "3":
                llMultiple.setVisibility(View.VISIBLE);
                ck1.setVisibility(View.VISIBLE);
                ck2.setVisibility(View.VISIBLE);
                ck3.setVisibility(View.VISIBLE);
                break;
            case "4":
                llMultiple.setVisibility(View.VISIBLE);
                ck1.setVisibility(View.VISIBLE);
                ck2.setVisibility(View.VISIBLE);
                ck3.setVisibility(View.VISIBLE);
                ck4.setVisibility(View.VISIBLE);
                break;
            case "5":
                llMultiple.setVisibility(View.VISIBLE);
                ck1.setVisibility(View.VISIBLE);
                ck2.setVisibility(View.VISIBLE);
                ck3.setVisibility(View.VISIBLE);
                ck4.setVisibility(View.VISIBLE);
                ck5.setVisibility(View.VISIBLE);
                break;
            case "6":
                llMultiple.setVisibility(View.VISIBLE);
                ck1.setVisibility(View.VISIBLE);
                ck2.setVisibility(View.VISIBLE);
                ck3.setVisibility(View.VISIBLE);
                ck4.setVisibility(View.VISIBLE);
                ck5.setVisibility(View.VISIBLE);
                ck6.setVisibility(View.VISIBLE);
                break;
            case "7":
                llMultiple.setVisibility(View.VISIBLE);
                ck1.setVisibility(View.VISIBLE);
                ck2.setVisibility(View.VISIBLE);
                ck3.setVisibility(View.VISIBLE);
                ck4.setVisibility(View.VISIBLE);
                ck5.setVisibility(View.VISIBLE);
                ck6.setVisibility(View.VISIBLE);
                ck7.setVisibility(View.VISIBLE);
                break;
            case "8":
                llMultiple.setVisibility(View.VISIBLE);
                ck1.setVisibility(View.VISIBLE);
                ck2.setVisibility(View.VISIBLE);
                ck3.setVisibility(View.VISIBLE);
                ck4.setVisibility(View.VISIBLE);
                ck5.setVisibility(View.VISIBLE);
                ck6.setVisibility(View.VISIBLE);
                ck7.setVisibility(View.VISIBLE);
                ck8.setVisibility(View.VISIBLE);
                break;
        }
        int questionNum = mParam1 + 1;
        int layout = Integer.parseInt(Constant.accountsQuestion[mParam1][0][0][0]);
        switch(mParam2){
            case "accounts":
                qNum.setText(questionNum + ".");
                qLabel.setText(Constant.accountsQuestion[mParam1][1][0][0]);
                switch(layout){
                    case 1:
                        rb1.setText("Yes");
                        rb2.setText("No");
                        choices1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        break;
                    case 2:
                        ck1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        break;
                    case 3:
                        ck1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        break;
                    case 4:
                        ck1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestion[mParam1][2][3][0]);
                        break;
                    case 5:
                        ck1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestion[mParam1][2][3][0]);
                        ck5.setText(Constant.accountsQuestion[mParam1][2][4][0]);
                        break;
                    case 6:
                        ck1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestion[mParam1][2][3][0]);
                        ck5.setText(Constant.accountsQuestion[mParam1][2][4][0]);
                        ck6.setText(Constant.accountsQuestion[mParam1][2][5][0]);
                        break;
                    case 7:
                        ck1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestion[mParam1][2][3][0]);
                        ck5.setText(Constant.accountsQuestion[mParam1][2][4][0]);
                        ck6.setText(Constant.accountsQuestion[mParam1][2][5][0]);
                        ck7.setText(Constant.accountsQuestion[mParam1][2][6][0]);
                        break;
                    case 8:
                        ck1.setText(Constant.accountsQuestion[mParam1][2][0][0]);
                        ck2.setText(Constant.accountsQuestion[mParam1][2][1][0]);
                        ck3.setText(Constant.accountsQuestion[mParam1][2][2][0]);
                        ck4.setText(Constant.accountsQuestion[mParam1][2][3][0]);
                        ck5.setText(Constant.accountsQuestion[mParam1][2][4][0]);
                        ck6.setText(Constant.accountsQuestion[mParam1][2][5][0]);
                        ck7.setText(Constant.accountsQuestion[mParam1][2][6][0]);
                        ck8.setText(Constant.accountsQuestion[mParam1][2][7][0]);
                        break;
                }
                break;
        }
        return v;
    }

    @OnClick({R.id.rb1,R.id.rb2,R.id.rb3,R.id.rb4,R.id.rb5,R.id.rb6,R.id.rb7,R.id.rb8,R.id.ck1,R.id.ck2,R.id.ck3,R.id.ck4,R.id.ck5,R.id.ck6,R.id.ck7,R.id.ck8})
    public void getAnswer(View v){
        int questionNum = mParam1 + 1;
        SharedPreferences.Editor editor = sharedPreference.edit();
        int currentPage = vp.getCurrentItem() + 1;
        switch(v.getId()){
            case R.id.rb1:
                editor.putInt("a"+questionNum, 1);
                break;
            case R.id.rb2:
                editor.putInt("a"+questionNum, 2);
                break;
            case R.id.rb3:
                editor.putInt("a"+questionNum, 3);
                break;
            case R.id.rb4:
                editor.putInt("a"+questionNum, 4);
                break;
            case R.id.rb5:
                editor.putInt("a"+questionNum, 5);
                break;
            case R.id.rb6:
                editor.putInt("a"+questionNum, 6);
                break;
            case R.id.rb7:
                editor.putInt("a"+questionNum, 7);
                break;
            case R.id.rb8:
                editor.putInt("a"+questionNum, 8);
                break;
            case R.id.ck1:
                editor.putInt("a"+questionNum, 1);
                break;
            case R.id.ck2:
                editor.putInt("a"+questionNum, 2);
                break;
            case R.id.ck3:
                editor.putInt("a"+questionNum, 3);
                break;
            case R.id.ck4:
                editor.putInt("a"+questionNum, 4);
                break;
            case R.id.ck5:
                editor.putInt("a"+questionNum, 5);
                break;
            case R.id.ck6:
                editor.putInt("a"+questionNum, 6);
                break;
            case R.id.ck7:
                editor.putInt("a"+questionNum, 7);
                break;
            case R.id.ck8:
                editor.putInt("a"+questionNum, 8);
                break;
        }
        editor.commit();
        if(vp.getCurrentItem() <= vp.getAdapter().getCount()){
            vp.setCurrentItem(currentPage);
        }
        if(questionNum == vp.getAdapter().getCount()){
            startActivity(new Intent(getActivity(), FinishActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            getActivity().finish();
        }
    }

}
