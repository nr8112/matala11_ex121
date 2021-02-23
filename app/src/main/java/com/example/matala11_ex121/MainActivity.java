package com.example.matala11_ex121;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Noa Rahamim
 * @version 1.0
 * @since 23/2/2021 short description-An application that will be used by classroom educators
 * The application has 3 Activities:
 * The first will be used for input to tables
 * The second will be used to display the information, with the option to delete
 * The third will be used to display the information with sorting and filtering options.
 *
 * note- yulia helped me with the xml because did'nt know how its supposed to look
 */
public class MainActivity extends AppCompatActivity {
    TextView t;
    String String_name, String_homeP, String_address, String_studentP, String_parent1, String_parent2, String_parentP;
    EditText nameEdit, addressEdit, homePEdit, studentPEdit, parent1Edit, parent2Edit, parentPEdit;
    int activ;
    Switch yes_no;
    SQLiteDatabase db;
    HelperDB hlp;

    /**
     * onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yes_no = findViewById(R.id.sw);
        t = findViewById(R.id.t);
        nameEdit = findViewById(R.id.n);
        studentPEdit = findViewById(R.id.studentP);
        parent1Edit = findViewById(R.id.parentName1);
        parent2Edit = findViewById(R.id.parentName2);
        parentPEdit = findViewById(R.id.parentP);
        addressEdit = findViewById(R.id.address);
        homePEdit = findViewById(R.id.homeP);
        activ = 1;


        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();


    }

    /**
     * description- input all the fields and mace sure that the fields are Filled
     *
     * @param view the view
     */
    public void ishor(View view) {

        /*
        if its null -tosat
            Toast.makeText(this, "please enter all the", Toast.LENGTH_SHORT).show();
        else{
         */
        String_name = nameEdit.getText().toString();
        String_studentP = studentPEdit.getText().toString();
        String_parent1 = parent1Edit.getText().toString();
        String_parent2 = parent2Edit.getText().toString();
        String_parentP = parentPEdit.getText().toString();
        String_address = addressEdit.getText().toString();
        String_homeP = homePEdit.getText().toString();

        if (String_address == null || String_name == null || String_studentP == null || String_parent1 == null || String_parent2 == null || String_parentP == null || String_homeP == null) {
            Toast.makeText(this, "please enter all the fields ", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues cv = new ContentValues();
            cv.put(Users.NAME, String_name);
            cv.put(Users.STUDENT_PHONE, String_studentP);
            cv.put(Users.PARENT_NAME_1, String_parent1);
            cv.put(Users.PARENT_NAME_2, String_parent2);
            cv.put(Users.PARENT_PHONE, String_parentP);
            cv.put(Users.HOME_PHONE, String_homeP);
            cv.put(Users.ADDRESS, String_address);
            cv.put(Users.ACTIVE_OR_NOT, activ);

            db = hlp.getWritableDatabase(); // writ
            db.insert(Users.TABLE_USERS, null, cv);
            db.close();

            nameEdit.setText("");
            studentPEdit.setText("");
            parent1Edit.setText("");
            parent2Edit.setText("");
            parentPEdit.setText("");
            addressEdit.setText("");
            homePEdit.setText("");
        }
    }


    /**
     * description- if switch is on THE uesr is active else, not active.
     *
     * @param view the view
     */
    public void active(View view) {

        if (yes_no.isChecked()) {
            activ = 0;
            t.setText("פעיל");
        } else {
            activ = 1;
            t.setText("לא פעיל");
        }
    }


//--------------------------------------------------------------------------------------------------------------------------------

    /**
     * Create Options Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * description- if "תלמיד חדש" selected return back to the Main activity.
     */
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
/**
 * end main activity
 */