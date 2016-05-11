package com.cloudwalkdigital.activation.evaluationapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudwalkdigital.activation.evaluationapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jpjpjp28 on 14/04/2016.
 */
public class EmployeesFragment extends Fragment {

    public Object rvList;

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

        return view;
    }

    @OnClick(R.id.btn_add)
    public void AddEmployee() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setView(R.layout.dialog_create_employee);
        dialog.setTitle("Create Employee");
        dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
