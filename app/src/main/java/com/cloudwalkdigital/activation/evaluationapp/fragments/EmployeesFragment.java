package com.cloudwalkdigital.activation.evaluationapp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.activities.CreateEmployeeActivity;
import com.cloudwalkdigital.activation.evaluationapp.adapter.EmployeeAdapter;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by jpjpjp28 on 14/04/2016.
 */
public class EmployeesFragment extends Fragment {

    private Realm realm;
    private RealmConfiguration realmConfig;

    String name, jobTitle, role;
    private List<EmployeeModel> listEmployeeModels;
    private EmployeeAdapter employeeAdapter;

    @Bind(R.id.rv_list) RecyclerView rvList;

    public EmployeesFragment() {
        // Required pty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard_create_employee, container, false);
        ButterKnife.bind(this, view);

        realmConfig = new RealmConfiguration.Builder(getContext()).build();
        realm = Realm.getInstance(realmConfig);

        rvList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(llm);

        listEmployeeModels = new ArrayList<EmployeeModel>();
        employeeAdapter = new EmployeeAdapter(listEmployeeModels);

        //Query for all the employee
        RealmResults<EmployeeModel> query = realm.where(EmployeeModel.class)
                .findAll();

        for(EmployeeModel o : query) {
            listEmployeeModels.add(o);
        }
        rvList.setAdapter(employeeAdapter);

        return view;
    }

    @OnClick(R.id.btn_add)
    public void AddEmployee() {
        startActivity(new Intent(getContext(), CreateEmployeeActivity.class));
    }

    private void AddEmployeeToDatabase() {

    }
}
