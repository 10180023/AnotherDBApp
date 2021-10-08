package com.example.anotherdbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private DBHelper helper;
    private EditText edName;
    private EditText edAge;
    private ListView usersList;
    private SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = findViewById(R.id.editTextName);
        edAge = findViewById(R.id.editTextAge);
        helper = new DBHelper(this);
        usersList = findViewById(R.id.lbText);
    }


    public void addClick(View view) {
        String name = edName.getText().toString();
        int age =Integer.parseInt( edAge.getText().toString());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.ColumnName, name);
        values.put(DBHelper.ColumnAge, age);
        db.insert(DBHelper.TableName, null, values);
    }

    public void clearClick(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(DBHelper.TableName, null, null);

    }

    public void getClick(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor query = db.query(DBHelper.TableName,
                null,
                DBHelper.ColumnName + " like ?",
                new String[] {"%Max%"},
                null,
                null,
                null,
                null);
        /*userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, query,
                new String[]{DBHelper.ColumnName, DBHelper.ColumnAge}, new int[]{android.R.id.text1, android.R.id.text2}, 0);*/
        userAdapter = new SimpleCursorAdapter(this, R.layout.user_item, query,
                new String[]{DBHelper.ColumnId, DBHelper.ColumnName, DBHelper.ColumnAge}, new int[]{R.id.tvUserId, R.id.tvUserName, R.id.tvUserAge}, 0);
        usersList.setAdapter(userAdapter);
    }
}