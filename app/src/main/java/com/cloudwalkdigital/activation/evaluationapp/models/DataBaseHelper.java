package com.cloudwalkdigital.activation.evaluationapp.models;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by henry on 14/04/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.cloudwalkdigital.activation.evaluationapp/databases/";
    private static String DB_NAME = "evaluation.db";
    private SQLiteDatabase dbQuestions;
    private final Context myContext;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist){

        }else{
            this.getReadableDatabase();
            //copyDataBase();
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            //checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Toast.makeText(this.myContext,"Database Open",Toast.LENGTH_LONG).show();
        }catch(SQLiteException e){

            //database does't exist yet.

        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        dbQuestions = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

    }

    public String[][] getDepartments() {

        final String TABLE_NAME = "departments";

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String[][] data      = null;
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                // get the data into array, or class variable
                data[i][0] = cursor.getString(0);
                data[i][1] = cursor.getString(1);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    @Override
    public synchronized void close() {

        if(dbQuestions != null)
            dbQuestions.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
