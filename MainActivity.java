package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase sqliteDB = null;
    EditText id, name, phone;
    Button button;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        id = (EditText) findViewById(R.id.editTextID);
        name = (EditText) findViewById(R.id.editTextName);
        phone = (EditText) findViewById(R.id.editTextPhone);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        result = (TextView) findViewById(R.id.textView);
        try{
            sqliteDB = openOrCreateDatabase("sample.db", MODE_PRIVATE, null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        String sqlCreateTable =
                "CREATE TABLE IF NOT EXISTS CONTACT_T(ID INTEGER NOT NULL, NAME TEXT, PHONE TEXT)";
        sqliteDB.execSQL(sqlCreateTable);
    }

    @Override
    public void onClick(View view) {
        String sqlInsert =
                "INSERT INTO CONTACT_T(ID, NAME, PHONE) VALUES ('" + id.getText().toString() + "' , '"
                + name.getText().toString() +"' , '"
                + phone.getText().toString() + "')";
        sqliteDB.execSQL(sqlInsert);

        String sqlSelect = "SELECT * FROM CONTACT_T";
        Cursor cursor = null;
        cursor = sqliteDB.rawQuery(sqlSelect, null);
        String tableResult = "";
        while (cursor.moveToNext()){
            tableResult += cursor.getInt(0) + ", " +
                    cursor.getString(1) +", " +
                    cursor.getString(2) + "\n";
        }
        result.setText(tableResult);
    }
}