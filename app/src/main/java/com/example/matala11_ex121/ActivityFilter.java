package com.example.matala11_ex121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class ActivityFilter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
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