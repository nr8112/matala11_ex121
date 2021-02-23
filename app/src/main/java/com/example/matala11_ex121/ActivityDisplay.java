package com.example.matala11_ex121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import static com.example.matala11_ex121.Users.KEY_ID;
import static com.example.matala11_ex121.Users.TABLE_USERS;

public class ActivityDisplay extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor c;
    Spinner names;
    EditText Homephone, Address, Pphone, ParentN1,ParentN2, SName,SPhone;

    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter adp;
    ArrayList<Integer> Kil = new ArrayList<Integer>();

    String SNAME,SPHONE,ADDRESS, HOMEP, PARENTN1,PARENTN2,PPHONE1;
    int pos,idS,a;     //active or not





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        //
        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        SName=findViewById(R.id.SName);
        SPhone=findViewById(R.id.SPhone);
        ParentN1=findViewById(R.id.parentN1);
        ParentN2=findViewById(R.id.parentN2);
        Pphone=findViewById(R.id.parentP);
        Address=findViewById(R.id.Address);
        Homephone=findViewById(R.id.HPhone);
        names= findViewById(R.id.Spinnames);
        printArrayNames();
    }


    /**
     * puts all the student information in fileds.
     */
    public void printInfo() {

        db = hlp.getWritableDatabase();
        c = db.query(TABLE_USERS, null, null, null, null, null, null);

        int col1 = c.getColumnIndex(KEY_ID);
        int col2 = c.getColumnIndex(Users.NAME);
        int col3 = c.getColumnIndex(Users.STUDENT_PHONE);
        int col4 = c.getColumnIndex(Users.PARENT_NAME_1);
        int col5 = c.getColumnIndex(Users.PARENT_NAME_2);
        int col6 = c.getColumnIndex(Users.PARENT_PHONE);
        int col7 = c.getColumnIndex(Users.ADDRESS);
        int col8 = c.getColumnIndex(Users.HOME_PHONE);
        int col9 = c.getColumnIndex(Users.ACTIVE_OR_NOT);

        c.moveToFirst();



        while (!c.isAfterLast()) {
            int key = c.getInt(col1);
            String name = c.getString(col2);
            String sdudentP = c.getString(col3);
            String parentN1 = c.getString(col4);
            String parentN2 = c.getString(col5);
            String parentP = c.getString(col6);
            String address = c.getString(col7);
            String homeP = c.getString(col8);
            int active = c.getInt(col9);

            if (idS == key) {

                SName.setText(name);
                SPhone.setText(sdudentP);
                ParentN1.setText(parentN1);
                ParentN2.setText(parentN2);
                Pphone.setText(parentP);
                Address.setText(address);
                Homephone.setText(homeP);
            }
        }
        c.close();
        db.close();
    }


    /**
     * description- Prints the list of student names in the spinner.
     */
    public void printArrayNames() {

        db = hlp.getWritableDatabase();
        tbl = new ArrayList<>();
        tbl.add("בחר תלמיד");
        c = db.query(TABLE_USERS, null, null,null, null, null,null);

        int col1 = c.getColumnIndex(KEY_ID);
        int col2 = c.getColumnIndex(Users.NAME);
        int col10 = c.getColumnIndex(Users.ACTIVE_OR_NOT);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            int key = c.getInt(col1);
            String name = c.getString(col2);
            int activ= c.getInt(col10);
            if(activ==1) {
                String tmp = " " + name + " -לא פעיל";
                tbl.add(tmp);
            }
            else {
                String tmp = " " + name;
                tbl.add(tmp);
            }
            Kil.add(key);
            c.moveToNext();
        }
        c.close();
        db.close();
        names.setOnItemSelectedListener(this);
        adp= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tbl);
        names.setAdapter(adp);
    }


    /**
     * description- Which reva is selected.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;

        if (spinner.getId() == R.id.Spinnames) {
            pos = position;
            if (position == 0) {

                SName.setText("");
                SPhone.setText("");
                ParentN1.setText("");
                ParentN2.setText("");
                Pphone.setText("");
                Address.setText("");
                Homephone.setText("");
            } else {
                idS = position;
                printInfo();
            }
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    /**
     * description- Updates the data
     */
    public void update(View view) {

        SNAME = SName.getText().toString();
        SPHONE = SPhone.getText().toString();
        ADDRESS = Address.getText().toString();
        HOMEP = Homephone.getText().toString();
        PARENTN1 = ParentN1.getText().toString();
        PARENTN2 = ParentN2.getText().toString();
        PPHONE1 = Pphone.getText().toString();


        ContentValues cv = new ContentValues();
        db = hlp.getWritableDatabase();
        cv.put(Users.NAME, SNAME);
        cv.put(Users.STUDENT_PHONE, SPHONE);
        cv.put(Users.PARENT_NAME_1, PARENTN1);
        cv.put(Users.PARENT_NAME_2, PARENTN2);
        cv.put(Users.PARENT_PHONE, PPHONE1);
        cv.put(Users.ADDRESS, ADDRESS);
        cv.put(Users.HOME_PHONE, HOMEP);
        cv.put(Users.ACTIVE_OR_NOT, a);
        db.update(TABLE_USERS, cv, "_id = ?", new String[]{String.valueOf(pos)});
        db.close();
        printArrayNames();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String st = item.getTitle().toString();


        if (st.endsWith("להכנס ציונים")) {
            Intent si = new Intent(this, ActivityGrade.class);
            startActivity(si);
        } else if (st.endsWith("ציונים")) {
            Intent si = new Intent(this, ActivityFilter.class);
            startActivity(si);
        } else if (st.endsWith("תלמידים")) {
            Intent si = new Intent(this, ActivityDisplay.class);
            startActivity(si);
        }
        else if (st.endsWith("Credits")) {
            Intent si = new Intent(this, cred.class);
            startActivity(si);
        }
        else if (st.endsWith("תלמיד חדש")) {
            Intent si = new Intent(this, cred.class);
            startActivity(si);
        }

        return true;
    }
}
