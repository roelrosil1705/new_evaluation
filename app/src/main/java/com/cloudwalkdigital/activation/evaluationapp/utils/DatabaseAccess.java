package com.cloudwalkdigital.activation.evaluationapp.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.models.AnswerModel;
import com.cloudwalkdigital.activation.evaluationapp.models.DataBaseHelper;
import com.cloudwalkdigital.activation.evaluationapp.models.EmployeeModel;
import com.cloudwalkdigital.activation.evaluationapp.models.EventModel;
import com.cloudwalkdigital.activation.evaluationapp.models.LeaderModel;
import com.cloudwalkdigital.activation.evaluationapp.models.QuestionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by henry on 07/07/2016.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private Context cm;
    Globals g = Globals.getInstance();

    private DatabaseAccess(Context context) {
        cm = context;
        this.openHelper = new DataBaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public void SaveSyncEmployee(List<EmployeeModel> empList){
        int startindex = 0;
        database.execSQL("delete from tblemployee");
        for(int x=1;x <= empList.size();x++){
            String addQuery = "INSERT INTO tblemployee (_id, emp_fname, emp_dept, emp_email, emp_pass, emp_lname) VALUES("+empList.get(startindex).getId()+",'"+empList.get(startindex).getFname()+"','"+empList.get(startindex).getDepartment()+"','"+empList.get(startindex).getEmail()+"','"+empList.get(startindex).getPassword()+"','"+empList.get(startindex).getLname()+"')";
            database.execSQL(addQuery);
            startindex++;
        }
    }

    public void SaveSyncTls(List<LeaderModel> leadList){
        int startindex = 0;
        for(int x=1;x <= leadList.size();x++){
            String selectQry = "SELECT * FROM tblteamleader WHERE _id="+leadList.get(startindex).getId();
            Cursor c1 = database.rawQuery(selectQry,null);
            if(c1.getCount() > 0){
                String addQuery = "UPDATE tblteamleader SET tfname='"+leadList.get(startindex).getFname()+"', tlname='"+leadList.get(startindex).getLname()+"', temail='"+leadList.get(startindex).getEmail()+"' WHERE _id="+leadList.get(startindex).getId();
                database.execSQL(addQuery);
            }else{
                String addQuery = "INSERT INTO tblteamleader (_id, tfname, tlname, temail) VALUES("+leadList.get(startindex).getId()+",'"+leadList.get(startindex).getFname()+"','"+leadList.get(startindex).getLname()+"','"+leadList.get(startindex).getEmail()+"')";
                database.execSQL(addQuery);
            }
            c1.close();
            startindex++;
        }
    }

    public void SaveSyncNego(List<LeaderModel> leadList){
        int startindex = 0;
        for(int x=1;x <= leadList.size();x++){
            String selectQry = "SELECT * FROM tblnegotiator WHERE _id="+leadList.get(startindex).getId();
            Cursor c1 = database.rawQuery(selectQry,null);
            if(c1.getCount() > 0){
                String addQuery = "UPDATE tblnegotiator SET nfname='"+leadList.get(startindex).getFname()+"', nlname='"+leadList.get(startindex).getLname()+"', nemail='"+leadList.get(startindex).getEmail()+"' WHERE _id="+leadList.get(startindex).getId();
                database.execSQL(addQuery);
            }else{
                String addQuery = "INSERT INTO tblnegotiator (_id, nfname, nlname, nemail) VALUES("+leadList.get(startindex).getId()+",'"+leadList.get(startindex).getFname()+"','"+leadList.get(startindex).getLname()+"','"+leadList.get(startindex).getEmail()+"')";
                database.execSQL(addQuery);
            }
            c1.close();
            startindex++;
        }
    }

    public EmployeeModel getEmployee(String email) {

        Cursor cursor = database.query("tblemployee",new String[] {"_id", "emp_fname", "emp_lname", "emp_email", "emp_pass", "emp_dept"}, "emp_email" + "=?",new String[] {String.valueOf(email)},null,null,null);
        EmployeeModel employee = new EmployeeModel();
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();

            try{
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setFname(cursor.getString(1));
                employee.setLname(cursor.getString(2));
                employee.setDepartment(cursor.getString(5));
                employee.setEmail(cursor.getString(3));
                employee.setPassword(cursor.getString(4));
            }catch (Exception e){

            }
        }else{
            employee = null;
        }

        // return contact

        return employee;
    }

    public List<String> getAssignLead(int lead, int eventid){
        List<String> intList = null;
        Cursor cursor = database.rawQuery("SELECT * FROM tblevents WHERE _id="+eventid, null);

        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                try{
                    if(cursor.getString(lead) != null){
                        JSONArray jsonArry = new JSONArray(cursor.getString(lead));
                        if (null == intList) {
                            intList = new ArrayList<String>();
                        }
                        for (int i = 0; i < jsonArry.length(); i++) {
                            intList.add(jsonArry.getString(i));
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }

            cursor.close();
        }else{
            intList = null;
        }
        return intList;
    }

    public List<LeaderModel> getLeaders(List<String> asignLead,String tablename){
        List<LeaderModel> feedItemList = new ArrayList<LeaderModel>();

        for (String qId : asignLead){
            String selectQuery = "SELECT * FROM "+ tablename +" WHERE _id=" + Integer.parseInt(qId);
            Cursor cursor = database.rawQuery(selectQuery, null);
            if(cursor.getCount() > 0){
                if (cursor != null)
                    cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    //Log.d("1.) GET QUESTION",cursor.getString(1));
                    LeaderModel qModel = new LeaderModel();
                    qModel.setId(Integer.parseInt(cursor.getString(0)));
                    qModel.setFname(cursor.getString(1));
                    qModel.setLname(cursor.getString(2));
                    qModel.setEmail(cursor.getString(4));
                    feedItemList.add(qModel);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }

        return feedItemList;
    }

    public List<EventModel> getEvents(String eid){
        Cursor cursor = database.rawQuery("SELECT * FROM tblevents", null);
        List<EventModel> feedItemList = new ArrayList<EventModel>();
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                try{
                    JSONArray jsonArry = new JSONArray(cursor.getString(5));
                    if (null == feedItemList) {
                        feedItemList = new ArrayList<EventModel>();
                    }
                    for (int i = 0; i < jsonArry.length(); i++) {
                        EventModel emodel = new EventModel();
                        if(jsonArry.getString(i).equalsIgnoreCase(eid)){
                            emodel.setId(Integer.parseInt(cursor.getString(0)));
                            emodel.setName(cursor.getString(1));
                            emodel.setJonum(cursor.getString(2));
                            emodel.setEventdate(cursor.getString(3));
                            feedItemList.add(emodel);
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }

            cursor.close();
        }else{
            feedItemList = null;
        }

        return feedItemList;
    }

    public List<String> getAssign(){
        List<String> intList = new ArrayList<String>();
        String selectQuery = "SELECT * FROM tblasignquestion WHERE dept = '" + g.getEmployee().getDepartment() + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                intList.add(cursor.getString(2));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return intList;
    }

    public List<AnswerModel> getAnswers(String qid){
        List<AnswerModel> feedanswer = new ArrayList<AnswerModel>();
        String selectQuery = "SELECT * FROM tblanswer WHERE qnum = '" + qid + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                //Log.d("1.) GET QUESTION",cursor.getString(1));
                AnswerModel aModel = new AnswerModel();
                aModel.setId(Integer.parseInt(cursor.getString(0)));
                aModel.setContent(cursor.getString(1));
                aModel.setQnum(Integer.parseInt(qid));
                feedanswer.add(aModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return feedanswer;
    }

    public void saveEvaluated(String emp_id, String event_id, String event_type){
        String selectQuery = "SELECT * FROM tblevaluates WHERE emp_id = '" + emp_id + "' AND event_id = '" + event_id + "' AND event_type = '" + event_type + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.getCount() < 1){
            String addQuery = "INSERT INTO tblevaluates (emp_id, event_id, event_type) VALUES('"+emp_id+"','"+event_id+"','"+event_type+"')";
            database.execSQL(addQuery);
        }
        cursor.close();
    }

    public void saveAnswer(String empid, String qcat, String qid, String eventid, String aid){
        String selectQuery = "SELECT * FROM tblrecords WHERE eid = '" + empid + "' AND qans = '" + aid + "' AND qevent = '" + eventid + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                //Log.d("1.) GET QUESTION",cursor.getString(1));
                database.delete("tblrecords", "_id =" + Integer.parseInt(cursor.getString(0)), null);
                cursor.moveToNext();
            }
        }
        cursor.close();

        String addQuery = "INSERT INTO tblrecords (eid, qcat, qevent, qid, qans) VALUES('"+empid+"','"+qcat+"','"+eventid+"','"+qid+"','"+aid+"')";
        database.execSQL(addQuery);
    }

    public List<QuestionModel> getQuestion(String qcategory, List<String> questionId){
        List<QuestionModel> feedItemList = new ArrayList<QuestionModel>();
        List<String> preevent = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        List<String> other = Arrays.asList("1", "2", "6", "8", "9", "10");
        List<String> listEvent = other;

        if(qcategory.equalsIgnoreCase("pre")){
            listEvent = preevent;
        }

        for (String qdept : listEvent) {
            for (String qId : questionId){
                String selectQuery = "SELECT * FROM tblquestions WHERE _id=" + Integer.parseInt(qId) + " AND qcat='" + qcategory + "' AND qdept='" + qdept + "'";
                //Log.d("selectQuery",selectQuery);
                Cursor cursor = database.rawQuery(selectQuery, null);
                if(cursor.getCount() > 0){
                    if (cursor != null)
                        cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        //Log.d("1.) GET QUESTION",cursor.getString(1));
                        QuestionModel qModel = new QuestionModel();
                        qModel.setId(Integer.parseInt(cursor.getString(0)));
                        qModel.setQuestion(cursor.getString(1));
                        qModel.setQdept(cursor.getString(2));
                        qModel.setQcat(cursor.getString(3));
                        qModel.setQtype(cursor.getString(4));
                        qModel.setQsub(cursor.getString(5));
                        feedItemList.add(qModel);
                        cursor.moveToNext();
                    }
                }
                cursor.close();
            }
        }
        return feedItemList;
    }

    public boolean checkEvent(String empid, String eventid, String eventype){
        boolean stat = false;
        Cursor cursor = database.rawQuery("SELECT _id, emp_id, event_id, event_type FROM tblevaluates WHERE emp_id = ? AND  event_id = ? AND  event_type = ?", new String[] { empid, eventid, eventype });
        if(cursor.getCount() > 0){
            if (cursor != null)
                cursor.moveToFirst();
            stat = true;
        }
        return stat;
    }

    public List<String> getQuotes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM tblemployee", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
